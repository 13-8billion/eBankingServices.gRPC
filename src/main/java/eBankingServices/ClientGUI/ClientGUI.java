package eBankingServices.ClientGUI;

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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import eBankingServices.ClientGUI.ClientGUI;
import eBankingServices.Transactions.DepositConfirmation;
import eBankingServices.Transactions.DepositSum;
import eBankingServices.Transactions.TransactionsGrpc;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsBlockingStub;
import eBankingServices.Transactions.TransactionsGrpc.TransactionsStub;
import eBankingServices.UserAccount.UserAccountGrpc;
import eBankingServices.UserTools.UserToolsGrpc;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class ClientGUI implements ActionListener{
	
//	private static TransactionsBlockingStub blockingStub;
//	private static TransactionsStub asyncStub;
//	
	
	private JTextField entry1, entry2, entry3, reply1;
//	private JTextArea reply1;
//	private JTextField entry2, reply2;
//	private JTextField entry3, reply3;
//	private JTextField entry4, reply4;


	private JPanel getTransactionsJPanel() {

		JPanel panel = new JPanel();
		
		BoxLayout boxlayoutY = new BoxLayout(panel, BoxLayout.Y_AXIS);
		
		JLabel depositLabel = new JLabel("DEPOSIT MONEY             |");
		panel.add(depositLabel);
		panel.add(Box.createRigidArea(new Dimension(50, 0)));
		panel.setLayout(boxlayoutY);

		
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

		JLabel label = new JLabel("Account No:")	;
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
		
		JLabel label3 = new JLabel("DepositID:")	;
		panel.add(label3);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		entry3 = new JTextField("",10);
		panel.add(entry3);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		

		JButton button = new JButton("Deposit");
		button.addActionListener(this);
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));

		reply1 = new JTextField("", 10);
		reply1.setEditable(false);
		panel.add(reply1);

		panel.setLayout(boxlayout);

		return panel;

	}

//	private JPanel getUserAccountJPanel() {
//
//		JPanel panel = new JPanel();
//
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
//
//		JLabel label = new JLabel("Enter value")	;
//		panel.add(label);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		entry2 = new JTextField("",10);
//		panel.add(entry2);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		JButton button = new JButton("Invoke Service 2");
//		button.addActionListener(this);
//		panel.add(button);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		reply2 = new JTextField("", 10);
//		reply2 .setEditable(false);
//		panel.add(reply2 );
//
//		panel.setLayout(boxlayout);
//
//		return panel;
//
//	}
//
//	private JPanel getUserToolsJPanel() {
//
//		JPanel panel = new JPanel();
//
//		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
//
//		JLabel label = new JLabel("Enter value")	;
//		panel.add(label);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//		entry3 = new JTextField("",10);
//		panel.add(entry3);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		JButton button = new JButton("Invoke Service 3");
//		button.addActionListener(this);
//		panel.add(button);
//		panel.add(Box.createRigidArea(new Dimension(10, 0)));
//
//		reply3 = new JTextField("", 10);
//		reply3 .setEditable(false);
//		panel.add(reply3 );
//
//		panel.setLayout(boxlayout);
//
//		return panel;
//
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
	
		panel.add( getTransactionsJPanel() );
//		panel.add( getUserAccountJPanel() );
//		panel.add( getUserToolsJPanel() );

		// Set size for the frame
		frame.setSize(300, 300);

		// Set the window to be visible as the default to be false
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	

	
//			JButton btnDeposit = new JButton("Deposit");
//			btnDeposit.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//			
//			int accNo = Integer.parseInt(entry1.getText());
//			int sum = Integer.parseInt(entry2.getText());
//			int depositID = Integer.parseInt(entry3.getText());
//
//			DepositSum req = DepositSum.newBuilder()
//					.setAccNo(accNo)
//					.setSum(sum)
//					.setDepositID(depositID)
//					.build();
//
//			DepositConfirmation response = blockingStub.deposit(req);
//
//			reply1.append("reply:"+ response.getMessage());
//			
//			System.out.println("res: " + response.getMessage());
//				}
//			});
//}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

			/*
			 * 
			 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton)e.getSource();
				String label = button.getActionCommand();  
		
				if (label.equals("Deposit")) {
					System.out.println("Deposits service to be invoked ...");
					
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050)
					.usePlaintext()
					.build();
			
			TransactionsGrpc.TransactionsBlockingStub blockingStub = TransactionsGrpc.newBlockingStub(channel);

			//preparing message to send
			DepositSum request = DepositSum.newBuilder()
					.setAccNo(Integer.parseInt(entry1.getText()))
					.setSum(Integer.parseInt(entry2.getText()))
					.setDepositID(Integer.parseInt(entry3.getText()))
					.build();

			//Retrieving reply from service
			DepositConfirmation response = blockingStub.deposit(request);
			//withWaitForReady().

			reply1.setText(response.getMessage());
		
			}
			else if (label.equals("Transfer")) {
			System.out.println("Transfer service to be invoked ...");
			}
			
}





		
			/*
			 * 
			 */
//			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
//			Service2Grpc.Service2BlockingStub blockingStub = Service2Grpc.newBlockingStub(channel);
//
//			//preparing message to send
//			ds.service2.RequestMessage request = ds.service2.RequestMessage.newBuilder().setText(entry2.getText()).build();
//
//			//retreving reply from service
//			ds.service2.ResponseMessage response = blockingStub.service2Do(request);
//
//			reply2.setText( String.valueOf( response.getLength()) );
//			
//		}else if (label.equals("Invoke Service 3")) {
//			System.out.println("service 3 to be invoked ...");

		
			/*
			 * 
			 */
//			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
//			Service3Grpc.Service3BlockingStub blockingStub = Service3Grpc.newBlockingStub(channel);
//
//			//preparing message to send
//			ds.service3.RequestMessage request = ds.service3.RequestMessage.newBuilder().setText(entry3.getText()).build();
//
//			//retreving reply from service
//			ds.service3.ResponseMessage response = blockingStub.service3Do(request);
//
//			reply3.setText( String.valueOf( response.getLength()) );
//		
//		}else if (label.equals("Invoke Service 4")) {
//			System.out.println("service 4 to be invoked ...");

		
			/*
			 * 
			 */
//			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50054).usePlaintext().build();
//			Service4Grpc.Service4BlockingStub blockingStub = Service4Grpc.newBlockingStub(channel);
//
//			//preparing message to send
//			ds.service4.RequestMessage request = ds.service4.RequestMessage.newBuilder().setText(entry4.getText()).build();
//
//			//retreving reply from service
//			ds.service4.ResponseMessage response = blockingStub.service4Do(request);
//
//			reply4.setText( String.valueOf( response.getLength()) );
//		
//		}else{
//			
	

}


