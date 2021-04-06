package eBankingServices.UserAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountImplBase;
import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.LoginConfirmation;
import eBankingServices.UserAccount.ViewRequest;
import eBankingServices.UserAccount.AccountInfo;
//import eBankingServices.UserAccount.PasswordRequest;
//import eBankingServices.UserAccount.PasswordConfirmation;


public class UserAccountServer extends UserAccountImplBase {
	

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserAccountServer userAccounts = new UserAccountServer();

		int port = 50052;

		Server server = ServerBuilder.forPort(port).addService(userAccounts)
				.build()
				.start();

		System.out.println("User Account server started, listening on " + port);

		server.awaitTermination();
	}

	
// login to account unary rpc

	public void login(LoginRequest request, StreamObserver<LoginConfirmation> responseObserver) {
		
		LoginConfirmation lc;
		

				if (authenticateUser(request.getUsername(), request.getPassword())) {
						
					lc = LoginConfirmation.newBuilder()	
							.setMessage("Server >>>>>>>>> Username and Password Correct! Welcome "+ request.getUsername())
							.build();
					
				} else {	
					lc = LoginConfirmation.newBuilder()	
							.setMessage("Server >>>>>>>>> Username or Password Incorrect!" )
							.build();
				}
				
		responseObserver.onNext(lc);
		responseObserver.onCompleted();
	}

	
// Server-streaming RPC - View Account info
	
	public void viewAccount(ViewRequest request, StreamObserver<AccountInfo> responseObserver) {
			
		AccountInfo reply;
		
		Customer[] cArray = new Customer[3];
		
		cArray[0] = new Customer(1, "Arthur", "Morgan", 27000);
		cArray[1] = new Customer(2, "Sadie", "Adler", 5900);
		cArray[2] = new Customer(3, "Amy", "Percival", 33333);
		
				
		for(int i=0; i<cArray.length;i++)
		{
			Customer c = cArray[i];
			
			if (request.getAccNo() == c.getAccNo()) 
			{
				reply = AccountInfo.newBuilder()
						.setMessage("Server >>>>>>>>> Streaming details for Account No: " + request.getAccNo() + " ...") 
						.setAccNo(request.getAccNo())
						.setFirstName(c.getFirstName())
						.setLastName(c.getLastName())
						.setBalance(c.getBalance())
						.build();	
				responseObserver.onNext(reply);
			}
			
		}
		
		responseObserver.onCompleted();
	}


//		else if (request.getAccNo() == 2)
//		{
//			reply = AccountInfo.newBuilder()
//					.setMessage("Account info for AccNo: " + request.getAccNo() + c2) 
//					.build();
//		}
//		else if (request.getAccNo() == 2)
//		{
//			reply = AccountInfo.newBuilder()
//					.setMessage("Account info for AccNo: " + request.getAccNo() + c3) 
//					.build();
//		}

//		responseObserver.onNext(reply);	
//		responseObserver.onCompleted();
//		
//		try {
//			//wait for a second
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

	
	
	
	

//	@Override
//	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {
//		
//		String euro = "\u20ac";
//		
//		return new StreamObserver<RequestSum> () {
//
//			@Override
//			public void onNext(RequestSum request) {
//				System.out.println("Server >>>>>>>>> Receiving money request:  AccountNo. " + request.getToAccNo() + " is requesting " + euro + request.getSum() + " from AccountNo. "+ request.getFromAccNo());
//				
//				String status =  "Server status >>>>>>>>> Money request is pending...";
//				
//				RequestStatus reply = RequestStatus.newBuilder()
//						.setStatus(status)
//						.build();
//				
//				responseObserver.onNext(reply);
//				
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				
//				t.printStackTrace();
//
//			}
//
//			@Override
//			public void onCompleted() {
//				System.out.println("Server >>>>>>>>> Receiving money request completed ");
//				
//				//completed too
//				responseObserver.onCompleted();
//			}
//			
//		};
//}

	private boolean authenticateUser(String username, String password) {
		if (username.equals("Amy") && password.equals("123")) {
			return true;
		} else {
			return false;
		}
	}
	
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

