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
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class TransactionsGUI implements ActionListener {

	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;
	
	// deposit instance variables
	private JLabel labelentry1 = new JLabel("To Acc No: ");
	private JTextField entry1 = new JTextField(10);
	private JLabel labelentry2 = new JLabel("Amount € ");
	private JTextField entry2 = new JTextField(10);
	private JButton buttondeposit = new JButton("Deposit");
	private JLabel labelreply1 = new JLabel("Message: ");
	private JTextField reply1 = new JTextField(30);
	
	// transfer instance variables
	private JLabel labelfromaccno = new JLabel("From Acc No: ");
	private JTextField fromAccNo = new JTextField(10);
	private JLabel labeltoaccno = new JLabel("To Acc No: ");
	private JTextField toAccNo = new JTextField(10);
	private JLabel labelsum = new JLabel("Amount € ");
	private JTextField sum = new JTextField(10);
	private JButton buttonTransfer = new JButton("Transfer");
	private JLabel labelmsg = new JLabel("Message: ");
	private JTextField transMsg = new JTextField(30);

	// request instance variables
	private JLabel labelfromaccno2 = new JLabel("From Acc No: ");
	private JTextField fromAccNo2 = new JTextField(10);
	private JLabel labeltoaccno2 = new JLabel("To Acc No: ");
	private JTextField toAccNo2 = new JTextField(10);
	private JLabel labelsum2 = new JLabel("Amount € ");
	private JTextField sum2 = new JTextField(10);
	private JButton buttonRequest = new JButton("Request");
	private JLabel labelstatus = new JLabel("Message: ");
	private JTextField status = new JTextField(30);

// deposit -----------------------------
	private JPanel getDepositJPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelentry1, constraints);

		constraints.gridx = 1;
		panel.add(entry1, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelentry2, constraints);

		constraints.gridx = 1;
		panel.add(entry2, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttondeposit.addActionListener(this);
		panel.add(buttondeposit, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(labelreply1, constraints);

		constraints.gridx = 1;
		panel.add(reply1, constraints);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DEPOSIT"));

		return panel;
	}

// transfer -----------------------------	
	private JPanel getTransferJPanel() {
		
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labeltoaccno, constraints);

		constraints.gridx = 1;
		panel.add(toAccNo, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelfromaccno, constraints);

		constraints.gridx = 1;
		panel.add(fromAccNo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(labelsum, constraints);

		constraints.gridx = 1;
		panel.add(sum, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttonTransfer.addActionListener(this);
		panel.add(buttonTransfer, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(labelmsg, constraints);

		constraints.gridx = 1;
		panel.add(transMsg, constraints);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DEPOSIT"));

		return panel;
	}

// request -----------------------------		
	private JPanel getRequestJPanel() {
		
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelfromaccno2, constraints);

		constraints.gridx = 1;
		panel.add(fromAccNo2, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labeltoaccno2, constraints);

		constraints.gridx = 1;
		panel.add(toAccNo2, constraints);
	
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(labelsum2, constraints);

		constraints.gridx = 1;
		panel.add(sum2, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttonRequest.addActionListener(this);
		panel.add(buttonRequest, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(labelstatus, constraints);

		constraints.gridx = 1;
		panel.add(status, constraints);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "REQUEST"));

		return panel;
	}

	public static void main(String[] args) {

		TransactionsGUI gui = new TransactionsGUI();

		gui.build();
	}

	private void build() {

		JFrame frame = new JFrame("eBanking Application - Transaction Services");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

		panel.add(getDepositJPanel());
		panel.add(getTransferJPanel());
		panel.add(getRequestJPanel());
		
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
// DEPOSIT ---------------------------------------------------------------------------------------			
		if (label.equals("Deposit")) {
			System.out.println("Deposits service invoked ...");

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

			TransactionsGrpc.TransactionsBlockingStub blockingStub = TransactionsGrpc.newBlockingStub(channel);

			// preparing message to send
			DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder()
					.setAccNo(Integer.parseInt(entry1.getText())).setSum(Double.parseDouble(entry2.getText())).build());

			// Retrieving reply from service
			reply1.setText(response.getMessage());

// TRANSFER ---------------------------------------------------------------------------------------		
		} else if (label.equals("Transfer")) {
			System.out.println("Transfer service invoked ...");
			/*
			 * 
			 */
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

			TransactionsGrpc.TransactionsStub asyncStub = TransactionsGrpc.newStub(channel);

			// preparing message to send
			StreamObserver<TransferConfirmation> response = new StreamObserver<TransferConfirmation>() {

				@Override
				public void onNext(TransferConfirmation response) {

					transMsg.setText(response.getMessage());
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
			request.onNext(TransferSum.newBuilder().setFromAccNo(Integer.parseInt(fromAccNo.getText()))
					.setToAccNo(Integer.parseInt(toAccNo.getText())).setSum(Double.parseDouble(sum.getText())).build());

// REQUEST ---------------------------------------------------------------------------------------				
		} else if (label.equals("Request")) {
			System.out.println("Request service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

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

			requestObserver.onNext(RequestSum.newBuilder().setFromAccNo(Integer.parseInt(fromAccNo2.getText()))
					.setToAccNo(Integer.parseInt(toAccNo2.getText())).setSum(Double.parseDouble(sum2.getText()))
					.build());
		}
	}
}
