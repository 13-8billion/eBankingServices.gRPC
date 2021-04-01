package eBankingServices.Transactions;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;

public class TransactionsClient {
	
	public static void main(String args[]) throws InterruptedException {
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
				.usePlaintext(true)
				.build();
		
		// Blocking client
		TransactionsBlockingStub client = TransactionsGrpc.newBlockingStub(channel);
		
		System.out.println(">>>>>>>>> Sending Deposit Request #1");
		DepositConfirmation response = client.deposit(DepositSum.newBuilder()
				.setAccNo(1)
				.setSum(33.5)
				.build());
		
		System.out.println(response);

	
		System.out.println(">>>>>>>>> Sending Deposit Request #2");
		response = client.deposit(DepositSum.newBuilder()
				.setAccNo(1)
				.setSum(100)
				.build());
		
		System.out.println(response);
		
	    channel.shutdown()
	    	   .awaitTermination(5, TimeUnit.SECONDS);
	}

}

