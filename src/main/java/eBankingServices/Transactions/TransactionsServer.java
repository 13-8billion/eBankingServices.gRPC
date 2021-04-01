package eBankingServices.Transactions;

import java.io.IOException;

import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.TransactionsServer;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsImplBase;
import eBankingServices.Transactions.TransactionStatus;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class TransactionsServer extends TransactionsImplBase{

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// initiate server
		TransactionsServer transactions = new TransactionsServer();
		
		int port = 50051;

		Server server = ServerBuilder.forPort(port)
				.addService(transactions)
				.build()
				.start();

		System.out.println("Transaction server started, listening on " + port);

		server.awaitTermination();	
	}
	
	@Override
	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {
		DepositConfirmation dc;

		// simulate "Bank process" 
		if (depositSum(request.getAccNo(), request.getSum())) {
			dc = DepositConfirmation.newBuilder()
					.setMessage("Money deposited successfully")
					.setStatus(TransactionStatus.SUCCESS)
					.build();
		} else {
			dc = DepositConfirmation.newBuilder()
					.setMessage("Not enough funds in bank account")
					.setStatus(TransactionStatus.FAILED)
					.build();
		}

		responseObserver.onNext(dc);
		responseObserver.onCompleted();
	}

	
	
	
	
}
