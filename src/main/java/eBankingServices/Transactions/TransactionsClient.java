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
		System.out.println("Client >>>>>>>>> Deposit money request #1 ");
		DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder()
				.setAccNo(1)
				.setSum(33)
				.build());
		
		System.out.println(response);

	
		System.out.println("Client >>>>>>>>> Deposit money request #2");
		response = blockingStub.deposit(DepositSum.newBuilder()
				.setAccNo(1)
				.setSum(100)
				.build());
		
		System.out.println(response);
		
//	    channel.shutdown()
//	    	   .awaitTermination(5, TimeUnit.SECONDS);
				
	}
	      
	    // Client-streaming RPC - Transfer money between accounts
	    public static void transfer() throws InterruptedException {
	    	
	  
			StreamObserver<TransferConfirmation> responseObserver = new StreamObserver<TransferConfirmation>() {

				@Override
				public void onNext(TransferConfirmation msg) {
					
					System.out.println("Client >>>>>>>>> transferring money.. " + msg.getTransferConf());
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
				}

				@Override
				public void onCompleted() {
					System.out.println("Client >>>>>>>>>  Stream is completed ... money transferred");
				}

			};
			
//			StreamObserver<TransferSum> requestObserver = asyncStub.transfer(responseObserver);
//			
//			requestObserver.onNext(TransferSum.newBuilder()
//					.setSum(100)
//					.setFromAccNo(1)
//					.setToAccNo(2)
//					.build());
//			Thread.sleep(500);
//			
//			System.out.println(requestObserver);
//			responseObserver.onCompleted();	
//			
//			Thread.sleep(10000);
//			
//	
			//THIS CODE "WORRKS"
			StreamObserver<TransferSum> res = asyncStub.transfer(TransferSum.newBuilder()
						.setSum(100)
						.setFromAccNo(1)
						.setToAccNo(2)
						.build());
				Thread.sleep(500);
				
				System.out.println(res);
				
			res = asyncStub.transfer(TransferSum.newBuilder()
						.setSum(200)
						.setFromAccNo(1)
					.setToAccNo(2)
						.build());
				Thread.sleep(500);
				
				System.out.println(res);
					
				responseObserver.onCompleted();	
				
			Thread.sleep(10000);
	    	};
	    
		}	

	   

