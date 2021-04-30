package eBankingServices.UserAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountImplBase;

public class UserAccountServer extends UserAccountImplBase {

	private String Username = "Amy"; // hardcode username and password
	private String currPassword = "123";
	private String newline = "\n\r";

	public static void main(String[] args) throws InterruptedException, IOException {

		// initiate server
		UserAccountServer userAccounts = new UserAccountServer();
		// get properties
		Properties prop = userAccounts.getProperties();

		// register the service
		userAccounts.registerService(prop);

		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {
			// build and start the server
			Server server = ServerBuilder.forPort(port).addService(userAccounts).build().start();

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

			String service_description_properties = prop.getProperty("service_description");

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);

			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

//get service properties method

	private Properties getProperties() {

		Properties prop = null;

		try (InputStream input = new FileInputStream("src/main/resources/UserAccount.properties")) {

			prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("User Account Service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

// Login method - Unary

	public void login(LoginRequest request, StreamObserver<LoginConfirmation> responseObserver) {

		LoginConfirmation lc;

		// invoke authenticateUser() method to ensure valid user credentials
		if (authenticateUser(request.getUsername(), request.getPassword())) {

			lc = LoginConfirmation.newBuilder().setMessage("Welcome back " + request.getUsername() + "!").build();
			responseObserver.onNext(lc); // send response

		} else { // if not valid inform the user
			lc = LoginConfirmation.newBuilder().setMessage("Username or Password Incorrect!").build();
			responseObserver.onNext(lc);
		}

		responseObserver.onCompleted(); // complete RPC call
	}

// View Account Info method - Server-streaming

	public void viewAccount(ViewRequest request, StreamObserver<AccountInfo> responseObserver) {

		AccountInfo reply;

		Customer[] cArray = new Customer[3]; // declare customer array

		cArray[0] = new Customer(1, "Arthur", "Morgan", 27000); // hardcode customer values
		cArray[1] = new Customer(2, "Sadie", "Adler", 5900);
		cArray[2] = new Customer(3, "Amy", "Percival", 33333);

		String accNo = String.valueOf(request.getAccNo()); // extract required fields from user

		for (int i = 0; i < cArray.length; i++) // for loop to iterate through customer array
		{
			Customer c = cArray[i]; // add current iteration to a Customer object

			if (request.getAccNo() == c.getAccNo() && validAccNo(accNo)) // get account number from user and validate
																			// using private method
			{
				reply = AccountInfo.newBuilder() // build response
						.setMessage("Streaming Account No: " + request.getAccNo() + " ...").setAccNo(request.getAccNo())
						.setFirstName(c.getFirstName()) // get requested details from Customer
						.setLastName(c.getLastName()).setBalance(c.getBalance()).build();
				responseObserver.onNext(reply); // send response
			} else if (!validAccNo(accNo)) // of account number invalid inform the user
			{

				reply = AccountInfo.newBuilder() // build reply
						.setMessage(
								"Invalid Account Number!" + newline + "Please enter a valid account number (1, 2 or 3)")
						.build();
				responseObserver.onNext(reply); // send response
			}

		}

		responseObserver.onCompleted(); // complete RPC call
	}

// Change Password method - Unary

	public void changePassword(PasswordRequest request, StreamObserver<PasswordConfirmation> responseObserver) {

		PasswordConfirmation pc;

		// validate username and password and invoked private method changePass() to
		// change password is credentials are valid
		if (changePass(request.getUsername(), request.getCurrPass(), request.getNewPass())) {

			pc = PasswordConfirmation.newBuilder() // build response
					.setMessage(
							"Password for " + request.getUsername() + " has been changed to " + request.getNewPass())
					.build();

		} else { // else credentials are invalid
			pc = PasswordConfirmation.newBuilder().setMessage("Username or Password Incorrect!").build();
		}

		responseObserver.onNext(pc); // send response
		responseObserver.onCompleted(); // complete RPC call
	}

// Change password method

	private boolean changePass(String username, String currPass, String newPass) {
		if (username.equals(Username) && currPass.equals(currPassword)) // if user input match hardcoded values
		{
			currPassword = newPass; // set new password
			return true;
		} else {
			return false;
		}
	}

// Authenticate user method

	private boolean authenticateUser(String username, String password) {
		if (username.equals(Username) && password.equals(currPassword)) {
			return true;
		} else {
			return false;
		}
	}

	// validate account number

	private boolean validAccNo(String accNo) {
		if (accNo.matches("([1-3])")) {
			return true;
		} else {
			return false;
		}
	}

// Customer class 

	private class Customer {

		private int accNo;
		private String firstName;
		private String lastName;
		private double balance;

		// constructor
		public Customer(int accNo, String firstName, String lastName, double balance) {
			this.accNo = accNo;
			this.firstName = firstName;
			this.lastName = lastName;
			this.balance = balance;
		}

		public Customer() {
		}

		public int getAccNo() {
			return accNo;
		}

		public void setAccNo(int accNo) {
			this.accNo = accNo;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}
	}
}
