package _02_Chat_Application;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	JTextField tf1 = new JTextField(45);
	JTextField tf2 = new JTextField(45);
	JButton button=new JButton("SEND");
	JPanel panel=new JPanel();
	Server server;
	Client client;
	static String message="";
	static String currentText="";
	public static void main(String[] args) {
		new ChatApp();
	}
	ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			panel.add(tf1);
			panel.add(button);
			add(panel);
			button.addActionListener((e)->{
				currentText=tf1.getText();
				message=" Server: "+currentText;
				tf1.setText("");
				server.sendMessage();
				System.out.println(message);
			});
			setVisible(true);
			setSize(600, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			panel.add(tf2);
			panel.add(button);
			add(panel);
			button.addActionListener((e)->{
				currentText=tf2.getText();
				message=" Client: "+currentText;
				tf1.setText("");
				client.sendMessage();
				System.out.println(message);
			});
			setVisible(true);
			setSize(600, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
