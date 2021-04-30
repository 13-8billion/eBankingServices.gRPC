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

			// hard code some customer accounts to array of type Customer
			new Customer(1, "Arthur", "Morgan", 27000), new Customer(2, "Sadie", "Adler", 5900),
			new Customer(3, "Amy", "Percival", 33300) };

	// hard code account balances to array of type double
	private double[] accounts = { 0, 27000, 5900, 33333 };

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		TransactionsServer transactions = new TransactionsServer();

		// get properties from getProperties() method
		Properties prop = transactions.getProperties();

		// register the service
		transactions.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {
			// build and start the server
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

// register transactions service

	private void registerService(Properties prop) {

		try {
			// Create JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");

			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			String service_description_properties = prop.getProperty("service_description");// "path=index.html";

			// Register the service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

// get service properties

	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/Transactions.properties")) {

			prop = new Properties();

			// load the properties file
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

// Deposit method - Unary

	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver)
			throws NumberFormatException {

		DepositConfirmation dc = null;

		double newBalance;
		String accNo = String.valueOf(request.getAccNo());

		try {
			for (int i = 0; i < cArray.length; i++) { // for loop iterates through customer array
				Customer c = cArray[i]; // adds current iteration to object of type Customer
				
				// new balance equals sum plus current customer balance from array
				newBalance = request.getSum() + c.getBalance(); 

				// get account number from user and validate by invoking validate methods
				if (request.getAccNo() == c.getAccNo() && validAccNo(accNo)) { 
					// build the response
					dc = DepositConfirmation.newBuilder()
							.setMessage("SUCCESS " + newline + euro + request.getSum() + " deposited into Acc No. "
									+ request.getAccNo() + newline + "Your Previous Balance: " + euro + c.getBalance()
									+ newline + "Your New Balance: " + euro + newBalance)
							.build();

				} else if (request.getAccNo() != c.getAccNo() && !validAccNo(accNo)) {
					dc = DepositConfirmation.newBuilder().setMessage("Account number: " + request.getAccNo()
							+ " is not valid!" + newline + "Please enter a valid Account Number (1, 2 or 3)").build();

				}
			}

			responseObserver.onNext(dc);// send the response
			responseObserver.onCompleted(); // complete the RPC call

		} catch (RuntimeException e) {
			e.printStackTrace();

		}
	}

// Transfer money - Bi-directional streaming

	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver)
			throws NumberFormatException {

		return new StreamObserver<TransferSum>() {

			String newline = "\n\r";
			String conf;

			@Override
			public void onNext(TransferSum request) {

				String toAccNo = String.valueOf(request.getToAccNo()); // extract the required fields from user input
				String fromAccNo = String.valueOf(request.getFromAccNo());

				try {
					// validate account number and invoke private method transferSum to check account balances for sufficient funds
					if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())
							&& validAccNo(toAccNo) && validAccNo(fromAccNo)) { 
						conf = "Receiving transfer request..." + euro + request.getSum() + " >>> to Acc No. "
								+ request.getToAccNo();
						// build response
						responseObserver.onNext(TransferConfirmation.newBuilder().setConf(conf).build()); 

						Thread.sleep(1000);// sleep to simulate wait time

						// invoke private method withdraw() to precess the transfer
						conf = ("SUCCESS! " + newline + newline + "Acc No. " + request.getFromAccNo() + " transferred "
								+ euro + request.getSum() + " to Acc No.  " + request.getToAccNo() + newline
								+ withdraw(request.getFromAccNo(), request.getSum()) + newline + newline); 

					} else if (!transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
						conf = ("FAILED! not enough funds in Account No. " + request.getFromAccNo());
					}
					Thread.sleep(2000); // sleep to simulate wait time

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (AccNoException ex) { // custom exception to handle invalid account numbers

					System.out.println(ex.getMessage());
					conf = ("Receiving transfer request... Invalid Account Number!" + newline
							+ "Please enter a valid Account Number (1, 2 or 3)" + newline);

				} catch (NumberFormatException ex) { // custom exception to handle non-number inputs in number fields

					System.out.println(ex.getMessage());
					conf = ("Receiving transfer request... Account number or sum must be a number!!" + newline);

				}
				// send the response
				responseObserver.onNext(TransferConfirmation.newBuilder().setConf(conf).build());

			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {
				System.out.println("Transactions complete");
				responseObserver.onCompleted(); // complete the RPC call

			}

		};

	}

	// Request money from another account - Bi-directional streaming

	@Override
	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {

		return new StreamObserver<RequestSum>() {
			String status;
			int check = 0;

			@Override
			public void onNext(RequestSum request) {
				String toAccNo = String.valueOf(request.getToAccNo()); // extract required fields from user input
				String fromAccNo = String.valueOf(request.getFromAccNo());
				if (validAccNo(toAccNo) && validAccNo(fromAccNo)) { // validate account number

					status = ("Acc No. " + request.getToAccNo() + " requesting " + euro + request.getSum()
							+ " from Acc No. " + request.getFromAccNo() + "..." + newline + newline + "Pending...");
					// build response
					responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());

				} else if (!validAccNo(toAccNo)) { // if to-account number invalid
					status = ("Account number: " + request.getToAccNo() + " is not valid!" + newline
							+ "Request cancelled." + newline
							+ "Please try again with a vaild account number (1, 2 or 3)");
					check = 3;

					responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());
					;

				} else if (!validAccNo(fromAccNo)) {// if from -account number invalid
					status = ("Account number: " + request.getFromAccNo() + " is not valid!" + newline
							+ "Request cancelled." + newline
							+ "Please try again with a vaild account number (1, 2 or 3)");
					check = 3;
					responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());
					;

				}
				try {
					// sleep to simulate waiting for confirmation from other account
					Thread.sleep(6000);

				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (check != 3) { // check variable to print 'confirm' only if transfer is validated above
					status = ("Confirming...");
					responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build()); // build response
				}
				try {
					// sleep to simulate waiting for confirmation from other account
					Thread.sleep(6000);

				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (check != 3) {
					status = ("Request confirmed!" + newline + newline + "Acc No. " + request.getFromAccNo()
							+ " has transfered " + newline + euro + request.getSum() + " to Acc No. "
							+ request.getToAccNo() + newline + newline + "Monthly request: " + request.getMonthly());
					responseObserver.onNext(RequestStatus.newBuilder().setStatus(status).build());
				}
				check = 0;
			}

			@Override
			public void onError(Throwable t) {

				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Receiving money request complete");

				// completed too
				responseObserver.onCompleted(); // complete RPC call
			}

		};
	}

// method to withdraw money from account 

	private String withdraw(int fromAccNo, double sum) {

		double preBalance = accounts[fromAccNo];

		double newBalance = accounts[fromAccNo] - sum;

		String res = "Previous Balance: " + euro + preBalance + newline + "New Balance: " + euro + newBalance;

		return res;

	}

// validate account number

	private boolean validAccNo(String accNo) {
		if (accNo.matches("([1-3])")) {
			return true;
		} else {
			return false;
		}
	}

// method to check for sufficient funds and throw valid AccNo exception

	private boolean transferSum(int toAccNo, int fromAccNo, double sum) throws AccNoException {

		String toAccNos = String.valueOf(toAccNo);
		String fromAccNos = String.valueOf(fromAccNo);

		// if account number not 1-3 then invalid and throw new exception
		if (!toAccNos.matches("([1-3])") || !fromAccNos.matches("([1-3])")) {
			throw new AccNoException("Invalid Account Number! Please enter a valid Account Number (1, 2 or 3)");
		}

		if (accounts[fromAccNo] < sum) {
			return false;
		} else {
			accounts[fromAccNo] -= sum;
			accounts[toAccNo] += sum;
			return true;
		}

	}

// custom exception class to validate account numbers
	public class AccNoException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AccNoException(String errorMsg) {
			super(errorMsg);
		}
	}

// custom exception class to validate number inputs
	public class NoFormatException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoFormatException(String errorMsg) {
			super(errorMsg);
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
