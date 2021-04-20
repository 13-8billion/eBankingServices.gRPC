package eBankingServices.Transactions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;

import java.io.IOException;
import java.util.Random;

public class TransactionsClient {

	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;
	private static ManagedChannel channel;

	private static final Logger logger = Logger.getLogger(TransactionsClient.class.getName());

	public static void main(String args[]) throws InterruptedException, IOException {

		channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();
		// stubs -- generated from .proto files
		blockingStub = TransactionsGrpc.newBlockingStub(channel);
		asyncStub = TransactionsGrpc.newStub(channel);

		// call methods
//		deposit();
//		transfer();
		request();	

//		channel.shutdown()
//	 	   .awaitTermination(60, TimeUnit.SECONDS);
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

// Transfer money between accounts method - Client-streaming RPC

	public static void transfer() {

		StreamObserver<TransferConfirmation> responseObserver = new StreamObserver<TransferConfirmation>() {

			@Override
			public void onNext(TransferConfirmation response) {
//					
				System.out.println("Getting confirmation... " + response.getMessage());
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
			
			requestObserver.onNext(TransferSum.newBuilder().setSum(10).setFromAccNo(2).setToAccNo(1).build());
			Thread.sleep(1000);


			Thread.sleep(2000);

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
			requestObserver.onNext(RequestSum.newBuilder().setSum(10).setFromAccNo(3).setToAccNo(2).setMonthly(false)
					.setApprove(true).build());
			Thread.sleep(500);

			requestObserver.onNext(RequestSum.newBuilder().setSum(20).setFromAccNo(2).setToAccNo(1).setMonthly(true)
					.setApprove(false).build());
			Thread.sleep(500);

			requestObserver.onNext(RequestSum.newBuilder().setSum(30).setFromAccNo(2).setToAccNo(1).setMonthly(false)
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
