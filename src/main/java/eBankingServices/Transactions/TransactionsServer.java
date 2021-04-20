package eBankingServices.Transactions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsImplBase;

public class TransactionsServer extends TransactionsImplBase {

	private String euro = "\u20ac";
	String newline = "\n\r";

	private Customer[] cArray = new Customer[] {

			// hard code some customer accounts
			new Customer(1, "Arthur", "Morgan", 27000), new Customer(2, "Sadie", "Adler", 5900),
			new Customer(3, "Amy", "Percival", 33300) };

	// hard code account balances
	private double[] accounts = { 0, 27000, 5900, 33333 };

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		TransactionsServer transactions = new TransactionsServer();

		Properties prop = transactions.getProperties();

		// register the service
		transactions.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {

			Server server = ServerBuilder.forPort(port).addService(transactions).build().start();

			System.out.println("Transaction server started, listening on " + port);

			server.awaitTermination();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

// register service

	private void registerService(Properties prop) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");

			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			String service_description_properties = prop.getProperty("service_description");// "path=index.html";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

//			// Wait a bit
//			Thread.sleep(500);

			// Unregister all services
			// jmdns.unregisterAllServices();

		} catch (IOException e) {
			System.out.println(e.getMessage());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

// get service properties

	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/Transactions.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("Transactions Service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

// Deposit money method - Unary

	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {

		DepositConfirmation dc = null;

		double newBalance;
		String accNo = String.valueOf(request.getAccNo());

		for (int i = 0; i < cArray.length; i++) {
			Customer c = cArray[i];

			newBalance = request.getSum() + c.getBalance();

			if (request.getAccNo() == c.getAccNo() && validAccNo(accNo)) {

				dc = DepositConfirmation.newBuilder()
						.setMessage("SUCCESS " + newline + euro + request.getSum() + " deposited into Acc No. "
								+ request.getAccNo() + newline + "Your Previous Balance: " + euro + c.getBalance()
								+ newline + "Your New Balance: " + euro + newBalance)
						.build();

			} else if (request.getAccNo() != c.getAccNo() && !validAccNo(accNo)) {
				dc = DepositConfirmation.newBuilder().setMessage("Account number: " + accNo + " is not valid!" + newline
						+ "Please enter a valid Account Number (1, 2 or 3)").build();
			}
		}
		responseObserver.onNext(dc);
		responseObserver.onCompleted();
	}

// Transfer money method - Client streaming

	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {

		return new StreamObserver<TransferSum>() {

			TransferConfirmation reply;

			@Override
			public void onNext(TransferSum request) {
				
				System.out.println(request.getMessage());

				String newline = "\n\r";
				String toAccNo = String.valueOf(request.getToAccNo());
				String fromAccNo = String.valueOf(request.getFromAccNo());
				int fromAccNo1 = request.getFromAccNo();

				try {
					if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())
							&& validAccNo(toAccNo) && validAccNo(fromAccNo)) {

						withdraw(fromAccNo1, request.getSum());

						reply = TransferConfirmation.newBuilder()
								.setMessage("SUCCESS " + newline + euro + request.getSum()
										+ " transferred to Account No.  " + request.getToAccNo() + newline
										+ withdraw(fromAccNo1, request.getSum()))
								.build();

					} else if (!transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
						reply = TransferConfirmation.newBuilder()
								.setMessage("FAILED not enough funds in Account No. " + request.getFromAccNo()).build();

					} else if (!validAccNo(toAccNo)) {
						reply = TransferConfirmation.newBuilder().setMessage("Account number: " + toAccNo
								+ " is not valid!" + newline + "Please enter a valid Account Number (1, 2 or 3)")
								.build();

					} else if (!validAccNo(fromAccNo)) {
						reply = TransferConfirmation.newBuilder().setMessage("Account number: " + fromAccNo
								+ " is not valid!" + newline + "Please enter a valid Account Number (1, 2 or 3)")
								.build();

					}

					Thread.sleep(1000);

				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				responseObserver.onNext(reply);
//				responseObserver.onCompleted();
			}

			@Override
			public void onCompleted() {
				System.out.println("Transaction process completed");
//				System.out.println(reply);
//				responseObserver.onCompleted();

			}

			@Override
			public void onError(Throwable t) {

			}
		};

	}

// Request money from another account - Bi-directional streaming

	@Override
	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {

		return new StreamObserver<RequestSum>() {

			@Override
			public void onNext(RequestSum request) {

				String status = ("Pending...");

				responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());
				try {
					// sleep to simulate waiting for confirmation from other account
					Thread.sleep(6000);

				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				status = ("Do you approve this request?");
				responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());

				try {
					// sleep to simulate waiting for confirmation from other account
					Thread.sleep(6000);

				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				status = ("Request confirmed!" + newline + newline + "Acc No. " + request.getFromAccNo()
						+ " has transfered " + newline + euro + request.getSum() + " to Acc No. " + request.getToAccNo()
						+ newline + newline + "Monthly request? " + request.getMonthly());
				responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());
			}

			@Override
			public void onError(Throwable t) {

				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Receiving money request complete");

				// completed too
				responseObserver.onCompleted();
			}

		};
	}

	private String withdraw(int fromAccNo, double sum) {

		double preBalance = accounts[fromAccNo];

		double newBalance = accounts[fromAccNo] - sum;

		String res = "Previous Balance: " + euro + preBalance + newline + "New Balance: " + euro + newBalance;

		return res;

	}

// valid account number

	private boolean validAccNo(String accNo) {
		if (accNo.matches("([1-3])")) {
			return true;
		} else {
			return false;
		}
	}

// method to check for sufficient funds 

	private boolean transferSum(int toAccNo, int fromAccNo, double sum) {

		if (accounts[fromAccNo] < sum) {
			return false;
		} else {
			accounts[fromAccNo] -= sum;
			accounts[toAccNo] += sum;
			return true;
		}

	}

// Customer class

	private class Customer {

		private int accNo;
		private String firstName;
		private String lastName;
		private double balance;

		// constructor
		public Customer(int accNo, String firstName, String lastName, double balance) {
			this.accNo = accNo;
			this.firstName = firstName;
			this.lastName = lastName;
			this.balance = balance;
		}

		public Customer() {
		}

		public int getAccNo() {
			return accNo;
		}

		public void setAccNo(int accNo) {
			this.accNo = accNo;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}
	}
}
