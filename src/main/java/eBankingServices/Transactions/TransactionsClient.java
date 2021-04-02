package eBankingServices.Transactions;

import java.util.concurrent.TimeUnit;

import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferSum;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;

import java.util.Scanner;


public class TransactionsClient {
	
	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;

	
	public static void main(String args[]) throws InterruptedException {
		
		final ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 50051)
				.usePlaintext()
				.build();
		
		//stubs -- generate from .proto
		blockingStub = TransactionsGrpc.newBlockingStub(channel);
		asyncStub = TransactionsGrpc.newStub(channel);
		
		deposit();
		transfer();
	}
	
	public static void deposit() {
		
		// Unary RPC - Deposit money into account
		System.out.println("Client >>>>>>>>> Requesting to deposit money...");
		DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder()
				.setAccNo(1)
				.setSum(33)
				.setDepositID(1)
				.build());
		
		System.out.println(response);

	
		System.out.println("Client >>>>>>>>> Requesting to deposit money...");
		response = blockingStub.deposit(DepositSum.newBuilder()
				.setAccNo(2)
				.setSum(100)
				.setDepositID(2)
				.build());
		
		System.out.println(response);
	
//		
//	    channel.shutdown()
//	    	   .awaitTermination(5, TimeUnit.SECONDS);
				
	}
	      
	    // Client-streaming RPC - Transfer money between accounts
	    public static void transfer() {  	
	  
			StreamObserver<TransferConfirmation> responseObserver = new StreamObserver<TransferConfirmation>() {

				@Override
				public void onNext(TransferConfirmation response) {
					
					System.out.println("Client >>>>>>>>> getting confirmation " + response.getMessage());
					
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
				}

				@Override
				public void onCompleted() {
					System.out.println("Client >>>>>>>>> STREAM END: All transfers have completed.");
				}
				
			};
			
			StreamObserver<TransferSum> requestObserver = asyncStub.transfer(responseObserver);
			try {
			requestObserver.onNext(TransferSum.newBuilder()
					.setSum(30)
					.setFromAccNo(1)
					.setToAccNo(2)
					.build());
			Thread.sleep(1000);
			
			requestObserver.onNext(TransferSum.newBuilder()
					.setSum(300)
					.setFromAccNo(2)
					.setToAccNo(1)
					.build());
			Thread.sleep(1000);
			
			requestObserver.onNext(TransferSum.newBuilder()
					.setSum(300)
					.setFromAccNo(0)
					.setToAccNo(1)
					.build());
			Thread.sleep(1000);
						
			Thread.sleep(2000);
			
			responseObserver.onCompleted();	
		
			
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
			
	    };
	}	

	   

