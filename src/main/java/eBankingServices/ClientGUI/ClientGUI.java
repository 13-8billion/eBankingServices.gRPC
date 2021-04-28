package eBankingServices.ClientGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import eBankingServices.UserTools.HelpRequest.Operation;
import eBankingServices.ClientGUI.ClientGUI;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.RequestStatus;
import eBankingServices.Transactions.RequestSum;
import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.TransferConfirmation;
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

public class ClientGUI implements ActionListener {

	private ServiceInfo transactionsServiceInfo;
	private ServiceInfo userAccountServiceInfo;
	private ServiceInfo userToolsServiceInfo;
	private String newlinee = "\n\r";

	// TRANSACTIONS -----------

	// deposit instance variables
	private JLabel labelentry1 = new JLabel("To Acc No: ");
	private JTextField entry1 = new JTextField(10);
	private JLabel labelentry2 = new JLabel("Amount € ");
	private JTextField entry2 = new JTextField(10);
	private JButton buttondeposit = new JButton("Deposit");
	private JLabel labelreply1 = new JLabel("Message: ");
	private JTextArea reply1 = new JTextArea();
	// transfer instance variables
	private JLabel labelfromaccno = new JLabel("From Acc No: ");
	private JTextField fromAccNo = new JTextField(10);
	private JLabel labeltoaccno = new JLabel("To Acc No: ");
	private JTextField toAccNo = new JTextField(10);
	private JLabel labelsum = new JLabel("Amount € ");
	private JTextField sum = new JTextField(10);
	private JButton buttonTransfer = new JButton("Transfer");
	private JLabel labelmsg = new JLabel("Message: ");
	private JTextArea transMsg = new JTextArea();
	// request instance variables
	private JLabel labelfromaccno2 = new JLabel("From Acc No: ");
	private JTextField fromAccNo2 = new JTextField(10);
	private JLabel labeltoaccno2 = new JLabel("To Acc No: ");
	private JTextField toAccNo2 = new JTextField(10);
	private JLabel labelsum2 = new JLabel("Amount € ");
	private JTextField sum2 = new JTextField(10);
	private JLabel labelmonthly = new JLabel("Set monthly? (true/false) ");
	private JTextField monthly = new JTextField(10);
	private JButton buttonRequest = new JButton("Request");
	private JLabel labelstatus = new JLabel("Message: ");
	private JTextArea status = new JTextArea();
	private JLabel labelapprove = new JLabel("Approve? (true/false) ");
	private JTextField approve = new JTextField(10);

	// USER ACCOUNT---------------------

	// change password
	private JLabel labelusername = new JLabel("Username: ");
	private JTextField username = new JTextField(10);
	private JLabel labelcurrpass = new JLabel("Current password: ");
	private JPasswordField currPass = new JPasswordField(10);
	private JLabel labelnewpass = new JLabel("New password: ");
	private JPasswordField newPass = new JPasswordField(10);
	private JTextArea passMsg = new JTextArea();
	private JButton buttonPass = new JButton("Change password");
	// view account
	private JLabel labelaccno = new JLabel("Account No: ");
	private JTextField accno = new JTextField(10);
	private JLabel labelfname = new JLabel("First Name: ");
	private JTextField firstName = new JTextField(10);
	private JLabel labellname = new JLabel("Last Name: ");
	private JTextField lastName = new JTextField(10);
	private JLabel labelbalance = new JLabel("Balance € ");
	private JTextField balance = new JTextField(10);
	private JButton buttonAcc = new JButton("View Account");
	private JTextArea viewAccMsg = new JTextArea();
	private JTextArea recentTrans = new JTextArea();
	// login
	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JTextField username1 = new JTextField(10);
	private JTextArea loginConf1 = new JTextArea();
	private JPasswordField password1 = new JPasswordField(10);
	private JButton buttonLogin = new JButton("Login");
	private JLabel labelUsername2 = new JLabel("Enter username: ");
	private JLabel labelPassword2 = new JLabel("Enter password: ");

	// USER TOOLS --------------

	// vault
	private JTextField vaultUsername = new JTextField(10);
	private JTextArea vaultConf = new JTextArea();
	private JLabel labelaccnovault = new JLabel("Acc No: ");
	private JLabel labelsumvault = new JLabel("Amount € ");
	private JTextField vaultaccno = new JTextField(10);
	private JLabel labelunlock = new JLabel("Unlock Date (dd/mm/yyyy): ");
	private JTextField vaultunlock = new JTextField(10);
	private JTextField vaultsum = new JTextField(10);
	private JPasswordField vaultPassword = new JPasswordField(10);
	private JButton buttonVault = new JButton("Vault");
	// calculator
	private JLabel labelroi = new JLabel("Rates of interest: ");
	private JTextArea roi = new JTextArea(
			"12 MONTHS TERM: " + newlinee + "access - 0.0001%" + newlinee + "no access - 0.0004%" + newlinee + newlinee
					+ "24 MONTHS TERM: " + newlinee + "access - 0.002%" + newlinee + "no access - 0.003%" + newlinee
					+ newlinee + "36 MONTHS TERM: " + newlinee + "access - 0.0025%" + newlinee + "no access - 0.05%");
	private JLabel labelacctype = new JLabel("Account type (12, 24 or 36): ");
	private JTextField acctype = new JTextField(10);
	private JLabel labelcalcsum = new JLabel("Amount € ");
	private JTextField calcsum = new JTextField(10);
	private JLabel labelaccess = new JLabel("Money access? (yes/no): ");
	private JTextField access = new JTextField(10);
	private JButton buttoncalc = new JButton("Calculate");
	private JLabel labelinterest = new JLabel("Interest Payable € ");
	private JTextField interest = new JTextField(10);
	private JTextArea error = new JTextArea();
	// help bot
	String newline = "\n\r";
	private JLabel labelhowto = new JLabel("How to: ");
	private JTextArea solutions = new JTextArea();
	private JButton buttonselect = new JButton("Select");
	private JComboBox comboOperation = new JComboBox();

	private JTabbedPane getUserToolsGUIJTabbedPane() {

		JTabbedPane tabPane = new JTabbedPane();

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

// HELP BOT PANEL ----------------------

		JPanel helpBot = new JPanel(new GridBagLayout());

		constraints.gridx = 0;
		constraints.gridy = 0;
		helpBot.add(labelhowto, constraints);

		comboOperation.setModel(new DefaultComboBoxModel(
				new String[] { "Reset Password", "Report a Bug", "Use Vaults", "Send/Receive Payments" }));
		helpBot.add(comboOperation);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		buttonselect.addActionListener(this);
		helpBot.add(buttonselect, constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		helpBot.add(solutions, constraints);
		solutions.setEditable(false);

		// set border for the panel
		helpBot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "HELP BOT"));

// VAULT PANEL -------------------

		JPanel vault = new JPanel(new GridBagLayout());
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.anchor = GridBagConstraints.WEST;
		constraints2.insets = new Insets(5, 5, 5, 5);

		// add components to the panel
		constraints2.gridx = 0;
		constraints2.gridy = 0;
		vault.add(labelUsername2, constraints2);

		constraints2.gridx = 1;
		vault.add(vaultUsername, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 1;
		vault.add(labelPassword2, constraints2);

		constraints2.gridx = 1;
		vault.add(vaultPassword, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 2;
		vault.add(labelaccnovault, constraints2);

		constraints2.gridx = 1;
		vault.add(vaultaccno, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 3;
		vault.add(labelsumvault, constraints2);

		constraints2.gridx = 1;
		vault.add(vaultsum, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 4;
		vault.add(labelunlock, constraints2);

		constraints2.gridx = 1;
		vault.add(vaultunlock, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 5;
		constraints2.gridwidth = 2;
		constraints2.anchor = GridBagConstraints.CENTER;
		buttonVault.addActionListener(this);
		vault.add(buttonVault, constraints2);

		constraints2.gridx = 0;
		constraints2.gridy = 6;
		vault.add(vaultConf, constraints2);
		// set border for the panel
		vault.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "VAULT"));

// INTEREST CALC -------------------

		JPanel interestCalc = new JPanel(new GridBagLayout());

		GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.anchor = GridBagConstraints.WEST;
		constraints3.insets = new Insets(5, 5, 5, 5);

		// add components to the panel
		constraints3.gridx = 0;
		constraints3.gridy = 0;
		interestCalc.add(labelroi, constraints3);

		constraints3.gridx = 1;
		interestCalc.add(roi, constraints3);
		roi.setEditable(false);

		constraints3.gridx = 0;
		constraints3.gridy = 1;
		interestCalc.add(labelacctype, constraints3);

		constraints3.gridx = 1;
		interestCalc.add(acctype, constraints3);

		constraints3.gridx = 0;
		constraints3.gridy = 2;
		interestCalc.add(labelcalcsum, constraints3);

		constraints3.gridx = 1;
		interestCalc.add(calcsum, constraints3);

		constraints3.gridx = 0;
		constraints3.gridy = 3;
		interestCalc.add(labelaccess, constraints3);

		constraints3.gridx = 1;
		interestCalc.add(access, constraints3);

		constraints3.gridx = 0;
		constraints3.gridy = 5;
		constraints3.gridwidth = 2;
		constraints3.anchor = GridBagConstraints.CENTER;
		buttoncalc.addActionListener(this);
		interestCalc.add(buttoncalc, constraints3);

		constraints3.anchor = GridBagConstraints.WEST;
		constraints3.gridx = 0;
		constraints3.gridy = 6;
		interestCalc.add(labelinterest, constraints3);

		constraints3.gridx = 1;
		interestCalc.add(interest, constraints3);
		interest.setEditable(false);
		
		constraints3.gridx = 1;
		constraints3.gridy = 7;
		interestCalc.add(error, constraints3);
		error.setEditable(false);
		// set border for the panel
		interestCalc
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "INTEREST CALCULATOR"));

		tabPane.add("Vault", vault);
		tabPane.add("Help Bot", helpBot);

		tabPane.add("Interest Calculator", interestCalc);

		return tabPane;

	}

	private JTabbedPane getUserAccountGUIJTabbedPane() {

		JTabbedPane tabPane = new JTabbedPane();

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);

// LOGIN PANEL -------------------

		JPanel login = new JPanel(new GridBagLayout());

		GridBagConstraints constraints4 = new GridBagConstraints();
		constraints4.anchor = GridBagConstraints.WEST;
		constraints4.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints4.gridx = 0;
		constraints4.gridy = 0;
		login.add(labelUsername, constraints4);

		constraints4.gridx = 1;
		login.add(username1, constraints4);

		constraints4.gridx = 0;
		constraints4.gridy = 1;
		login.add(labelPassword, constraints4);

		constraints4.gridx = 1;
		login.add(password1, constraints4);

		constraints4.gridx = 0;
		constraints4.gridy = 2;
		constraints4.gridwidth = 2;
		constraints4.anchor = GridBagConstraints.CENTER;
		buttonLogin.addActionListener(this);
		login.add(buttonLogin, constraints4);

		constraints4.gridx = 0;
		constraints4.gridy = 3;
		login.add(loginConf1, constraints4);
		// set border for the panel
		login.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "LOGIN"));

// VIEW ACC PANEL -------------------

		JPanel viewAcc = new JPanel(new GridBagLayout());

		GridBagConstraints constraints5 = new GridBagConstraints();
		constraints5.anchor = GridBagConstraints.WEST;
		constraints5.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints5.gridx = 0;
		constraints5.gridy = 0;
		viewAcc.add(labelaccno, constraints5);

		constraints5.gridx = 1;
		viewAcc.add(accno, constraints5);

		constraints5.gridx = 0;
		constraints5.gridy = 1;
		constraints5.gridwidth = 2;
		constraints5.anchor = GridBagConstraints.EAST;
		buttonAcc.addActionListener(this);
		viewAcc.add(buttonAcc, constraints5);

		constraints5.gridx = 1;
		constraints5.gridy = 2;
		viewAcc.add(viewAccMsg, constraints5);

		constraints5.anchor = GridBagConstraints.WEST;
		constraints5.gridx = 0;
		constraints5.gridy = 3;
		viewAcc.add(labelfname, constraints5);

		constraints5.gridx = 1;
		viewAcc.add(firstName, constraints5);
		firstName.setEditable(false);

		constraints5.gridx = 0;
		constraints5.gridy = 4;
		viewAcc.add(labellname, constraints5);

		constraints5.gridx = 1;
		viewAcc.add(lastName, constraints5);
		lastName.setEditable(false);

		constraints5.gridx = 0;
		constraints5.gridy = 5;
		viewAcc.add(labelbalance, constraints5);

		constraints5.gridx = 1;
		viewAcc.add(balance, constraints5);
		balance.setEditable(false);

		constraints5.gridx = 1;
		constraints5.gridy = 6;
		viewAcc.add(recentTrans, constraints5);
		// set border for the panel
		viewAcc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "VIEW ACCOUNTS"));

// CHANGE PASSWORD PANEL -------------------

		JPanel changePass = new JPanel(new GridBagLayout());

		GridBagConstraints constraints6 = new GridBagConstraints();
		constraints6.anchor = GridBagConstraints.WEST;
		constraints6.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints6.gridx = 0;
		constraints6.gridy = 0;
		changePass.add(labelusername, constraints6);

		constraints6.gridx = 1;
		changePass.add(username, constraints6);

		constraints6.gridx = 0;
		constraints6.gridy = 1;
		changePass.add(labelcurrpass, constraints6);

		constraints6.gridx = 1;
		changePass.add(currPass, constraints6);

		constraints6.gridx = 0;
		constraints6.gridy = 2;
		changePass.add(labelnewpass, constraints6);

		constraints6.gridx = 1;
		changePass.add(newPass, constraints6);

		constraints6.gridx = 0;
		constraints6.gridy = 3;
		constraints6.gridwidth = 2;
		constraints6.anchor = GridBagConstraints.CENTER;
		buttonPass.addActionListener(this);
		changePass.add(buttonPass, constraints6);

		constraints6.anchor = GridBagConstraints.WEST;
		constraints6.gridx = 0;
		constraints6.gridy = 4;

		constraints6.gridx = 0;
		changePass.add(passMsg, constraints6);

		// set border for the panel
		changePass.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "CHANGE PASSWORD"));

		tabPane.add("Login", login);
		tabPane.add("View Account", viewAcc);
		tabPane.add("Change password", changePass);

		return tabPane;
	}

	private JTabbedPane getTransactionsGUIJTabbedPane() {

		JTabbedPane tabPane = new JTabbedPane();

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 5, 5, 5);
// TRANSFER PANEL -------------------

		JPanel transfer = new JPanel(new GridBagLayout());

		GridBagConstraints constraints7 = new GridBagConstraints();
		constraints7.anchor = GridBagConstraints.WEST;
		constraints7.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints7.gridx = 0;
		constraints7.gridy = 0;
		transfer.add(labeltoaccno, constraints7);

		constraints7.gridx = 1;
		transfer.add(toAccNo, constraints7);

		constraints7.gridx = 0;
		constraints7.gridy = 1;
		transfer.add(labelfromaccno, constraints7);

		constraints7.gridx = 1;
		transfer.add(fromAccNo, constraints7);

		constraints7.gridx = 0;
		constraints7.gridy = 2;
		transfer.add(labelsum, constraints7);

		constraints7.gridx = 1;
		transfer.add(sum, constraints7);

		constraints7.gridx = 2;
		constraints7.gridy = 2;
		constraints7.gridwidth = 2;
		constraints7.anchor = GridBagConstraints.CENTER;
		buttonTransfer.addActionListener(this);
		transfer.add(buttonTransfer, constraints7);

		constraints7.anchor = GridBagConstraints.WEST;
		constraints7.gridx = 0;
		constraints7.gridy = 6;
		transfer.add(labelmsg, constraints7);

		constraints7.gridx = 1;
		constraints7.gridy = 6;
		transfer.add(transMsg, constraints7);

		// set border for the panel
		transfer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "TRANSFER"));

// DEPOSIT PANEL -------------------

		JPanel deposit = new JPanel(new GridBagLayout());

		GridBagConstraints constraints8 = new GridBagConstraints();
		constraints8.anchor = GridBagConstraints.WEST;
		constraints8.insets = new Insets(10, 10, 10, 10);

		// add components to the panel
		constraints8.gridx = 0;
		constraints8.gridy = 0;
		deposit.add(labelentry1, constraints8);

		constraints8.gridx = 1;
		deposit.add(entry1, constraints8);

		constraints8.gridx = 0;
		constraints8.gridy = 1;
		deposit.add(labelentry2, constraints8);

		constraints8.gridx = 1;
		deposit.add(entry2, constraints8);

		constraints8.gridx = 1;
		constraints8.gridy = 2;
		constraints8.gridwidth = 2;
		constraints8.anchor = GridBagConstraints.CENTER;
		buttondeposit.addActionListener(this);
		deposit.add(buttondeposit, constraints8);

		constraints8.anchor = GridBagConstraints.WEST;
		constraints8.gridx = 0;
		constraints8.gridy = 5;
		deposit.add(labelreply1, constraints8);

		constraints8.gridx = 1;
		deposit.add(reply1, constraints8);

		// set border for the panel
		deposit.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "DEPOSIT"));

// REQUEST PANEL -------------------

		JPanel request = new JPanel(new GridBagLayout());

		GridBagConstraints constraints9 = new GridBagConstraints();
		constraints9.anchor = GridBagConstraints.WEST;
		constraints9.insets = new Insets(10, 10, 10, 10);

		// add components to the panel

		constraints9.gridx = 0;
		constraints9.gridy = 0;
		request.add(labelfromaccno2, constraints9);

		constraints9.gridx = 1;
		request.add(fromAccNo2, constraints9);

		constraints9.gridx = 0;
		constraints9.gridy = 1;
		request.add(labeltoaccno2, constraints9);

		constraints9.gridx = 1;
		request.add(toAccNo2, constraints9);

		constraints9.gridx = 0;
		constraints9.gridy = 2;
		request.add(labelsum2, constraints9);

		constraints9.gridx = 1;
		request.add(sum2, constraints9);

		constraints9.gridx = 0;
		constraints9.gridy = 3;
		request.add(labelmonthly, constraints9);

		constraints9.gridx = 1;
		request.add(monthly, constraints9);

		constraints9.gridx = 1;
		constraints9.gridy = 4;
		constraints9.gridwidth = 2;
		constraints9.anchor = GridBagConstraints.CENTER;
		buttonRequest.addActionListener(this);
		request.add(buttonRequest, constraints9);

		constraints9.anchor = GridBagConstraints.WEST;
		constraints9.gridx = 0;
		constraints9.gridy = 6;
		request.add(labelstatus, constraints9);

		constraints9.gridx = 1;
		request.add(status, constraints9);

		constraints9.gridx = 1;
		constraints9.gridy = 7;
		request.add(labelapprove, constraints9);

		constraints9.gridx = 1;
		constraints9.gridy = 8;
		request.add(approve, constraints9);

		// set border for the panel
		request.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "REQUEST"));

		tabPane.add("Deposit", deposit);
		tabPane.add("Transfer", transfer);
		tabPane.add("Request", request);

		return tabPane;
	}

	public static void main(String[] args) {


		ClientGUI gui = new ClientGUI();

		gui.build();
	}


	private void discoverTransactionsService(String service_type){

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("TRANSACTION Service resolved: " + event.getInfo());

					transactionsServiceInfo = event.getInfo();

					int port = transactionsServiceInfo.getPort();

					String host = transactionsServiceInfo.getHostAddresses()[0];

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + transactionsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + host);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("TRANSACTION Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("TRANSACTION Service added: " + event.getInfo());
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

	private void discoverUserToolsService(String service_type) {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			jmdns.addServiceListener(service_type, new ServiceListener() {

				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("USER TOOLS Service resolved: " + event.getInfo());

					userToolsServiceInfo = event.getInfo();

					int port = userToolsServiceInfo.getPort();

					String host = userToolsServiceInfo.getHostAddresses()[0];

					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:" + event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + userToolsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + host);

				}

				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("USER TOOLS Service removed: " + event.getInfo());

				}

				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("USER TOOLS Service added: " + event.getInfo());
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
	
	private void build() {

		JFrame frame = new JFrame("eBanking Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the panel to add buttons
		JPanel panel = new JPanel();

		// Set the BoxLayout to be X_AXIS: from left to right
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		panel.setLayout(boxlayout);

		// Set border for the panel
		panel.setBorder(new EmptyBorder(new Insets(25, 50, 25, 50)));

		panel.add(getUserAccountGUIJTabbedPane());
		panel.add(getTransactionsGUIJTabbedPane());
		panel.add(getUserToolsGUIJTabbedPane());

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String label = button.getActionCommand();
// DEPOSIT ---------------------------------------------------------------------------------------			
		if (label.equals("Deposit")) {
			System.out.println("Deposits service invoked ...");

			String transactions_service_type = "_transactions._tcp.local.";

			// discover transactions service
			discoverTransactionsService(transactions_service_type);

			String host = transactionsServiceInfo.getHostAddresses()[0];
			int port = transactionsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			TransactionsGrpc.TransactionsBlockingStub blockingStub = TransactionsGrpc.newBlockingStub(channel);

			// preparing message to send
			DepositConfirmation response = blockingStub.deposit(DepositSum.newBuilder()
					.setAccNo(Integer.parseInt(entry1.getText())).setSum(Double.parseDouble(entry2.getText())).build());

			// Retrieving reply from service
			reply1.setText(response.getMessage());

			try {
				channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

// TRANSFER ---------------------------------------------------------------------------------------		
		} else if (label.equals("Transfer")) {
			System.out.println("Transfer service invoked ...");
			/*
			 * 
			 */
			String transactions_service_type = "_transactions._tcp.local.";

			// discover transactions service
			discoverTransactionsService(transactions_service_type);

			String host = transactionsServiceInfo.getHostAddresses()[0];
			int port = transactionsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			TransactionsGrpc.TransactionsStub asyncStub = TransactionsGrpc.newStub(channel);

			// preparing message to send
			StreamObserver<TransferConfirmation> response = new StreamObserver<TransferConfirmation>() {

				@Override
				public void onNext(TransferConfirmation response) {

					transMsg.setText(response.getConf());
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
					try {
						channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void onCompleted() {
					System.out.println("Client >>>>>>>>> STREAM END: All transfers have completed.");
					try {
						channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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

			String transactions_service_type = "_transactions._tcp.local.";

			// discover transactions service
			discoverTransactionsService(transactions_service_type);

			String host = transactionsServiceInfo.getHostAddresses()[0];
			int port = transactionsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			TransactionsGrpc.TransactionsStub asyncStub = TransactionsGrpc.newStub(channel);

			StreamObserver<RequestStatus> responseObserver = new StreamObserver<RequestStatus>() {

				@Override
				public void onNext(RequestStatus response) {

					status.setText(response.getStatus());
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();
					try {
						channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				@Override
				public void onCompleted() {
					System.out.println("Client >>>>>>>>> END OF STREAM: Money request completed");
					try {
						channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			};

			StreamObserver<RequestSum> requestObserver = asyncStub.request(responseObserver);

			requestObserver.onNext(RequestSum.newBuilder().setFromAccNo(Integer.parseInt(fromAccNo2.getText()))
					.setToAccNo(Integer.parseInt(toAccNo2.getText())).setSum(Double.parseDouble(sum2.getText()))
					.setMonthly(Boolean.parseBoolean(monthly.getText()))
					.setApprove(Boolean.parseBoolean(approve.getText())).build());

// LOGIN ---------------------------------------------------------------------------------------					
		} else if (label.equals("Login")) {
			System.out.println("Login service invoked ...");

			/*
			 * 
			 */

			String userAccount_service_type = "_userAccount._tcp.local.";

			// discover user account service
			discoverUserAccountService(userAccount_service_type);

			String host = userAccountServiceInfo.getHostAddresses()[0];
			int port = userAccountServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

			// preparing message to send
			LoginConfirmation response = blockingStub.login(LoginRequest.newBuilder().setUsername(username1.getText())
					.setPassword(password1.getText()).build());

			// Retrieving reply from service
			loginConf1.setText(response.getMessage());

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

// VIEW ACCOUNT  ---------------------------------------------------------------------------------------					
		} else if (label.equals("View Account")) {
			System.out.println("View Account service invoked ...");

			/*
			 * 
			 */

			String userAccount_service_type = "_userAccount._tcp.local.";

			// discover user account service
			discoverUserAccountService(userAccount_service_type);

			String host = userAccountServiceInfo.getHostAddresses()[0];
			int port = userAccountServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

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

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

// CHANGE PASSWORD  ---------------------------------------------------------------------------------------					

		} else if (label.equals("Change password")) {
			System.out.println("Password service invoked ...");

			/*
			 * 
			 */

			String userAccount_service_type = "_userAccount._tcp.local.";

			// discover user account service
			discoverUserAccountService(userAccount_service_type);

			String host = userAccountServiceInfo.getHostAddresses()[0];
			int port = userAccountServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			UserAccountGrpc.UserAccountBlockingStub blockingStub = UserAccountGrpc.newBlockingStub(channel);

			// preparing message to send
			PasswordConfirmation response = blockingStub
					.changePassword(PasswordRequest.newBuilder().setUsername(username.getText())
							.setCurrPass(currPass.getText()).setNewPass(newPass.getText()).build());

			// Retrieving reply from service
			passMsg.setText(response.getMessage());

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

// HELP BOT  ---------------------------------------------------------------------------------------					
		} else if (label.equals("Select")) {
			System.out.println("Help Bot service invoked ...");

			/*
			 * 
			 */

			int index = comboOperation.getSelectedIndex();
			Operation operation = Operation.forNumber(index);

			String userTools_service_type = "_userTools._tcp.local.";

			// discover user tools service
			discoverUserToolsService(userTools_service_type);

			String host = userToolsServiceInfo.getHostAddresses()[0];
			int port = userToolsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

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

			requestObserver.onNext(HelpRequest.newBuilder().setOperation(operation).build());

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

// VAULT ---------------------------------------------------------------------------------------
		} else if (label.equals("Vault")) {
			System.out.println("Vault service invoked ...");

			/*
			 * 
			 */

			String userTools_service_type = "_userTools._tcp.local.";

			// discover user tools service
			discoverUserToolsService(userTools_service_type);

			String host = userToolsServiceInfo.getHostAddresses()[0];
			int port = userToolsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);

			VaultConfirmation response = blockingStub.vault(VaultAccess.newBuilder()
					.setUsername(vaultUsername.getText()).setPassword(vaultPassword.getText())
					.setAccNo(Integer.parseInt(vaultaccno.getText())).setSum(Double.parseDouble(vaultsum.getText()))
					.setUnlockDate(vaultunlock.getText()).build());
			// Retrieving reply from service
			vaultConf.setText(response.getVaultConf());

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

// INTEREST CALCULATOR ---------------------------------------------------------------------------------------

		} else if (label.equals("Calculate")) {
			System.out.println("interest calculator service invoked ...");

			/*
			 * 
			 */

			String userTools_service_type = "_userTools._tcp.local.";

			// discover user tools service
			discoverUserToolsService(userTools_service_type);

			String host = userToolsServiceInfo.getHostAddresses()[0];
			int port = userToolsServiceInfo.getPort();

			ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

			UserToolsGrpc.UserToolsBlockingStub blockingStub = UserToolsGrpc.newBlockingStub(channel);

			CalcResponse response = blockingStub.interestCalc(CalcRequest.newBuilder().setAccType(acctype.getText())
					.setAccess(access.getText()).setSum(Double.parseDouble(calcsum.getText()))

					.build());
			
			// Retrieving reply from service
			interest.setText((String.valueOf(response.getInterest())));
			error.setText(response.getError());
		

//			try {
//				channel.shutdown().awaitTermination(60, TimeUnit.SECONDS);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

		}

	}

}
