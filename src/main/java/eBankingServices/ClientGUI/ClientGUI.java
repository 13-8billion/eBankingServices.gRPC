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
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositConfirmationOrBuilder;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.RequestStatus;
import eBankingServices.Transactions.RequestSum;
import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;
import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferConfirmationOrBuilder;
import eBankingServices.Transactions.TransferSum;
import eBankingServices.UserAccount.AccountInfo;
import eBankingServices.UserAccount.LoginConfirmation;
import eBankingServices.UserAccount.LoginRequest;
import eBankingServices.UserAccount.PasswordConfirmation;
import eBankingServices.UserAccount.PasswordRequest;
import eBankingServices.UserAccount.UserAccountGrpc;
import eBankingServices.UserAccount.ViewRequest;
import eBankingServices.UserTools.CalcRequest;
import eBankingServices.UserTools.CalcResponse;
import eBankingServices.UserTools.HelpRequest;
import eBankingServices.UserTools.HelpResponse;
import eBankingServices.UserTools.UserToolsGrpc;
import eBankingServices.UserTools.VaultAccess;
import eBankingServices.UserTools.VaultConfirmation;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


public class ClientGUI implements ActionListener{
	
	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;

	private JTextField entry1, entry2, reply1;
	private JTextField fromAccNo, toAccNo, sum, transferID, tranMsg;
	private JTextField fromAccNo2, toAccNo2, sum2, requestID, status;
	private JPasswordField currPass, newPass; // password;
	private JTextField accno, firstName, lastName, balance, viewAccMsg, username, passMsg;
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
    // vault
    private JTextField vaultUsername = new JTextField(10);
    private JTextField vaultConf = new JTextField(50);
	private JLabel labelaccno = new JLabel("Acc No: ");
    private JLabel labelsum = new JLabel("Amount: ");
    private JTextField vaultaccno = new JTextField(10);
    private JLabel labelunlock = new JLabel("Unlock Date (dd/mm/yyyy): ");
    private JTextField vaultunlock = new JTextField(10);
    private JTextField vaultsum = new JTextField(10);
    private JPasswordField vaultPassword = new JPasswordField(10);
    private JButton buttonVault = new JButton("Vault");
    //calculator
    private JLabel labelacctype = new JLabel("Acc type (12, 24 or 36) term: ");
    private JTextField acctype = new JTextField(10);
    private JLabel labelcalcsum = new JLabel("Amount € ");
    private JTextField calcsum = new JTextField(10);
    private JLabel labelaccess = new JLabel("Access(yes/no): ");
    private JTextField access = new JTextField(10);
    private JButton buttoncalc = new JButton("Calculate");
    private JLabel labelinterest = new JLabel("Interest Payable € ");
    private JTextField interest = new JTextField(10);


// deposit -----------------------------
 	private JPanel getDepositJPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label = new JLabel("To Acc No:")	;
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry1 = new JTextField("",10);
		panel.add(entry1);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label2 = new JLabel("Amount:")	;
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry2 = new JTextField("",10);
		panel.add(entry2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button = new JButton("Deposit");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply1 = new JTextField("", 100);
		reply1.setEditable(false);
		panel.add(reply1);
		
		panel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "DEPOSIT"));
				         

		panel.setLayout(boxlayout);

		return panel;
	}
	
// transfer -----------------------------	
	private JPanel getTransferJPanel() {
		
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label5 = new JLabel("To Acc No:")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		toAccNo = new JTextField("",10);
		panel.add(toAccNo);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JLabel label4 = new JLabel("From Acc No:")	;
		panel.add(label4);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		fromAccNo = new JTextField("",10);
		panel.add(fromAccNo);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label6 = new JLabel("Amount:")	;
		panel.add(label6);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		sum = new JTextField("",10);
		panel.add(sum);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button2 = new JButton("Transfer");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		tranMsg = new JTextField("", 100);
		tranMsg.setEditable(false);
		panel.add(tranMsg);
		
		panel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "TRANSFER"));
				         
		panel.setLayout(boxlayout);

		return panel;
	}
		
// request -----------------------------		
	private JPanel getRequestJPanel() {
				
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label5 = new JLabel("To Acc No:")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		toAccNo2 = new JTextField("",10);
		panel.add(toAccNo2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label4 = new JLabel("From Acc No:")	;
		panel.add(label4);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		fromAccNo2 = new JTextField("",10);
		panel.add(fromAccNo2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label6 = new JLabel("Amount:")	;
		panel.add(label6);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		sum2 = new JTextField("",10);
		panel.add(sum2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton button2 = new JButton("Request");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		status = new JTextField("", 100);
		status.setEditable(false);
		panel.add(status);
		
	    panel.setBorder(BorderFactory.createTitledBorder(
	    BorderFactory.createEtchedBorder(), "REQUEST"));
	         
		panel.setLayout(boxlayout);

		return panel;
	}
		
// login ----------------------------
	private JPanel getLoginJPanel() {
			
//		JPanel panel = new JPanel();
//		
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
//		
//		JLabel transferLabel = new JLabel("LOGIN         |");
//		panel.add(transferLabel);
//		panel.add(Box.createRigidArea(new Dimension(50, 0)));
//	
//		JLabel label5 = new JLabel("Username:")	;
//		panel.add(label5);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		username = new JTextField(10);
//		panel.add(username);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		
//		JLabel label4 = new JLabel("Password:")	;
//		panel.add(label4);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		password = new JPasswordField(10);
//		panel.add(password);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		
//		JButton button2 = new JButton("Login");
//		button2.addActionListener(this);
//		panel.add(button2);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		loginConf = new JTextField("", 100);
//		loginConf.setEditable(false);
//		panel.add(loginConf);
//
//		panel.setLayout(boxlayout);
//
//		return panel;
//		
//		private JLabel labelUsername = new JLabel("Enter username: ");
//	    private JLabel labelPassword = new JLabel("Enter password: ");
//	    private JTextField textUsername = new JTextField(20);
//	    private JPasswordField fieldPassword = new JPasswordField(20);
//	    private JButton buttonLogin = new JButton("Login");
//	
		         
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
		        
		        constraints.gridx = 1;
		        constraints.gridy = 3;
		        panel.add(loginConf1, constraints);
		         
		         
		        // set border for the panel
		        panel.setBorder(BorderFactory.createTitledBorder(
		        BorderFactory.createEtchedBorder(), "LOGIN"));
		         
		        return panel;

	}
		
	
	private JPanel getViewAccountJPanel() {
		
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
	
		JLabel label5 = new JLabel("Acc No: ")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		accno = new JTextField("",10);
		panel.add(accno);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton button2 = new JButton("View Account");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(0, 0)));

		viewAccMsg = new JTextField("", 20);
		viewAccMsg.setEditable(false);
		panel.add(viewAccMsg);
		
		JLabel label6 = new JLabel("First Name: ")	;
		panel.add(label6);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		firstName= new JTextField("", 10);
		firstName.setEditable(false);
		panel.add(firstName); 
		
		JLabel label7 = new JLabel("Last Name: ")	;
		panel.add(label7);
		lastName= new JTextField("", 10);
		lastName.setEditable(false);
		panel.add(lastName);
		
		JLabel label8 = new JLabel("Balance: ")	;
		panel.add(label8);
		balance= new JTextField("", 10);
		balance.setEditable(false);
		panel.add(balance);

		panel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "VIEW ACCOUNT"));
				         

		panel.setLayout(boxlayout);

		return panel;
	}

	
	private JPanel getChangePassJPanel() {
		
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label5 = new JLabel("Username: ")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		username = new JTextField("",10);
		panel.add(username);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label6 = new JLabel("Current password: ")	;
		panel.add(label6);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		currPass = new JPasswordField("",10);
		panel.add(currPass);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label7 = new JLabel("New password: ")	;
		panel.add(label7);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		newPass = new JPasswordField("",10);
		panel.add(newPass);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton button2 = new JButton("Change password");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(0, 0)));

		passMsg = new JTextField("", 20);
		passMsg.setEditable(false);
		panel.add(passMsg);
		
		panel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "CHANGE PASSWORD"));
		   
		 panel.setLayout(boxlayout);

		return panel;
		
	}
	
	
	private JPanel getHelpBotJPanel() {
		
		
		JPanel panel = new JPanel();
//		panel.setBackground(Color.white);
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label2 = new JLabel("How to: ");
		panel.add(label2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
	
		problems = new JTextField("1. Reset Password", 10);
		problems.setEditable(false);
		panel.add(problems);
		
		JLabel label5 = new JLabel("Enter no:")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		problemID = new JTextField("",0);
		panel.add(problemID);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton button2 = new JButton("Select");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(0, 0)));
		
		JLabel label3 = new JLabel("Solution: ");
		panel.add(label3);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		solution = new JTextField("", 20);
		solution.setEditable(false);
		panel.add(solution);
		
		panel.setBorder(BorderFactory.createTitledBorder(
		BorderFactory.createEtchedBorder(), "HELP BOT"));
		
		panel.setLayout(boxlayout);

		return panel;
	}
	
	
	private JPanel getVaultJPanel() {
		
		   JPanel panel = new JPanel(new GridBagLayout());
	         
	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.WEST;
	        constraints.insets = new Insets(10, 10, 10, 10);
	         
	        // add components to the panel
	       
	        constraints.gridx = 0;
	        constraints.gridy = 0;     
	        panel.add(labelUsername2, constraints);
	 
	        constraints.gridx = 1;
	        panel.add(vaultUsername, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 1;     
	        panel.add(labelPassword2, constraints);
	         
	        constraints.gridx = 1;
	        panel.add(vaultPassword, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 2;     
	        panel.add(labelaccno, constraints);
	 
	        constraints.gridx = 1;
	        panel.add(vaultaccno, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 3;     
	        panel.add(labelsum, constraints);
	         
	        constraints.gridx = 1;
	        panel.add(vaultsum, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 4;     
	        panel.add(labelunlock, constraints);
	         
	        constraints.gridx = 1;
	        panel.add(vaultunlock, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 5;
	        constraints.gridwidth = 2;
	        constraints.anchor = GridBagConstraints.CENTER;
	    	buttonVault.addActionListener(this);
	        panel.add(buttonVault, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 6;
	        panel.add(vaultConf, constraints);
	         
	         
	        // set border for the panel
	        panel.setBorder(BorderFactory.createTitledBorder(
	        BorderFactory.createEtchedBorder(), "VAULT"));
	         
	        return panel;
		
	}
	
	
	private JPanel getInterestCalcJPanel() {
		
		 JPanel panel = new JPanel(new GridBagLayout());
         
	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.anchor = GridBagConstraints.WEST;
	        constraints.insets = new Insets(10, 10, 10, 10);
	         
	        // add components to the panel
	        constraints.gridx = 0;
	        constraints.gridy = 0;     
	        panel.add(labelacctype, constraints);
	 
	        constraints.gridx = 1;
	        panel.add(acctype, constraints);
	         
	        constraints.gridx = 0;
	        constraints.gridy = 1;     
	        panel.add(labelcalcsum, constraints);
	         
	        constraints.gridx = 1;
	        panel.add(calcsum, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 2;     
	        panel.add(labelaccess, constraints);
	 
	        constraints.gridx = 1;
	        panel.add(access, constraints);
	        
	        constraints.gridx = 0;
	        constraints.gridy = 4;
	        constraints.gridwidth = 2;
	        constraints.anchor = GridBagConstraints.CENTER;
	    	buttoncalc.addActionListener(this);
	        panel.add(buttoncalc, constraints);
	        
	        constraints.anchor = GridBagConstraints.WEST;
	        constraints.gridx = 0;
	        constraints.gridy = 5;     
	        panel.add(labelinterest, constraints);
	         
	        constraints.gridx = 1;
	        panel.add(interest, constraints);
	        
	        // set border for the panel
	        panel.setBorder(BorderFactory.createTitledBorder(
	        BorderFactory.createEtchedBorder(), "INTEREST CALCULATOR"));
	         
	        return panel;
		
	}
		

	public static void main(String[] args) {

		ClientGUI gui = new ClientGUI();

		gui.build();
	}

	private void build() { 

		JFrame frame = new JFrame("eBANKING APPLICATION");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));
	
//		panel.add( getDepositJPanel() );
//		panel.add( getTransferJPanel() );
//		panel.add( getRequestJPanel() );
//		panel.add( getLoginJPanel() );
//		panel.add( getViewAccountJPanel() );
//		panel.add( getChangePassJPanel() );
		panel.add( getHelpBotJPanel() );
		panel.add( getVaultJPanel() );
		panel.add( getInterestCalcJPanel() );


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
				JButton button = (JButton)e.getSource();
				String label = button.getActionCommand();  
// DEPOSIT ---------------------------------------------------------------------------------------			
				if (label.equals("Deposit")) {
					System.out.println("Deposits service invoked ...");
					
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
					.usePlaintext()
					.build();
			
			TransactionsGrpc.TransactionsBlockingStub blockingStub = TransactionsGrpc.newBlockingStub(channel);

			//preparing message to send
			DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder()
					.setAccNo(Integer.parseInt(entry1.getText()))
					.setSum(Double.parseDouble(entry2.getText()))
					.build());

			//Retrieving reply from service
			reply1.setText(response.getMessage());

// TRANSFER ---------------------------------------------------------------------------------------		
			}
			else if (label.equals("Transfer")) 
			{
				System.out.println("Transfer service invoked ...");	
			/*
			 * 
			 */
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
						.usePlaintext()
						.build();
				
				TransactionsGrpc.TransactionsStub asyncStub = TransactionsGrpc.newStub(channel);

				//preparing message to send
				StreamObserver<TransferConfirmation> response = new StreamObserver<TransferConfirmation>() {

					@Override
					public void onNext(TransferConfirmation response) {
						
						tranMsg.setText(response.getMessage()); 
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
						System.out.println("Client >>>>>>>>> STREAM END: All transfers have completed.");
		
					}			
				};
				
				StreamObserver<TransferSum> request = asyncStub.transfer(response);
				request.onNext(TransferSum.newBuilder()
						.setFromAccNo(Integer.parseInt(fromAccNo.getText()))
						.setToAccNo(Integer.parseInt(toAccNo.getText()))
						.setSum(Double.parseDouble(sum.getText()))
						.build());

// REQUEST ---------------------------------------------------------------------------------------				
				}
				else if (label.equals("Request")) 
				{
				System.out.println("Request service invoked ...");
				
				/*
				 * 
				 */
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
						.usePlaintext()
						.build();
				
				TransactionsGrpc.TransactionsStub asyncStub = TransactionsGrpc.newStub(channel);

					
				StreamObserver<RequestStatus> responseObserver = new StreamObserver<RequestStatus>() {

					@Override
					public void onNext(RequestStatus response) {
	
						status.setText(response.getStatus()); 
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.println("Client >>>>>>>>> END OF STREAM: Money request completed");
					}

				};

				StreamObserver<RequestSum> requestObserver = asyncStub.request(responseObserver);

					requestObserver.onNext(RequestSum.newBuilder()
							.setFromAccNo(Integer.parseInt(fromAccNo2.getText()))
							.setToAccNo(Integer.parseInt(toAccNo2.getText()))
							.setSum(Double.parseDouble(sum2.getText()))
							.build());
// LOGIN ---------------------------------------------------------------------------------------					
				}
				else if (label.equals("Login")) 
				{
				System.out.println("Login service invoked ...");
			
				/*
				 * 
				 */ 
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
						.usePlaintext()
						.build();
				
				UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

				//preparing message to send
				LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder()
						.setUsername(username1.getText())
						.setPassword(password1.getText())
						.build());

				//Retrieving reply from service
				loginConf1.setText(response.getMessage());
				
// VIEW ACCOUNT  ---------------------------------------------------------------------------------------					
				}
				else if (label.equals("View Account")) 
				{
				System.out.println("View Account service invoked ...");
				
				/*
				 * 
				 */ 
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
						.usePlaintext()
						.build();
				
				UserAccountGrpc.UserAccountStub asyncStub = UserAccountGrpc.newStub(channel);
				
				ViewRequest request = ViewRequest.newBuilder()
						.setAccNo(Integer.parseInt(accno.getText()))
						.build();

				StreamObserver<AccountInfo> responseObserver = new StreamObserver<AccountInfo>() {

					@Override
					public void onNext(AccountInfo value) {
						viewAccMsg.setText(value.getMessage());
						firstName.setText(value.getFirstName());
						lastName.setText(value.getLastName());
						balance.setText(String.valueOf(value.getBalance()) );
							
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
				
				}
				else if (label.equals("Change password")) 
				{
				System.out.println("Password service invoked ...");
			
				/*
				 * 
				 */ 
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
						.usePlaintext()
						.build();
				
				UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

				//preparing message to send
				PasswordConfirmation response = blockingStub.changePassword(PasswordRequest.newBuilder()
						.setUsername(username.getText())
						.setCurrPass(currPass.getText())
						.setNewPass(newPass.getText())	
						.build());

				//Retrieving reply from service
				passMsg.setText(response.getMessage());
				
// HELP BOT  ---------------------------------------------------------------------------------------					
				}
				else if (label.equals("Select"))
				{
				System.out.println("Help Bot service invoked ...");
				
				/*
				 * 
				 */ 
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
						.usePlaintext()
						.build();
				
				UserToolsGrpc.UserToolsStub asyncStub = UserToolsGrpc.newStub(channel);
				
				StreamObserver<HelpResponse> responseObserver = new StreamObserver<HelpResponse>() {

					@Override
					public void onNext(HelpResponse response) {
						problems.setText(response.getProblems()); 
						solution.setText(response.getSolution()); 
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();

					}

					@Override
					public void onCompleted() {
						System.out.println("END OF STREAM: HelpBot offline");
					}

				};

				StreamObserver<HelpRequest> requestObserver = asyncStub.helpBot(responseObserver);

				
					requestObserver.onNext(HelpRequest.newBuilder()
							.setProblemID(Integer.parseInt(problemID.getText()))		
							.build());
		
// VAULT ---------------------------------------------------------------------------------------
			}
			else if (label.equals("Vault")) 
			{
			System.out.println("Vault service invoked ...");
		
			/*
			 * 
			 */ 
			
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
					.usePlaintext()
					.build();
			
			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);
			
				VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
						.setUsername(vaultUsername.getText())
						.setPassword(vaultPassword.getText())
						.setAccNo(Integer.parseInt(vaultaccno.getText()))
						.setSum(Double.parseDouble(vaultsum.getText()))
						.setUnlockDate(vaultunlock.getText())
						.build());
				//Retrieving reply from service
				vaultConf.setText(response.getVaultConf());	

// INTEREST CALCULATOR ---------------------------------------------------------------------------------------
				
			}
			else if (label.equals("Calculate")) 
			{
			System.out.println("interest calculator service invoked ...");
		
			/*
			 * 
			 */ 
			
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
					.usePlaintext()
					.build();
			
			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);
			
			CalcResponse response = blockingStub.interestCalc(CalcRequest.newBuilder()
					.setAccType(acctype.getText())
					.setAccess(access.getText())
					.setSum(Double.parseDouble(calcsum.getText()))
			
					.build());
			//Retrieving reply from service
			interest.setText((String.valueOf(response.getInterest())));	
				
	}
}
}

			
	



