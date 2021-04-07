package eBankingServices.UserTools;

import java.util.Random;
import java.util.Scanner;

import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
//import eBankingServices.Transactions.RequestStatus;
//import eBankingServices.Transactions.RequestSum;
//import eBankingServices.UserTools.UserToolsGrpc;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsBlockingStub;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UserToolsClient {
	
	private static UserToolsBlockingStub blockingStub;
	private static UserToolsStub asyncStub;

	
	public static void main(String args[]) throws InterruptedException {
		
		final ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50053)
				.usePlaintext()
				.build();
		
		//stubs -- generate from .proto
		blockingStub = UserToolsGrpc.newBlockingStub(channel);
		asyncStub = UserToolsGrpc.newStub(channel);
		
		// call methods in transactions client class
//		helpBot();
		vault();
//		interestCalc();
	}
	
// HelpBot method - Bi-directional streaming gRPC
	public static void helpBot() {

		StreamObserver<HelpResponse> responseObserver = new StreamObserver<HelpResponse>() {

			@Override
			public void onNext(HelpResponse response) {
				System.out.println("Client >>>>>>>>> "+ response.getSolution());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Client >>>>>>>>> END OF STREAM: HelpBot offline");
			}

		};

		StreamObserver<HelpRequest> requestObserver = asyncStub.helpBot(responseObserver);

		try {
			

			requestObserver.onNext(HelpRequest.newBuilder()
					.setProblemID(1)
					.build());
			Thread.sleep(2000);
			

			requestObserver.onNext(HelpRequest.newBuilder()
					.setProblemID(2)
					.build());
			Thread.sleep(2000);

			
			requestObserver.onNext(HelpRequest.newBuilder()
					.setProblemID(3)
					.build());
			Thread.sleep(2000);
			
			requestObserver.onNext(HelpRequest.newBuilder()
					.setProblemID(4)
					.build());
			Thread.sleep(2000);

			// Mark the end of requests
			requestObserver.onCompleted();


			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);


		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}


		try {
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
// Vault method - Unary gRPC

	public static void vault() {
		
		Scanner in = new Scanner(System.in);
		String username;
		String password;
		int accNo;
		int sum;
		String unlockDate;
	
		System.out.println("Client >>>>>>>>> Requesting to access vault...");
		System.out.println("Enter username (Amy): ");
		username = in.next();
		System.out.println("Enter password (123): ");
		password = in.next();
		System.out.println("Enter Account No (1, 2 or 3): ");
		accNo = in.nextInt();
		System.out.println("Enter sum to store: ");
		sum = in.nextInt();
		System.out.println("Enter unlock date (dd-mm-yyyy): ");
		unlockDate = in.next();
		
		VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
				.setUsername(username)
				.setPassword(password)
				.setAccNo(accNo)
				.setSum(sum)
				.setVaultID(1)
				.setUnlockDate(unlockDate)
				.build());
		
		System.out.println(response);
	}
	
}
