package eBankingServices.UserTools;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferSum;
import eBankingServices.UserTools.HelpRequest.Operation;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsBlockingStub;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UserToolsClient {

	private static UserToolsBlockingStub blockingStub;
	private static UserToolsStub asyncStub;
	private static ServiceInfo userToolsServiceInfo;
	private static String newline = "\n\r";

	public static void main(String args[]) throws InterruptedException {

		UserToolsClient obj = new UserToolsClient();

		String userTools_service_type = "_userTools._tcp.local.";

		// discover user tools service
		obj.discoverUserToolsService(userTools_service_type);

		String host = userToolsServiceInfo.getHostAddresses()[0];
		int port = userToolsServiceInfo.getPort();

		final ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// stubs -- generated from .proto file
		blockingStub = UserToolsGrpc.newBlockingStub(channel);
		asyncStub = UserToolsGrpc.newStub(channel);

		// call methods
//		helpBot();
//		vault();
		interestCalc();
	}

	private void discoverUserToolsService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("USER TOOLS Service resolved: " + event.getInfo());

					userToolsServiceInfo = event.getInfo();

					int port = userToolsServiceInfo.getPort();

					String host = userToolsServiceInfo.getHostAddresses()[0];

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + userToolsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + host);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("USER TOOLS Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("USER TOOLS Service added: " + event.getInfo());
					jmdns.requestServiceInfo(event.getType(), event.getName());

				}
			});

			// Wait a bit
			Thread.sleep(500);
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			requestObserver.onNext(HelpRequest.newBuilder().setMessage("How do I reset my password?")
					.setOperation(Operation.PASSWORD_RESET).build());
			Thread.sleep(3000); // simulating wait time

			requestObserver.onNext(HelpRequest.newBuilder().setMessage("How do I report a bug?")
					.setOperation(Operation.REPORT_BUG).build());
			Thread.sleep(3000);

			requestObserver.onNext(
					HelpRequest.newBuilder().setMessage("How do I use Vaults?").setOperation(Operation.VAULTS).build());
			Thread.sleep(3000);

			requestObserver.onNext(HelpRequest.newBuilder().setMessage("How do I send and receieve payments?")
					.setOperation(Operation.PAYMENTS).build());
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

		VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder().setUsername("Amy").setPassword("123")
				.setAccNo(1).setSum(100).setUnlockDate("03/04/2022").build());

		System.out.println(response);
	}

// Interest calculator method - Unary

//	public static void interestCalc() {
//
//		CalcResponse response = blockingStub
//				.interestCalc(CalcRequest.newBuilder().setAccess("no").setAccType("24").setSum(30000).build());
//
//		System.out.println("Total payable " + response);
//	}

	// --------------------------- CLIENT STREAMING
//	public static void interestCalc() {
//
//		StreamObserver<CalcResponse> responseObserver = new StreamObserver<CalcResponse>() {
//
//			@Override
//			public void onNext(CalcResponse response) {
//
//				System.out.println("Client >>> Calculating interest... ");
//				System.out.println("Client >>> Interest: " + response.getError());
//
//			}
//
//			@Override
//			public void onError(Throwable t) {
//
//			}
//
//			@Override
//			public void onCompleted() {
//				System.out.println("STREAM END: All transfers have completed.");
//
//			}
//		};
//
//		StreamObserver<CalcRequest> requestObserver = asyncStub.interestCalc(responseObserver);
//		try {
//
//			requestObserver.onNext(CalcRequest.newBuilder().setAccess("no").setAccType("24").setSum(30000).build());
//			Thread.sleep(500);
//
//			requestObserver.onNext(CalcRequest.newBuilder().setAccess("yes").setAccType("24").setSum(30000).build());
//			Thread.sleep(500);
//
//			Thread.sleep(2000);
//
//			requestObserver.onCompleted();
//
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	};
//
//}
	
	public static void interestCalc() {

		StreamObserver<CalcResponse> responseObserver = new StreamObserver<CalcResponse>() {

			@Override
			public void onNext(CalcResponse msg) {
				System.out.println("Interest: " + msg.getMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("STREAM END: Calculating completed.");
			}

		};


		StreamObserver<CalcRequest> requestObserver = asyncStub.interestCalc(responseObserver);
		try {
			requestObserver.onNext(CalcRequest.newBuilder().setAccess("no").setAccType("24").setSum(30000).build());
			Thread.sleep(500);

			requestObserver.onNext(CalcRequest.newBuilder().setAccess("yes").setAccType("24").setSum(30000).build());
			Thread.sleep(500);


			// Mark the end of requests
			requestObserver.onCompleted();

			
			Thread.sleep(10000);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}


	}
}
