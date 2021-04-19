package eBankingServices.UserTools;

import java.util.Random;
import java.util.Scanner;

import eBankingServices.UserTools.HelpRequest.Operation;
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
				.forAddress("localhost", 50054)
				.usePlaintext()
				.build();
		
		//stubs -- generated from .proto file
		blockingStub = UserToolsGrpc.newBlockingStub(channel);
		asyncStub = UserToolsGrpc.newStub(channel);
		
		// call methods 
//		helpBot();
//		vault();
		interestCalc();
	}
	
// HelpBot method - Bi-directional streaming
	public static void helpBot() {

		StreamObserver<HelpResponse> responseObserver = new StreamObserver<HelpResponse>() {

			@Override
			public void onNext(HelpResponse response) {
				System.out.println(response.getSolution());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("END OF STREAM: HelpBot offline");
			}

		};

		StreamObserver<HelpRequest> requestObserver = asyncStub.helpBot(responseObserver);

		try {
			
			// simulating multiple help requests to the help bot server
			requestObserver.onNext(HelpRequest.newBuilder()
					.setMessage("How do I reset my password?")
					.setOperation(Operation.PASSWORD_RESET)
					.build());
			Thread.sleep(3000); // simulating wait time
			

			requestObserver.onNext(HelpRequest.newBuilder()
					.setMessage("How do I report a bug?")
					.setOperation(Operation.REPORT_BUG)
					.build());
			Thread.sleep(3000);

			
			requestObserver.onNext(HelpRequest.newBuilder()
					.setMessage("How do I use Vaults?")
					.setOperation(Operation.VAULTS)
					.build());
			Thread.sleep(3000);
			
			requestObserver.onNext(HelpRequest.newBuilder()
					.setMessage("How do I send and receieve payments?")
					.setOperation(Operation.PAYMENTS)
					.build());
			Thread.sleep(3000);

			// Mark the end of requests
			requestObserver.onCompleted();



		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}


		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
// Vault method - Unary

	public static void vault() {

		VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
				.setUsername("Amy")
				.setPassword("123")
				.setAccNo(1)
				.setSum(100)
				.setUnlockDate("03/04/2021")
				.build());
		
		System.out.println(response);
	}
	
// Interest calculator method - Unary
	
	public static void interestCalc() {
		
		CalcResponse response = blockingStub.interestCalc(CalcRequest.newBuilder()
				.setAccess("yes")
				.setAccType("12")
				.setSum(30000)
				.build());

		System.out.println("Total payable " + response);
	}
	
}
