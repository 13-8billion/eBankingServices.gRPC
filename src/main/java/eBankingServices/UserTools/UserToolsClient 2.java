package eBankingServices.UserTools;

import java.util.Random;
import java.util.Scanner;

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
		helpBot();
		vault();
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
					.build());
			Thread.sleep(2000); // simulating wait time
			

			requestObserver.onNext(HelpRequest.newBuilder()
					.build());
			Thread.sleep(2000);

			
			requestObserver.onNext(HelpRequest.newBuilder()
					.build());
			Thread.sleep(2000);
			
			requestObserver.onNext(HelpRequest.newBuilder()
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
	
// Vault method - Unary

	public static void vault() {

		VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
				.build());
		
		System.out.println(response);
	}
	
// Interest calculator method - Unary
	
	public static void interestCalc() {
		
		CalcResponse response = blockingStub.interestCalc(CalcRequest.newBuilder()
				.build());

		System.out.println("Total payable " + response);
	}
	
}
