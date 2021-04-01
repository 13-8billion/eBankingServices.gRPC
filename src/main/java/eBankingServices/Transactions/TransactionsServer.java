package eBankingServices.Transactions;

import java.io.IOException;

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
	private double[] accounts = {0, 300, 3000 };

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

	@Override
	public void deposit(DepositSum request, StreamObserver<DepositConfirmation> responseObserver) {

		DepositConfirmation dc = DepositConfirmation.newBuilder()

				.setMessage("Sever: Money has deposited successfully")
				.build();

		responseObserver.onNext(dc);
		responseObserver.onCompleted();
	}

// transfer money client streaming method 

	@Override
	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {

		return new StreamObserver<TransferSum>() {

			@Override
			public void onNext(TransferSum request) {

				System.out.println("Server: Receiving money..." + request.getSum());
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
			}

			public void onCompleted(TransferSum request) {

				if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
					System.out.printf("Server: Money transferred successfully");
				} else {
					System.out.printf("Server: Transaction failed, not enough funds");

					TransferConfirmation tc = TransferConfirmation.newBuilder()
							.setTransferConf("Server: TRANSFER CONFIRMED")
							.build();

					responseObserver.onNext(tc);

					responseObserver.onCompleted();
				}
			}

			@Override
			public void onCompleted() {
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
