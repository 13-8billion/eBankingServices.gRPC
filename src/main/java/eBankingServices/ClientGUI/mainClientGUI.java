package eBankingServices.ClientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;
import eBankingServices.Transactions.TransferConfirmation;
import eBankingServices.Transactions.TransferSum;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;


public class mainClientGUI {

	private static TransactionsBlockingStub blockingStub;
	private static TransactionsStub asyncStub;

	private ServiceInfo transactionsInfo;
	
	private JFrame frame;
	private JTextField entryAccNo, entrySum, entryDepositID, toAccNo, fromAccNo, sum, transferID;
	private JTextArea replyMessage, transferMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainClientGUI window = new mainClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainClientGUI() {
		
		String transactions_service_type = "_transactions._tcp.local.";
		
		// discover math service
		discoverTransactions(transactions_service_type);
		
//		String host = transactionsInfo.getHostAddresses()[0];
		int port = 50050;
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", port)
				.usePlaintext()
				.build();

		//stubs -- generate from proto
		blockingStub = TransactionsGrpc.newBlockingStub(channel);

		asyncStub = TransactionsGrpc.newStub(channel);

		
		initialize();
		initialize2();
	}

	
	
	private void discoverTransactions(String service_type) {
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

				
			jmdns.addServiceListener(service_type, new ServiceListener() {
				
				@Override
				public void serviceResolved(ServiceEvent event) {
					System.out.println("Transaction Service resolved: " + event.getInfo());

					transactionsInfo = event.getInfo();

					int port = transactionsInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + transactionsInfo.getNiceTextString());
					System.out.println("\t host: " + transactionsInfo.getHostAddresses()[0]);
				
					
				}
				
				@Override
				public void serviceRemoved(ServiceEvent event) {
					System.out.println("Transaction Service removed: " + event.getInfo());

					
				}
				
				@Override
				public void serviceAdded(ServiceEvent event) {
					System.out.println("Transaction Service added: " + event.getInfo());

					
				}
			});
			
			// Wait a bit
			Thread.sleep(2000);
			
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
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("eBANKING APPLICATION");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Account No");
		panel_service_1.add(lblNewLabel_1);
		
		entryAccNo = new JTextField();
		panel_service_1.add(entryAccNo);
		entryAccNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		panel_service_1.add(lblNewLabel_2);
		
		entrySum = new JTextField();
		panel_service_1.add(entrySum);
		entrySum.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("DepositID");
		panel_service_1.add(lblNewLabel_2);
		
		entryDepositID = new JTextField();
		panel_service_1.add(entryDepositID);
		entryDepositID.setColumns(10);
		
		
//		JComboBox comboOperation = new JComboBox();
//		comboOperation.setModel(new DefaultComboBoxModel(new String[] {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"}));
//		panel_service_1.add(comboOperation);
	
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int accNo = Integer.parseInt(entryAccNo.getText());
				int sum = Integer.parseInt(entrySum.getText());
				int depositID = Integer.parseInt(entryDepositID.getText());

//				int index = comboOperation.getSelectedIndex();
//				Operation operation = Operation.forNumber(index);
				
				DepositSum req = DepositSum.newBuilder()
						.setAccNo(accNo)
						.setSum(sum)
						.setDepositID(depositID)
						.build();

				DepositConfirmation response = blockingStub.deposit(req);

				replyMessage.append("reply:"+ response.getMessage());
				
				System.out.println("res: " + response.getMessage());

			}
		});
		panel_service_1.add(btnDeposit);
		
		replyMessage = new JTextArea(3, 20);
		replyMessage .setLineWrap(true);
		replyMessage.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(replyMessage);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);
		
		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
			
	}
	
	private void initialize2() {
		frame = new JFrame();
		frame.setTitle("eBANKING APPLICATION");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(bl);
		
		JPanel panel_service_1 = new JPanel();
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("To Account No");
		panel_service_1.add(lblNewLabel_1);
		
		toAccNo = new JTextField();
		panel_service_1.add(toAccNo);
		toAccNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("From Account No");
		panel_service_1.add(lblNewLabel_1);
		
		fromAccNo = new JTextField();
		panel_service_1.add(fromAccNo);
		entryAccNo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		panel_service_1.add(lblNewLabel_2);
		
		sum = new JTextField();
		panel_service_1.add(sum);
		sum.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("TransferID");
		panel_service_1.add(lblNewLabel_2);
		
		transferID = new JTextField();
		panel_service_1.add(transferID);
		transferID.setColumns(10);
		
		
//		JComboBox comboOperation = new JComboBox();
//		comboOperation.setModel(new DefaultComboBoxModel(new String[] {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"}));
//		panel_service_1.add(comboOperation);
	
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int tAccNo = Integer.parseInt(toAccNo.getText());
				int fAccNo = Integer.parseInt(fromAccNo.getText());
				int sum1 = Integer.parseInt(sum.getText());
				int transferIDs = Integer.parseInt(transferID.getText());

//				int index = comboOperation.getSelectedIndex();
//				Operation operation = Operation.forNumber(index);
				
				StreamObserver<TransferConfirmation> req = TransferSum.newBuilder()
						.setToAccNo(tAccNo)
						.setFromAccNo(fAccNo)
						.setSum(sum1)
						.setTransferID(transferIDs)
						.build();

				TransferConfirmation response = asyncStub.transfer(req);

				transferMsg.append("reply:"+ response.getMessage());
				
				System.out.println("res: " + response.getMessage());

			}
		});
		panel_service_1.add(btnTransfer);
		
		transferMsg = new JTextArea(3, 20);
		transferMsg .setLineWrap(true);
		transferMsg.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(transferMsg);
		
		//textResponse.setSize(new Dimension(15, 30));
		panel_service_1.add(scrollPane);
		
		
		JPanel panel_service_2 = new JPanel();
		frame.getContentPane().add(panel_service_2);
		
		JPanel panel_service_3 = new JPanel();
		frame.getContentPane().add(panel_service_3);
		
		
		
	}

}
