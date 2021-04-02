package eBankingServices.Transactions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import eBankingServices.Transactions.RequestStatus;
import eBankingServices.Transactions.RequestSum;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
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

// deposit money to account unary rpc


	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {

		DepositConfirmation dc = DepositConfirmation.newBuilder()

				.setMessage("From Sever: DepositID. " + request.getDepositID() + ": Euro " + request.getSum() + " has been deposited successfully into account no. " + request.getAccNo())
				.build();

		responseObserver.onNext(dc);
		responseObserver.onCompleted();
	}

	
// transfer money client streaming rpc


	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {
		
		String euro = "\u20ac";
				
		return new StreamObserver<TransferSum>() {
			
			 ArrayList<Object> acc = new ArrayList<Object>(Arrays.asList(accounts)); 
		
				@Override
			public void onNext(TransferSum request) {
			
				
				if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
					System.out.println("Server >>>>>>>>> Transfer request SUCCESS " + euro + request.getSum() + " transferred to AccountNo.  "+ request.getToAccNo());
						
				} else {
					System.out.println("Server >>>>>>>>> Transfer request FAILED not enough funds in AccountNo. " + request.getFromAccNo());
				}
				
			}
				
			@Override
			public void onCompleted() {

				System.out.println("Server >>>>>>>>> Transaction process completed" );		
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
			}
		};
	}
	@Override
	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {
		
		String euro = "\u20ac";
		
		return new StreamObserver<RequestSum> () {

			@Override
			public void onNext(RequestSum request) {
				System.out.println("Server >>>>>>>>> Receiving money request:  AccountNo. " + request.getToAccNo() + " is requesting " + euro + request.getSum() + " from AccountNo. "+ request.getFromAccNo());
				
				String status =  "Server status >>>>>>>>> Money request is pending...";
				
				RequestStatus reply = RequestStatus.newBuilder()
						.setStatus(status)
						.build();
				
				responseObserver.onNext(reply);
				
			}

			@Override
			public void onError(Throwable t) {
				
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Server >>>>>>>>> Receiving money request completed ");
				
				//completed too
				responseObserver.onCompleted();
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
