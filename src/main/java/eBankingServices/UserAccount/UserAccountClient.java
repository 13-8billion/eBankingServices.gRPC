package eBankingServices.UserAccount;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.LoginConfirmation;
import eBankingServices.UserAccount.ViewRequest;
import eBankingServices.UserAccount.AccountInfo;
import eBankingServices.UserAccount.PasswordRequest;
import eBankingServices.UserAccount.PasswordConfirmation;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountBlockingStub;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountStub;
import java.util.Scanner;


public class UserAccountClient {
	

	private static UserAccountBlockingStub blockingStub;
	private static UserAccountStub asyncStub;

	
	public static void main(String args[]) throws InterruptedException {
		
		final ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50052)
				.usePlaintext()
				.build();
		
		//stubs -- generate from .proto
		blockingStub = UserAccountGrpc.newBlockingStub(channel);
		asyncStub = UserAccountGrpc.newStub(channel);
		
		// call methods in transactions client class
		login();
		viewAccount();
		changePassword();
	}
	
	
// Login - Unary gRPC
	
	public static void login() {
		
		Scanner in = new Scanner(System.in);
		String username;
		String password;
		
		System.out.println("Client >>>>>>>>> Requesting login...");
		System.out.println("Enter username (Amy): ");
		username = in.next();
		System.out.println("Enter password (123): ");
		password = in.next();
		
		
		LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder()
				.setUsername(username)
				.setPassword(password)
				.build());
		
		System.out.println(response.getMessage());
				
	}
	     

//  View Account Info - Server-streaming gRPC
	
	public static void viewAccount() {
		
		Scanner in = new Scanner(System.in);
		int accountNo;
		String euro = "\u20ac";
		
		System.out.println("Client >>>>>>>>> Enter account number to view (1, 2 or 3): ");
		accountNo = in.nextInt();

		ViewRequest request = ViewRequest.newBuilder()
				.setAccNo(accountNo)
				.build();


		StreamObserver<AccountInfo> responseObserver = new StreamObserver<AccountInfo>() {

			@Override
			public void onNext(AccountInfo value) {
				System.out.println("Client >>>>>>>>> Requesting details for Account No: " + accountNo);
				System.out.println(value.getMessage());
				System.out.println("Account No:  " + value.getAccNo());
				System.out.println("Full Name:  " + value.getFirstName() + " " + value.getLastName());
				System.out.println("Balance:  " + euro + value.getBalance());			
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Client >>>>>>>>> Server stream complete");
			}

		};

		asyncStub.viewAccount(request, responseObserver);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	  
// Change Password - Unary gRPC
	
	public static void changePassword() {
		
		Scanner in = new Scanner(System.in);
		String username;
		String currPass;
		String newPass;
		
		System.out.println("Client >>>>>>>>> Requesting to change password...");
		System.out.println("Enter username (Amy): ");
		username = in.next();
		System.out.println("Enter current password (123): ");
		currPass = in.next();
		System.out.println("Enter new password: ");
		newPass = in.next();
		
		
		PasswordConfirmation response = blockingStub.changePassword(PasswordRequest.newBuilder()
				.setUsername(username)
				.setCurrPass(currPass)
				.setNewPass(newPass)
				.build());
		
		System.out.println(response.getMessage());
				
	}	
}	

	   

