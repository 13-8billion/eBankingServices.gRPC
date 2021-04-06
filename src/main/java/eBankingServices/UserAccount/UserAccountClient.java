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
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();
		
		//stubs -- generate from .proto
		blockingStub = UserAccountGrpc.newBlockingStub(channel);
		asyncStub = UserAccountGrpc.newStub(channel);
		
		// call methods in transactions client class
//		login();
		viewAccount();
//		changePassword();
	}
	
	
// Unary RPC - Login to account
	
	public static void login() {
		
		Scanner in = new Scanner(System.in);
		String username;
		String password;
		
		System.out.println("Client >>>>>>>>> Requesting login...");
		System.out.println("Client >>>>>>>>> Enter username (Amy): ");
		username = in.next();
		System.out.println("Client >>>>>>>>> Enter password (123): ");
		password = in.next();
		
		
		LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder()
				.setUsername(username)
				.setPassword(password)
				.build());
		
		System.out.println(response.getMessage());
				
	}
	     

// Server-streaming RPC - View account info
	
	public static void viewAccount() {
		
		Scanner in = new Scanner(System.in);
		int accountNo;
		
		System.out.println("Client >>>>>>>>> Enter account number to view (1, 2 or 3): ");
		accountNo = in.nextInt();

		ViewRequest request = ViewRequest.newBuilder()
				.setAccNo(accountNo)
				.build();


		StreamObserver<AccountInfo> responseObserver = new StreamObserver<AccountInfo>() {

			@Override
			public void onNext(AccountInfo value) {
				System.out.println("Client >>>>>>>>> Recieveing account infomation for Account No: " + accountNo);
				System.out.println("Client >>>>>>>>> Account Info: " + value.getMessage());
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Client >>>>>>>>> Stream is completed ");
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
	
	  
//BI DIRECTIONAL - REUQEST MONEY	    
//	   
//	    public static void request() {
//
//			StreamObserver<RequestStatus> responseObserver = new StreamObserver<RequestStatus>() {
//
//				@Override
//				public void onNext(RequestStatus response) {
//					System.out.println("Client >>>>>>>>> Requesting status: "+ response.getStatus());
//				}
//
//				@Override
//				public void onError(Throwable t) {
//					t.printStackTrace();
//
//				}
//
//				@Override
//				public void onCompleted() {
//					System.out.println("Client >>>>>>>>> END OF STREAM: Money request completed");
//				}
//
//			};
//
//
//
//			StreamObserver<RequestSum> requestObserver = asyncStub.request(responseObserver);
//
//			try {
//
//				requestObserver.onNext(RequestSum.newBuilder()
//						.setSum(10)
//						.setFromAccNo(1)
//						.setToAccNo(2)
//						.build());
//				
//				requestObserver.onNext(RequestSum.newBuilder()
//						.setSum(20)
//						.setFromAccNo(2)
//						.setToAccNo(1)
//						.build());
//				
//				requestObserver.onNext(RequestSum.newBuilder()
//						.setSum(30)
//						.setFromAccNo(2)
//						.setToAccNo(1)
//						.build());
//
//				// Mark the end of requests
//				requestObserver.onCompleted();
//
//
//				// Sleep for a bit before sending the next one.
//				Thread.sleep(new Random().nextInt(1000) + 500);
//
//
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {			
//				e.printStackTrace();
//			}
//
//
//
//			try {
//				Thread.sleep(15000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}	
}	

	   

