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

public class UserToolsGUI implements ActionListener {

	// calculator
	private JLabel labelacctype = new JLabel("Acc type (12, 24 or 36) term: ");
	private JTextField acctype = new JTextField(10);
	private JLabel labelcalcsum = new JLabel("Amount € ");
	private JTextField calcsum = new JTextField(10);
	private JLabel labelaccess = new JLabel("Access(yes/no): ");
	private JTextField access = new JTextField(10);
	private JButton buttoncalc = new JButton("Calculate");
	private JLabel labelinterest = new JLabel("Interest Payable € ");
	private JTextField interest = new JTextField(10);
	// vault
	private JTextField vaultUsername = new JTextField(10);
	private JLabel labelUsername2 = new JLabel("Username: ");
	private JTextArea vaultConf = new JTextArea();
	private JLabel labelaccno = new JLabel("Acc No: ");
	private JLabel labelsum = new JLabel("Amount: ");
	private JTextField vaultaccno = new JTextField(10);
	private JLabel labelunlock = new JLabel("Unlock Date (dd/mm/yyyy): ");
	private JTextField vaultunlock = new JTextField(10);
	private JTextField vaultsum = new JTextField(10);
	private JLabel labelPassword2 = new JLabel("Password: ");
	private JPasswordField vaultPassword = new JPasswordField(10);
	private JButton buttonVault = new JButton("Vault");
	// help bot
	String newline = "\n\r";
	private JLabel labelhowto = new JLabel("How to: ");
	private JTextArea problem = new JTextArea("1. Reset password" + newline + "2. Report bug" + newline + "3. Use vaults" + newline + "4. Send/receive payments");
	private JLabel labelproblemID = new JLabel("Enter No:  ");
	private JTextField problemID = new JTextField(10);
	private JLabel labelsolution = new JLabel("Solution: ");
	private JTextArea solutions = new JTextArea();
	private JButton buttonselect = new JButton("Select");

	private JPanel getHelpBotJPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

		// add components to the panel

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelhowto, constraints);

		constraints.ipady = 5;
		constraints.ipadx = 5;
		constraints.gridx = 1;
		panel.add(problem, constraints);
		problem.setEditable(false);

		constraints.ipady = 0;
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelproblemID, constraints);

		constraints.gridx = 1;
		panel.add(problemID, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttonselect.addActionListener(this);
		panel.add(buttonselect, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(labelsolution, constraints);

		constraints.ipady = 5;
		constraints.ipadx = 5;
		constraints.gridx = 1;
		panel.add(solutions, constraints);
		solutions.setEditable(false);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HELP BOT"));

		return panel;
	}

	private JPanel getVaultJPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

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

		constraints.ipady = 5;
		constraints.ipadx = 5;
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(vaultConf, constraints);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "VAULT"));

		return panel;

	}

	private JPanel getInterestCalcJPanel() {

		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

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
		interest.setEditable(false);

		// set border for the panel
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "INTEREST CALCULATOR"));

		return panel;

	}

	public static void main(String[] args) {

		UserToolsGUI gui = new UserToolsGUI();

		gui.build();
	}

	private void build() {

		JFrame frame = new JFrame("eBanking Application - User Tools");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(25, 50, 25, 50)));

		panel.add(getHelpBotJPanel());
		panel.add(getVaultJPanel());
		panel.add(getInterestCalcJPanel());

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
		
// HELP BOT  ---------------------------------------------------------------------------------------					

		if (label.equals("Select")) {
			System.out.println("Help Bot service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();

			UserToolsGrpc.UserToolsStub asyncStub = UserToolsGrpc.newStub(channel);

			StreamObserver<HelpResponse> responseObserver = new StreamObserver<HelpResponse>() {

				@Override
				public void onNext(HelpResponse response) {
					solutions.setText(response.getSolution());
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

			requestObserver
					.onNext(HelpRequest.newBuilder().setProblemID(Integer.parseInt(problemID.getText())).build());

// VAULT ---------------------------------------------------------------------------------------
		} else if (label.equals("Vault")) {
			System.out.println("Vault service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();

			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);

			VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
					.setUsername(vaultUsername.getText()).setPassword(vaultPassword.getText())
					.setAccNo(Integer.parseInt(vaultaccno.getText())).setSum(Double.parseDouble(vaultsum.getText()))
					.setUnlockDate(vaultunlock.getText()).build());
			// Retrieving reply from service
			vaultConf.setText(response.getVaultConf());

// INTEREST CALCULATOR ---------------------------------------------------------------------------------------

		} else if (label.equals("Calculate")) {
			System.out.println("interest calculator service invoked ...");

			/*
			 * 
			 */

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();

			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);

			CalcResponse response = blockingStub.interestCalc(CalcRequest.newBuilder().setAccType(acctype.getText())
					.setAccess(access.getText()).setSum(Double.parseDouble(calcsum.getText()))

					.build());
			// Retrieving reply from service
			interest.setText((String.valueOf(response.getInterest())));

		}
	}
}
