package eBankingServices.UserTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.text.SimpleDateFormat;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferSum;
import eBankingServices.UserTools.HelpRequest.Operation;
import eBankingServices.UserTools.UserToolsGrpc.UserToolsImplBase;

public class UserToolsServer extends UserToolsImplBase {

	private String euro = "\u20AC";
	private String newline = "\n\r";
	private String Username = "Amy";
	private String Password = "123";

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserToolsServer userTools = new UserToolsServer();

		Properties prop = userTools.getProperties();

		// register the service
		userTools.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {

			Server server = ServerBuilder.forPort(port).addService(userTools).build().start();

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
			String service_name = prop.getProperty("service_name");

			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			String service_description_properties = prop.getProperty("service_description");// "path=index.html";

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

			// Wait a bit
			Thread.sleep(50);

			// Unregister all services
			// jmdns.unregisterAllServices();

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
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

// HelpBot method - bi-directional streaming

	public StreamObserver<HelpRequest> helpBot(StreamObserver<HelpResponse> responseObserver) {

		return new StreamObserver<HelpRequest>() {

			@Override
			public void onNext(HelpRequest request) {

				String solution = null;

				if (request.getOperation() == Operation.PASSWORD_RESET) {
					solution = request.getMessage() + newline + "To reset your password, navigate to your account"
							+ newline + "and under settings select 'change password'" + newline
							+ "and follow the instructions" + newline + newline;
				} else if (request.getOperation() == Operation.REPORT_BUG) {
					solution = request.getMessage() + newline + "To report a bug please send an email to our " + newline
							+ "24 hour help assist at: eBankingServices.gRPC@nci.ie" + newline + newline;
				} else if (request.getOperation() == Operation.VAULTS) {
					solution = request.getMessage() + newline
							+ "To use our vault service simply navigate to the vaults under the " + newline
							+ "'user tools' menu, select the amount of money you wish to store and " + newline
							+ "select a date for when the vault can be reopened." + newline + newline;
				} else if (request.getOperation() == Operation.PAYMENTS) {
					solution = request.getMessage() + newline
							+ "If you are experiencing issues making or recieveing payment " + newline
							+ "please call our free 24 hour hotline at: 0800-03041992." + newline + newline;
				}

				HelpResponse reply = HelpResponse.newBuilder().setSolution(solution).build();

				responseObserver.onNext(reply);

			}

			@Override
			public void onError(Throwable t) {

				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println();

				// completed too
				responseObserver.onCompleted();
			}
		};
	}

// Vault method - Unary

	public void vault(VaultAccess request, StreamObserver<VaultConfirmation> responseObserver) {

		boolean validAccNo = false;
		boolean checkFormat;
		String dateFormat = "03/04/2021";
		String unlockDate = request.getUnlockDate();
		String accNo = String.valueOf(request.getAccNo());

		if (accNo.matches("([1-3])")) {
			validAccNo = true;
		} else {
			validAccNo = false;
		}

		if (unlockDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
			checkFormat = true;
		else
			checkFormat = false;

		VaultConfirmation vc = null;

		if (authenticateUser(request.getUsername(), request.getPassword()) && validAccNo == true
				&& checkFormat == true) {

			vc = VaultConfirmation.newBuilder()
					.setVaultConf("SUCCESS! : " + euro + request.getSum() + " locked into Acc No. " + request.getAccNo()
							+ newline + "The money can not be accessed until " + request.getUnlockDate())
					.build();

		} else if (!authenticateUser(request.getUsername(), request.getPassword())) {
			vc = VaultConfirmation.newBuilder().setVaultConf("Username or Password Incorrect!").build();
		} else if (validAccNo != true) {
			vc = VaultConfirmation.newBuilder().setVaultConf(
					"Account Number does not exist exist!" + newline + "Please enter valid Account Number (1, 2 or 3)")
					.build();
		} else if (checkFormat != true) {
			vc = VaultConfirmation.newBuilder().setVaultConf("Invalid date/format!").build();
		}
		System.out.println(vc);
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

		if (accType.equals("12")) { // if..else statement for "12 month term" account type
			if (access.equals("yes")) { // if money access allowed
				interest = sum * 0.0001; // interest will equal input amount * (interest stated in question)
			} else if (access.equals("no")) { // else if money access is not allowed
				interest = sum * 0.0004; // then interest will equal input amount * (interest stated in question)
			}
		} else if (accType.equals("24")) { // if..else statement for "24 month term" account type
			if (access.equals("yes")) {
				interest = sum * 0.002;
			} else if (access.equals("no")) {
				interest = sum * 0.003;
			}
		} else if (accType.equals("36")) { // if..else statement for "36 month term" account type
			if (access.equals("yes")) {
				interest = sum * 0.0025;
			} else if (access.equals("no")) {
				interest = sum * 0.05;
			}
		} else {
			interest = -1; // if account type is invalid
		}

		CalcResponse reply = CalcResponse.newBuilder().setInterest(interest).build();

		responseObserver.onNext(reply);

		responseObserver.onCompleted();
	}

// Authenticate user method

	private boolean authenticateUser(String username, String password) {
		if (username.equals(Username) && password.equals(Password)) {
			return true;
		} else {
			return false;
		}
	}

}
