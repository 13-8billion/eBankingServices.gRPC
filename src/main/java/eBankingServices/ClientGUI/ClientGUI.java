package eBankingServices.ClientGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import eBankingServices.UserTools.UserToolsGrpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;


public class ClientGUI implements ActionListener{
	
	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;

	private JTextField entry1, entry2, reply1;
	private JTextField fromAccNo, toAccNo, sum, transferID, tranMsg;
	private JTextField fromAccNo2, toAccNo2, sum2, requestID, status;
	private JTextField username, loginConf;
	private JPasswordField password, currPass, newPass;
	private JTextField accno, firstName, lastName, balance, viewAccMsg;
	private JTextField passMsg;


// deposit -----------------------------
 	private JPanel getDepositJPanel() {

		JPanel panel = new JPanel();

		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel depositLabel = new JLabel("DEPOSIT      |");
		panel.add(depositLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));

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

		panel.setLayout(boxlayout);

		return panel;
	}
	
// transfer -----------------------------	
	private JPanel getTransferJPanel() {
		
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel transferLabel = new JLabel("TRANSFER   |");
		panel.add(transferLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
		
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

		panel.setLayout(boxlayout);

		return panel;
	}
		
// request -----------------------------		
	private JPanel getRequestJPanel() {
				
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel transferLabel = new JLabel("REQUEST     |");
		panel.add(transferLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
	
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

		panel.setLayout(boxlayout);

		return panel;
	}
		
// login ----------------------------
	private JPanel getLoginJPanel() {
			
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel transferLabel = new JLabel("LOGIN         |");
		panel.add(transferLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
	
		JLabel label5 = new JLabel("Username:")	;
		panel.add(label5);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		username = new JTextField(10);
		panel.add(username);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JLabel label4 = new JLabel("Password:")	;
		panel.add(label4);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		password = new JPasswordField(10);
		panel.add(password);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton button2 = new JButton("Login");
		button2.addActionListener(this);
		panel.add(button2);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		loginConf = new JTextField("", 100);
		loginConf.setEditable(false);
		panel.add(loginConf);

		panel.setLayout(boxlayout);

		return panel;
	}
		
	
	private JPanel getViewAccountJPanel() {
		
		JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label = new JLabel("VIEW            |");
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
	
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


		panel.setLayout(boxlayout);

		return panel;
	}

	
	private JPanel getChangePassJPanel() {
		
JPanel panel = new JPanel();
		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
		
		JLabel label = new JLabel("RESET PASSWORD |");
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
	
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
		
		panel.setLayout(boxlayout);

		return panel;
		
	}
//	
//	private JPanel getHelpBotJPanel() {
//	}
//	
//	private JPanel getVaultJPanel() {
//	}
//	
//	private JPanel getInterestCalcJPanel() {
//	}
		



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
	
		panel.add( getDepositJPanel() );
		panel.add( getTransferJPanel() );
		panel.add( getRequestJPanel() );
		panel.add( getLoginJPanel() );
		panel.add( getViewAccountJPanel() );
		panel.add( getChangePassJPanel() );
//		panel.add( getHelpBotJPanel() );
//		panel.add( getVaultJPanel() );
//		panel.add( getInterestCalcJPanel() );


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
				System.out.println("Request service to be invoked ...");
				
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
				System.out.println("Login service to be invoked ...");
			
				/*
				 * 
				 */ 
				
				ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
						.usePlaintext()
						.build();
				
				UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

				//preparing message to send
				LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder()
						.setUsername(username.getText())
						.setPassword(password.getText())
						.build());

				//Retrieving reply from service
				loginConf.setText(response.getMessage());
				
// VIEW ACCOUNT  ---------------------------------------------------------------------------------------					
				}
				else if (label.equals("View Account")) 
				{
				System.out.println("View Account service to be invoked ...");
				
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
				System.out.println("Password service to be invoked ...");
			
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
	}
}

			
	



