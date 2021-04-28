 package eBankingServices.Transactions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class TransactionsClient {

	private static ServiceInfo transactionsServiceInfo;
	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;
	private static ManagedChannel channel;
	private static String newline = "\n\r";

	private static final Logger logger = Logger.getLogger(TransactionsClient.class.getName());

	public static void main(String args[]) throws InterruptedException, IOException {

		String transactions_service_type = "_transactions._tcp.local.";

		TransactionsClient obj = new TransactionsClient();
		// discover transactions service
		obj.discoverTransactionsService(transactions_service_type);

		String host = transactionsServiceInfo.getHostAddresses()[0];
		int port = transactionsServiceInfo.getPort();

		channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		// stubs -- generated from .proto files
		blockingStub = TransactionsGrpc.newBlockingStub(channel);
		asyncStub = TransactionsGrpc.newStub(channel);

		// call methods
		deposit();
		transfer();
		request();	

//		channel.shutdown()
//	 	   .awaitTermination(60, TimeUnit.SECONDS);
	}
	
	private void discoverTransactionsService(String service_type){

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("TRANSACTION Service resolved: " + event.getInfo());

					transactionsServiceInfo = event.getInfo();

					int port = transactionsServiceInfo.getPort();

					String host = transactionsServiceInfo.getHostAddresses()[0];

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + transactionsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + host);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("TRANSACTION Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("TRANSACTION Service added: " + event.getInfo());
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
	

// Deposit money into account method - Unary RPC

	public static void deposit() throws InterruptedException {

		System.out.println("Requesting to deposit...");
		DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder().setAccNo(1).setSum(33).build());

		System.out.println(response);

		System.out.println("Requesting to deposit...");
		response = blockingStub.deposit(DepositSum.newBuilder().setAccNo(2).setSum(22).build());

		System.out.println(response);

	}

// Transfer money between accounts method - bi-directional-streaming RPC

	public static void transfer() {

		StreamObserver<TransferConfirmation> responseObserver = new StreamObserver<TransferConfirmation>() {

			@Override
			public void onNext(TransferConfirmation response) {
					
				System.out.println("Getting confirmation... " + newline + response.getConf());
				
			}
	
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				try {
					channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			@Override
			public void onCompleted() {
				System.out.println("STREAM END: All transfers have completed.");
//					try {
//						channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}

			}
		};

		StreamObserver<TransferSum> requestObserver = asyncStub.transfer(responseObserver);
		try {
			
			requestObserver.onNext(TransferSum.newBuilder().setSum(10).setFromAccNo(3).setToAccNo(1).build());
			Thread.sleep(1000);

			requestObserver.onNext(TransferSum.newBuilder().setSum(100).setFromAccNo(2).setToAccNo(2).build());
			Thread.sleep(1000);
			
			requestObserver.onNext(TransferSum.newBuilder().setSum(1000).setFromAccNo(1).setToAccNo(3).build());
			Thread.sleep(1000);
			
			Thread.sleep(10000);

			requestObserver.onCompleted();

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

// Request money from other accounts - Bi-directional streaming 

	public static void request() {

		StreamObserver<RequestStatus> responseObserver = new StreamObserver<RequestStatus>() {

			@Override
			public void onNext(RequestStatus response) {
				System.out.println("Requesting status: " + response.getStatus());
//				System.out.println(response.getMessage());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				try {
					channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onCompleted() {
				System.out.println("END OF STREAM: Money request completed");
				try {
					channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

		StreamObserver<RequestSum> requestObserver = asyncStub.request(responseObserver);

		try {
			// simulating multiple requests
			requestObserver.onNext(RequestSum.newBuilder().setSum(10).setFromAccNo(1).setToAccNo(2).setMonthly(false)
					.setApprove(true).build());
			Thread.sleep(500);

			requestObserver.onNext(RequestSum.newBuilder().setSum(20).setFromAccNo(5).setToAccNo(1).setMonthly(true)
					.setApprove(false).build());
			Thread.sleep(500);

			requestObserver.onNext(RequestSum.newBuilder().setSum(30).setFromAccNo(3).setToAccNo(1).setMonthly(false)
					.setApprove(true).build());
			Thread.sleep(500);

			// Mark the end of requests
			requestObserver.onCompleted();

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
