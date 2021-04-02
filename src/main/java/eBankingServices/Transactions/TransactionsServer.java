package eBankingServices.Transactions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.Transactions.TransferSum;
import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferSum;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsImplBase;

public class TransactionsServer extends TransactionsImplBase {

	// hard code some account balances
	private double[] accounts = {0, 3000, 30000};

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		TransactionsServer transactions = new TransactionsServer();

		int port = 50051;

		Server server = ServerBuilder.forPort(port).addService(transactions)
				.build()
				.start();

		System.out.println("Transaction server started, listening on " + port);

		server.awaitTermination();
	}

// deposit money to account unary method 


	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {

		DepositConfirmation dc = DepositConfirmation.newBuilder()

				.setMessage("From Sever: DepositID. " + request.getDepositID() + ": Euro " + request.getSum() + " has been deposited successfully into account no. " + request.getAccNo())
				.build();

		responseObserver.onNext(dc);
		responseObserver.onCompleted();
	}

// transfer money client streaming method 


	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {
				
		return new StreamObserver<TransferSum>() {
			
			 ArrayList<Object> acc = new ArrayList<Object>(Arrays.asList(accounts)); 

			 
				@Override
				public void onCompleted() {

					System.out.println("Server >>>>>>>>> Transaction process completed" );		
				}
		
				@Override
			public void onNext(TransferSum request) {
			
				
				if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
					System.out.println("Server >>>>>>>>> SUCCESS €" + request.getSum() + " transferred to AccountNo.  "+ request.getToAccNo());
						
				} else {
					System.out.println("Server >>>>>>>>> FAILED not enough funds in AccountNo. " + request.getFromAccNo());
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
			}
		};
	}

	private boolean transferSum(int toAccNo, int fromAccNo, double sum) {
		if (accounts[fromAccNo] < sum) {
			return false;
		} else {
			accounts[fromAccNo] -= sum;
			accounts[toAccNo] += sum;
			return true;
		}
	}
}
