package eBankingServices.UserTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.UserTools.HelpRequest.Operation;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsImplBase;

public class UserToolsServer extends UserToolsImplBase {
	
	String euro = "\u20AC";
	
	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserToolsServer userTools = new UserToolsServer();
		
		Properties prop = userTools.getProperties();
		
		// register the service 
		userTools.registerService(prop);

		int port = Integer.valueOf( prop.getProperty("service_port") );
	
		try {
				
			Server server = ServerBuilder.forPort(port).addService(userTools)
					.build()
					.start();
	
			System.out.println("User Account server started, listening on " + port);
	
			server.awaitTermination();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// register service method 
	
	private void registerService(Properties prop) {
		
		try {
	         // Create a JmDNS instance
	         JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	         
	         String service_type = prop.getProperty("service_type");
	         String service_name = prop.getProperty("service_name")  ;

	         int service_port = Integer.valueOf( prop.getProperty("service_port") );
	         
	         String service_description_properties = prop.getProperty("service_description")  ;//"path=index.html";
	         
	         // Register a service
	         ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	         jmdns.registerService(serviceInfo);
	         
	         System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
	         
	         // Wait a bit
	         Thread.sleep(1000);

	         // Unregister all services
	         //jmdns.unregisterAllServices();

	     } catch (IOException e) {
	         System.out.println(e.getMessage());
	     } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	// get service properties method
	
	private Properties getProperties() {
		
		Properties prop = null;		
		
		 try (InputStream input = new FileInputStream("src/main/resources/UserTools.properties")) {

	           prop = new Properties();

	           // load a properties file
	           prop.load(input);

	           // get the property value and print it out
	           System.out.println("User Tools Service properies ...");
	           System.out.println("\t service_type: " + prop.getProperty("service_type"));
	           System.out.println("\t service_name: " +prop.getProperty("service_name"));
	           System.out.println("\t service_description: " +prop.getProperty("service_description"));
		       System.out.println("\t service_port: " +prop.getProperty("service_port"));

	       } catch (IOException ex) {
	           ex.printStackTrace();
	       }

		 return prop;
	}

	
// HelpBot method - bi-directional streaming
	
	public StreamObserver<HelpRequest> helpBot(StreamObserver<HelpResponse> responseObserver) {

		String newline = "\n\r";
			
		return new StreamObserver<HelpRequest> () {
			
			@Override
			public void onNext(HelpRequest request) {
				
//			try {
//				int id = request.getProblemID();
				
//				String problems = "1. Reset Password "
//								+ "2. Report bug"
//								+ "3. Use vaults"
//								+ "4. Send/receive payments";
////
//				System.out.println("Welcome to HelpBot. Please select the number of your problem below:");
//				System.out.println("Reset password enter 1: ");
//				System.out.println("Report a bug enter 2: ");
//				System.out.println("Help with Vaults enter 3: ");
//				System.out.println("Help with payments enter 4: ");
//				Thread.sleep(7000);
				
//				id = in.nextInt();
//				
//				System.out.println("You have selected Problem No: " + id + " please find solutions on next screen.");
//				Thread.sleep(2000); 
				
				String solution = null;

				if(request.getOperation()==Operation.PASSWORD_RESET)
				{
					solution = "To reset your password, navigate to your account and under settings select " + newline+ "'change password' and follow the instructions";			
				}
				else if(request.getOperation()==Operation.REPORT_BUG)
				{								
					solution = "To report a bug please send an email to our "+ newline+ " 24 hour help assist at: eBankingServices.gRPC@nci.ie";
				}
				else if(request.getOperation()==Operation.VAULTS)
				{
					solution = "To use our vault service simply navigate to the vaults under the " + newline+ "'user tools' menu, select the amount of money you wish to store and " + newline+ "select a date for when the vault can be reopened.";
				}
				else if(request.getOperation()==Operation.PAYMENTS)
				{
					solution = "If you are experiencing issues making or recieveing" + newline+"  payment please call our free 24 hour hotline at: 0800-03041992.";
				}
				else{

					solution = "You have selected an invalid option. " + newline+ " Please select from the list";
				}		

				HelpResponse reply = HelpResponse.newBuilder()
						.setSolution(solution)
						.build();
				
				responseObserver.onNext(reply);
			
//				Thread.sleep(1000);
//				

				// Sleep for a bit before sending the next one.
//				Thread.sleep(new Random().nextInt(1000) + 500);

//
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {			
//				e.printStackTrace();
//			}
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
	
// Vault method - Unary
	
public void vault(VaultAccess request, StreamObserver<VaultConfirmation> responseObserver) {
		
		VaultConfirmation vc;
			
		if (authenticateUser(request.getUsername(), request.getPassword())) {
			
			vc = VaultConfirmation.newBuilder()	
					.setVaultConf("Vault ID. " + request.getVaultID() + ": " + euro + request.getSum() +  
							" locked into Acc No. " + request.getAccNo() 
							+ ". The money can not be accessed until " + request.getUnlockDate())
					.build();
			
		} else {	
			vc = VaultConfirmation.newBuilder()	
					.setVaultConf("Username or Password Incorrect!" )
					.build();
		}
		
		responseObserver.onNext(vc);
		responseObserver.onCompleted();
	}

// Interest calculator method - Unary 

public void interestCalc(CalcRequest request, StreamObserver<CalcResponse> responseObserver) {

	System.out.println("Calculating interest...");


	String accType = request.getAccType();
	String access = request.getAccess();
	double sum = request.getSum();
	double interest = 0;
	

		if (accType.equals("12")){ // if..else statement for "12 month term" account type
			if (access.equals("yes")){ // if money access allowed
				interest = sum * 0.0001; // interest will equal input amount * (interest stated in question)
			}else if (access.equals("no")){ // else if money access is not allowed
				interest = sum * 0.0004; // then interest will equal input amount * (interest stated in question)
			}
		}else if (accType.equals("24")){ // if..else statement for "24 month term" account type
			if (access.equals("yes")){
				interest = sum * 0.002;
			}else if (access.equals("no")){
				interest = sum * 0.003;
			}
		}else if (accType.equals("36")){ // if..else statement for "36 month term" account type
			if (access.equals("yes")){
				interest = sum * 0.0025;
			}else if (access.equals("no")){
				interest = sum * 0.05;
			}
		} else { 
			interest = -1; // if account type is invalid
		}	

		CalcResponse reply = CalcResponse.newBuilder()
				.setInterest(interest)
				.build();
	
		responseObserver.onNext(reply);
	
		responseObserver.onCompleted();
}


// Authenticate user method
	
	private boolean authenticateUser(String username, String password) 
	{
		if (username.equals("Amy") && password.equals("123")) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
}
