package eBankingServices.ClientGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import eBankingServices.ClientGUI.ClientGUI;
import eBankingServices.UserAccount.AccountInfo;
import eBankingServices.UserAccount.LoginConfirmation;
import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.PasswordConfirmation;
import eBankingServices.UserAccount.PasswordRequest;
import eBankingServices.UserAccount.UserAccountGrpc;
import eBankingServices.UserAccount.ViewRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class UserAccountGUI implements ActionListener {

	private JTextField problems, problemID, solution;
	// login
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JTextField username1 = new JTextField(10);
	private JTextField loginConf1 = new JTextField(20);
	private JPasswordField password1 = new JPasswordField(10);
	private JButton buttonLogin = new JButton("Login");
	private JLabel labelUsername2 = new JLabel("Enter username: ");
	private JLabel labelPassword2 = new JLabel("Enter password: ");
	// change password
	private JLabel labelusername = new JLabel("Username: ");
	private JTextField username = new JTextField(10);
	private JLabel labelcurrpass = new JLabel("Current password: ");
	private JPasswordField currPass = new JPasswordField (10);
	private JLabel labelnewpass = new JLabel("New password: ");
	private JPasswordField newPass = new JPasswordField (10);
	private JLabel labelpassmsg = new JLabel("Message: ");
	private JTextField passMsg= new JTextField(10);
	private JButton buttonPass = new JButton("Change password");
	// view account
	private JLabel labelaccno = new JLabel("Acc no: ");
	private JTextField accno = new JTextField(10);
	private JLabel labelfname = new JLabel("First Name: ");
	private JTextField firstName = new JTextField(10);
	private JLabel labellname = new JLabel("Last Name: ");
	private JTextField lastName = new JTextField(10);
	private JLabel labelbalance = new JLabel("Balance € ");
	private JTextField balance = new JTextField(10);
	private JButton buttonAcc = new JButton("View Account");
	private JTextField viewAccMsg = new JTextField(10);

// login ----------------------------
	private JPanel getLoginJPanel() {

		// create a new panel with GridBagLayout manager
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelUsername, constraints);

		constraints.gridx = 1;
		panel.add(username1, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelPassword, constraints);

		constraints.gridx = 1;
		panel.add(password1, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttonLogin.addActionListener(this);
		panel.add(buttonLogin, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(loginConf1, constraints);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "LOGIN"));

		return panel;

	}

	private JPanel getViewAccountJPanel() {

		// create a new panel with GridBagLayout manager
				JPanel panel = new JPanel(new GridBagLayout());

				GridBagConstraints constraints = new GridBagConstraints();
				constraints.anchor = GridBagConstraints.WEST;
				constraints.insets = new Insets(10, 10, 10, 10);

				// add components to the panel
				constraints.gridx = 0;
				constraints.gridy = 0;
				panel.add(labelaccno, constraints);

				constraints.gridx = 1;
				panel.add(accno, constraints);
				
				constraints.gridx = 0;
				constraints.gridy = 1;
				constraints.gridwidth = 2;
				constraints.anchor = GridBagConstraints.CENTER;
				buttonAcc.addActionListener(this);
				panel.add(buttonAcc, constraints);
		
				constraints.gridx = 1;
				constraints.gridy = 2;
				panel.add(viewAccMsg, constraints);

				constraints.anchor = GridBagConstraints.WEST;
				constraints.gridx = 0;
				constraints.gridy = 3;
				panel.add(labelfname, constraints);

				constraints.gridx = 1;
				panel.add(firstName, constraints);
				
				constraints.gridx = 0;
				constraints.gridy = 4;
				panel.add(labellname, constraints);

				constraints.gridx = 1;
				panel.add(lastName, constraints);

				constraints.gridx = 0;
				constraints.gridy = 5;
				panel.add(labelbalance, constraints);

				constraints.gridx = 1;
				panel.add(balance, constraints);

	
				// set border for the panel
				panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "VIEW ACCOUNTS"));

				return panel;
	}

	private JPanel getChangePassJPanel() {
		
		// create a new panel with GridBagLayout manager
				JPanel panel = new JPanel(new GridBagLayout());

				GridBagConstraints constraints = new GridBagConstraints();
				constraints.anchor = GridBagConstraints.WEST;
				constraints.insets = new Insets(10, 10, 10, 10);

				// add components to the panel
				constraints.gridx = 0;
				constraints.gridy = 0;
				panel.add(labelusername, constraints);

				constraints.gridx = 1;
				panel.add(username, constraints);

				constraints.gridx = 0;
				constraints.gridy = 1;
				panel.add(labelcurrpass, constraints);

				constraints.gridx = 1;
				panel.add(currPass, constraints);
				
				constraints.gridx = 0;
				constraints.gridy = 2;
				panel.add(labelnewpass, constraints);

				constraints.gridx = 1;
				panel.add(newPass, constraints);

				constraints.gridx = 0;
				constraints.gridy = 3;
				constraints.gridwidth = 2;
				constraints.anchor = GridBagConstraints.CENTER;
				buttonPass.addActionListener(this);
				panel.add(buttonPass, constraints);

				constraints.anchor = GridBagConstraints.WEST;
				constraints.gridx = 0;
				constraints.gridy = 4;
				panel.add(labelpassmsg, constraints);
				
				constraints.gridx = 1;
				panel.add(passMsg, constraints);

				// set border for the panel
				panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CHANGE PASSWORD"));

				return panel;

	}

	public static void main(String[] args) {

		UserAccountGUI gui = new UserAccountGUI();

		gui.build();
	}

	private void build() {

		JFrame frame = new JFrame("eBanking Application - User Account Services");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

		panel.add(getLoginJPanel());
		panel.add(getViewAccountJPanel());
		panel.add(getChangePassJPanel());

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * 
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String label = button.getActionCommand();
// LOGIN ---------------------------------------------------------------------------------------					
		if (label.equals("Login")) {
			System.out.println("Login service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

			UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

			// preparing message to send
			LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder().setUsername(username1.getText())
					.setPassword(password1.getText()).build());

			// Retrieving reply from service
			loginConf1.setText(response.getMessage());

// VIEW ACCOUNT  ---------------------------------------------------------------------------------------					
		} else if (label.equals("View Account")) {
			System.out.println("View Account service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

			UserAccountGrpc.UserAccountStub asyncStub = UserAccountGrpc.newStub(channel);

			ViewRequest request = ViewRequest.newBuilder().setAccNo(Integer.parseInt(accno.getText())).build();

			StreamObserver<AccountInfo> responseObserver = new StreamObserver<AccountInfo>() {

				@Override
				public void onNext(AccountInfo value) {
					viewAccMsg.setText(value.getMessage());
					firstName.setText(value.getFirstName());
					lastName.setText(value.getLastName());
					balance.setText(String.valueOf(value.getBalance()));

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
			asyncStub.viewAccount(request, responseObserver);

// CHANGE PASSWORD  ---------------------------------------------------------------------------------------					

		} else if (label.equals("Change password")) {
			System.out.println("Password service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

			UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

			// preparing message to send
			PasswordConfirmation response = blockingStub
					.changePassword(PasswordRequest.newBuilder().setUsername(username.getText())
							.setCurrPass(currPass.getText()).setNewPass(newPass.getText()).build());

			// Retrieving reply from service
			passMsg.setText(response.getMessage());

		}
	}
}
