package eBankingServices.Transactions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsImplBase;


public class TransactionsServer extends TransactionsImplBase {
	
	String euro = "\u20ac";
	
	Customer[] cArray = new Customer[] {

	// hard code some customer accounts
	new Customer(1, "Arthur", "Morgan", 27000),
	new Customer(2, "Sadie", "Adler", 5900),
	new Customer(3, "Amy", "Percival", 33333)
	};

//	// hard code some account balances
	private double[] accounts = {0, 3000, 30000};

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		TransactionsServer transactions = new TransactionsServer();
		
		Properties prop = transactions.getProperties();
		
		// register the service 
		transactions.registerService(prop);

		int port = Integer.valueOf( prop.getProperty("service_port") );

		try {
			
			Server server = ServerBuilder.forPort(port).addService(transactions)
					.build()
					.start();
	
			System.out.println("Transaction server started, listening on " + port);
	
			server.start().awaitTermination();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
// register service method 
	
private void registerService(Properties prop) {
	
	try {
         // Create a JmDNS instance
         JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
         
         String service_type = prop.getProperty("service_type");
         String service_name = prop.getProperty("service_name")  ;

         int service_port = Integer.valueOf( prop.getProperty("service_port") );
         
         String service_description_properties = prop.getProperty("service_description")  ;//"path=index.html";
         
         // Register a service
         ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
         jmdns.registerService(serviceInfo);
         
         System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
         
         // Wait a bit
         Thread.sleep(500);

         // Unregister all services
         //jmdns.unregisterAllServices();

     } catch (IOException e) {
         System.out.println(e.getMessage());
     } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


// get service properties method

private Properties getProperties() {
	
	Properties prop = null;		
	
	 try (InputStream input = new FileInputStream("src/main/resources/Transactions.properties")) {

           prop = new Properties();

           // load a properties file
           prop.load(input);

           // get the property value and print it out
           System.out.println("Transactions Service properies ...");
           System.out.println("\t service_type: " + prop.getProperty("service_type"));
           System.out.println("\t service_name: " +prop.getProperty("service_name"));
           System.out.println("\t service_description: " +prop.getProperty("service_description"));
	       System.out.println("\t service_port: " +prop.getProperty("service_port"));

       } catch (IOException ex) {
           ex.printStackTrace();
       }

	 return prop;
}

// Deposit money method - Unary

	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {
	
	DepositConfirmation dc;
	
	int depositID = 1;
	String newline = "\n\r";
	double newBalance;
	
			
	for(int i=0; i<cArray.length;i++)
	{
		Customer c = cArray[i];
		
		newBalance = request.getSum() + c.getBalance();	
		
		if (request.getAccNo() == c.getAccNo()) 
		{
			
			dc = DepositConfirmation.newBuilder()
						.setMessage("SUCCESS " + newline + euro + request.getSum() +  " deposited into Acc No. " + request.getAccNo() + newline +
			"Previous Balance: " + c.getBalance() + newline + "New Balance: " + newBalance)
						.build();
		
		responseObserver.onNext(dc);
		responseObserver.onCompleted();
		
		}
	}
}
	

	



	
// Transfer money method - Client streaming

	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {
				
		return new StreamObserver<TransferSum>() {
		
				@Override
			public void onNext(TransferSum request) {
			
		try {	
				if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
						
					TransferConfirmation reply = TransferConfirmation.newBuilder()
							.setMessage("SUCCESS " + euro + request.getSum() + " transferred to Account No.  "+ request.getToAccNo())
							.build();
					
					responseObserver.onNext(reply);
						
				} else {
					String message2 = "FAILED not enough funds in Account No. " + request.getFromAccNo();
					
					TransferConfirmation reply2 = TransferConfirmation.newBuilder()
							.setMessage(message2)
							.build();
					
					responseObserver.onNext(reply2);
				}
				Thread.sleep(1000);
				
				Thread.sleep(new Random().nextInt(1000) + 500);


			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
				
			@Override
			public void onCompleted() {

				System.out.println("Transaction process completed" );		
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
			}
		};
	}
	
	
// Request money from another account - Bi-directional streaming
	
	@Override
	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {
		
		return new StreamObserver<RequestSum> () {

			@Override
			public void onNext(RequestSum request) {
		
		try {
			String status = ("Receiving request:  AccountNo. " + request.getToAccNo() + " requesting " + euro + request.getSum() + " from AccountNo. "+ request.getFromAccNo());
				
				RequestStatus reply = RequestStatus.newBuilder()
						.setStatus(status)
						.build();
				
				responseObserver.onNext(reply);
				
				Thread.sleep(1000);
				
				Thread.sleep(new Random().nextInt(1000) + 500);

		
				} catch (RuntimeException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {			
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable t) {
				
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Receiving money request complete");
				
				//completed too
				responseObserver.onCompleted();
			}
			
		};
	}

// method to check sufficient funds for transfers
	
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
			public Customer(int accNo, String firstName, String lastName, double balance)
			{
				this.accNo = accNo;
				this.firstName = firstName;
				this.lastName = lastName;
				this.balance = balance;
			}
			
			public Customer()
			{
			}

			public int getAccNo() {
				return accNo;
			}

			public void setEmpNo(int accNo) {
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
