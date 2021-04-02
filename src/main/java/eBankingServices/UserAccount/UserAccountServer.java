package eBankingServices.UserAccount;

import java.io.IOException;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import eBankingServices.UserAccount.UserAccountGrpc.UserAccountImplBase;
import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.LoginConfirmation;
//import eBankingServices.UserAccount.ViewRequest;
//import eBankingServices.UserAccount.AccountInfo;
//import eBankingServices.UserAccount.PasswordRequest;
//import eBankingServices.UserAccount.PasswordConfirmation;


public class UserAccountServer extends UserAccountImplBase {


	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserAccountServer userAccounts = new UserAccountServer();

		int port = 50051;

		Server server = ServerBuilder.forPort(port).addService(userAccounts)
				.build()
				.start();

		System.out.println("User Account server started, listening on " + port);

		server.awaitTermination();
	}

// login to account unary rpc


	public void login(LoginRequest request, StreamObserver<LoginConfirmation> responseObserver) {

		

				if (authenticateUser(request.getUsername(), request.getPassword())) {
						
				LoginConfirmation lc = LoginConfirmation.newBuilder()	
				.setMessage("Sever >>>>>>>>> Username and Password Correct! Welcome "+ request.getUsername())
				.build();
				
				responseObserver.onNext(lc);
				
				} else {
					
					System.out.println("Sever >>>>>>>>> Username or Password Incorrect!" );	
				}

		responseObserver.onCompleted();
	}

	
// transfer money client streaming rpc


//	public StreamObserver<TransferSum> transfer(StreamObserver<TransferConfirmation> responseObserver) {
//		
//		String euro = "\u20ac";
//				
//		return new StreamObserver<TransferSum>() {
//			
//			 ArrayList<Object> acc = new ArrayList<Object>(Arrays.asList(accounts)); 
//		
//				@Override
//			public void onNext(TransferSum request) {
//			
//				
//				if (transferSum(request.getToAccNo(), request.getFromAccNo(), request.getSum())) {
//					System.out.println("Server >>>>>>>>> SUCCESS " + euro + request.getSum() + " transferred to AccountNo.  "+ request.getToAccNo());
//						
//				} else {
//					System.out.println("Server >>>>>>>>> FAILED not enough funds in AccountNo. " + request.getFromAccNo());
//				}
//				
//			}
//				
//			@Override
//			public void onCompleted() {
//
//				System.out.println("Server >>>>>>>>> Transaction process completed" );		
//			}
//			
//			@Override
//			public void onError(Throwable t) {
//				// TODO Auto-generated method stub
//			}
//		};
//	}
//	@Override
//	public StreamObserver<RequestSum> request(StreamObserver<RequestStatus> responseObserver) {
//		
//		String euro = "\u20ac";
//		
//		return new StreamObserver<RequestSum> () {
//
//			@Override
//			public void onNext(RequestSum request) {
//				System.out.println("Server >>>>>>>>> Receiving money request:  AccountNo. " + request.getToAccNo() + " is requesting " + euro + request.getSum() + " from AccountNo. "+ request.getFromAccNo());
//				
//				String status =  "Server status >>>>>>>>> Money request is pending...";
//				
//				RequestStatus reply = RequestStatus.newBuilder()
//						.setStatus(status)
//						.build();
//				
//				responseObserver.onNext(reply);
//				
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				
//				t.printStackTrace();
//
//			}
//
//			@Override
//			public void onCompleted() {
//				System.out.println("Server >>>>>>>>> Receiving money request completed ");
//				
//				//completed too
//				responseObserver.onCompleted();
//			}
//			
//		};
//}

	private boolean authenticateUser(String username, String password) {
		if (username.equals("Amy") && password.equals("123")) {
			return true;
		} else {
			return false;
		}
	}
}

