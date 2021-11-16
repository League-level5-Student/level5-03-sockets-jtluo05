package _02_Chat_Application;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	static JTextArea tf1 = new JTextArea(2,45);
	static JTextArea tf2 = new JTextArea(2,45);
	static JLabel label1= new JLabel();
	static JLabel label2= new JLabel();
	static JButton button=new JButton("SEND");
	static JPanel panel=new JPanel();
	Server server;
	Client client;
	static String message="";
	static String fullmessage="";
	public static void main(String[] args) {
		new ChatApp();
	}
	ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			panel.add(tf1);
			panel.add(button);
			panel.add(label1);
			label1.setSize(100, 100);
			add(panel);
			button.addActionListener((e)->{
				message=tf1.getText();
				fullmessage+="\n"+"Server: "+message;
				tf1.setText("");
				server.sendMessage();
				label1.setText(fullmessage);
				System.out.println(fullmessage);
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
				//currentText=tf2.getText();
				//message=" Client: "+currentText;
				tf2.setText("");
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
