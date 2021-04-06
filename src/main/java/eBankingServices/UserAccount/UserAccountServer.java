package eBankingServices.UserAccount;

import java.io.IOException;
import java.util.Arrays;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountImplBase;
import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.LoginConfirmation;
import eBankingServices.UserAccount.ViewRequest;
import eBankingServices.UserAccount.AccountInfo;
import eBankingServices.UserAccount.PasswordRequest;
import eBankingServices.UserAccount.PasswordConfirmation;


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

	
// Login - Unary gRPC

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

	
// View Account Info - Server-streaming gRPC
	
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

	
// Change Password - Unary gRPC

	public void changePassword(PasswordRequest request, StreamObserver<PasswordConfirmation> responseObserver) {
		
		PasswordConfirmation pc;
		

				if (changePass(request.getUsername(), request.getCurrPass(), request.getNewPass())) {
						
					pc = PasswordConfirmation.newBuilder()	
							.setMessage("Server >>>>>>>>> Password for " + request.getUsername() + " has been changed to " + request.getNewPass())
							.build();
					
				} else {	
					pc = PasswordConfirmation.newBuilder()	
							.setMessage("Server >>>>>>>>> Username or Password Incorrect!" )
							.build();
				}
				
		responseObserver.onNext(pc);
		responseObserver.onCompleted();
	}
	
	
// Change password method
	
	private boolean changePass(String username, String currPass, String newPass)
	{
		if (username.equals("Amy") && currPass.equals("123"))
			currPass = newPass;
			return true;
	}
	
	
// Authenticate user method
	
	private boolean authenticateUser(String username, String password) 
	{
		if (username.equals("Amy") && password.equals("123")) 
		{
			return true;
		} 
		else 
		{
			return false;
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

