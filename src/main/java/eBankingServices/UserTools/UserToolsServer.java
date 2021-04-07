package eBankingServices.UserTools;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsImplBase;
//import eBankingServices.UserTools.HelpRequest;
//import eBankingServices.UserTools.HelpRequest.Solutions;
//import eBankingServices.UserTools.HelpResponse;
//import eBankingServices.UserTools.VaultAccess;
//import eBankingServices.UserTools.VaultConfirmation;
//import eBankingServices.UserTools.CalcRequest;
//import eBankingServices.UserTools.CalcResponse;


public class UserToolsServer extends UserToolsImplBase {
	

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserToolsServer userTools = new UserToolsServer();

		int port = 50053;

		Server server = ServerBuilder.forPort(port).addService(userTools)
				.build()
				.start();

		System.out.println("User Account server started, listening on " + port);

		server.awaitTermination();
	}

// HelpBot method - bi-directional streaming gRPC
	
	public StreamObserver<HelpRequest> helpBot(StreamObserver<HelpResponse> responseObserver) {
		
		Scanner in = new Scanner(System.in);
		
	
		return new StreamObserver<HelpRequest> () {
			
			@Override
			public void onNext(HelpRequest request) {
				
			try {
				int id = request.getProblemID();

				System.out.println("Server >>>>>>>>> Welcome to HelpBot. Please select the number of your problem below:");
				System.out.println("Reset password enter 1: ");
				System.out.println("Report a bug enter 2: ");
				System.out.println("Help with Vaults enter 3: ");
				System.out.println("Help with payments enter 4: ");
				Thread.sleep(7000);
				
//				id = in.nextInt();
				
				System.out.println("Server >>>>>>>>> You have selected Problem No: " + id + " please find solutions on next screen.");
				Thread.sleep(2000); 
				
				String solution = null;

				if(id == 1) //request.getSolutions()==Solutions.PASSWORD_RESET)
				{
					solution = "Problem ID. 1 Solution = To reset your password, navigate to your account and under settings select 'change password' and follow the instructions";			
				}
				else if(id == 2)// request.getSolutions()==Solutions.REPORT_BUG
				{								
					solution = "Problem ID. 2 Solution = To report a bug please send an email to our 24 hour help assist at: eBankingServices.gRPC@nci.ie";
				}
				else if(id == 3) // request.getSolutions()==Solutions.VAULTS
				{
					solution = "Problem ID. 3 Solution = To use our vault service simply navigate to the vaults under the 'user tools' menu, select the amount of money you wish to store and select a date for when the vault can be reopened.";
				}
				else if(id == 4) // request.getSolutions()==Solutions.PAYMENTS
				{
					solution = "Problem ID. 4 Solution = If you are experiencing issues making or recieveing payment please call our free 24 hour hotline at: 0800-03041992.";
				}
				else if (id != 1 || id != 2 || id !=3 | id !=4){

					solution = "You have selected an invalid option. Please select from the list";
				}		

				HelpResponse reply = HelpResponse.newBuilder()
						.setSolution(solution)
						.build();
				
				responseObserver.onNext(reply);
				Thread.sleep(1000);
				

				// Sleep for a bit before sending the next one.
				Thread.sleep(new Random().nextInt(1000) + 500);


			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}

			@Override
			public void onError(Throwable t) {
				
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println();
				
				//completed too
				responseObserver.onCompleted();
			}
			
		};
	}
	
}
