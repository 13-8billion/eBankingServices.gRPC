package eBankingServices.UserAccount;

import java.util.concurrent.TimeUnit;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import eBankingServices.UserAccount.UserAccountGrpc.UserAccountBlockingStub;
import eBankingServices.UserAccount.UserAccountGrpc.UserAccountStub;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UserAccountClient {

	private static UserAccountBlockingStub blockingStub; // Declare stubs
	private static UserAccountStub asyncStub;
	private static ServiceInfo userAccountServiceInfo;

	public static void main(String args[]) throws InterruptedException {

		UserAccountClient obj = new UserAccountClient();

		String userAccount_service_type = "_userAccount._tcp.local.";

		// discover user account service
		obj.discoverUserAccountService(userAccount_service_type);

		String host = userAccountServiceInfo.getHostAddresses()[0];
		int port = userAccountServiceInfo.getPort();

		final ManagedChannel channel = ManagedChannelBuilder // build channel
				.forAddress(host, port).usePlaintext().build();

		// stubs -- generated from .proto file
		blockingStub = UserAccountGrpc.newBlockingStub(channel);
		asyncStub = UserAccountGrpc.newStub(channel);

		// call methods
		login();
		viewAccount();
		changePassword();

		channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
	}

// discover service method jmDNS	
	private void discoverUserAccountService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("USER ACCOUNT Service resolved: " + event.getInfo());

					userAccountServiceInfo = event.getInfo();

					int port = userAccountServiceInfo.getPort();

					String host = userAccountServiceInfo.getHostAddresses()[0];

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + userAccountServiceInfo.getNiceTextString());
					System.out.println("\t host: " + host);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("USER ACCOUNT Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("USER ACCOUNT Service added: " + event.getInfo());
					jmdns.requestServiceInfo(event.getType(), event.getName());

				}
			});

			// Wait a bit
			Thread.sleep(500);
			jmdns.close();

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

// Login method - Unary

	public static void login() {

		Scanner in = new Scanner(System.in);
		String username;
		String password;

		System.out.println("Client >>>>>>>>> Requesting login...");
		System.out.println("Enter username (Amy): ");
		username = in.next();
		System.out.println("Enter password (123): ");
		password = in.next();

		// build client message
		LoginConfirmation response = blockingStub
				.login(LoginRequest.newBuilder().setUsername(username).setPassword(password).build());

		System.out.println(response.getMessage()); // print response from server

	}

//  View Account Info method - Server-streaming

	public static void viewAccount() {

		Scanner in = new Scanner(System.in);
		int accountNo;
		String euro = "\u20ac";

		System.out.println("Client >>>>>>>>> Enter account number to view (1, 2 or 3): ");
		accountNo = in.nextInt();

		ViewRequest request = ViewRequest.newBuilder() // build client message
				.setAccNo(accountNo).build();

		// prepare for server response
		StreamObserver<AccountInfo> responseObserver = new StreamObserver<AccountInfo>() {

			@Override
			public void onNext(AccountInfo value) {
				// retrieve response from the server
				System.out.println("Client >>>>>>>>> Requesting details for Account No: " + accountNo);
				System.out.println(value.getMessage());
				System.out.println("Account No:  " + value.getAccNo());
				System.out.println("Full Name:  " + value.getFirstName() + " " + value.getLastName());
				System.out.println("Balance:  " + euro + value.getBalance());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();

			}

			@Override
			public void onCompleted() {
				System.out.println("Client >>>>>>>>> Server stream complete");
			}

		};

		asyncStub.viewAccount(request, responseObserver); // send client request

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

// Change Password method - Unary
	public static void changePassword() {

		Scanner in = new Scanner(System.in);
		String username;
		String currPass;
		String newPass;

		System.out.println("Client >>>>>>>>> Requesting to change password...");
		System.out.println("Enter username (Amy): ");
		username = in.next();
		System.out.println("Enter current password (123): ");
		currPass = in.next();
		System.out.println("Enter new password: ");
		newPass = in.next();

		// build client message
		PasswordConfirmation response = blockingStub.changePassword(PasswordRequest.newBuilder().setUsername(username)
				.setCurrPass(currPass).setNewPass(newPass).build());	// set user input values
	

		System.out.println(response.getMessage()); // print response from server

	}
}
