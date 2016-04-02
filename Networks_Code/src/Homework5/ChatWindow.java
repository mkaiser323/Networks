package Homework5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.*;

public class ChatWindow extends JFrame implements ActionListener, KeyListener 
{
	private JPanel mainPanel;
	private JTextField typeMessage;
	private JTextArea messageArea;
	private JButton sendBtn, endBtn,IpLookupBtn;
	private DatagramSocket sendSocket;
	private InetAddress otherIP;
	private int otherPort;
	private static DatagramSendReceive server;
	//private DatagramSocket socket;
	private String title;
	
	public ChatWindow(String title) 
	{
//		this.sendSocket = sendSocket;
//		this.otherIP = otherIP;
//		this.otherPort = otherPort;
		
		//setTitle("IP = " + otherIP.getHostAddress() + ", Port = " + otherPort);
		this.title = title;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initializeComponents();
		setVisible(true);
		
		
	}
	
	/** Initialize all default components of the chat program **/
	private void initializeComponents()
	{
		typeMessage = new JTextField();
		messageArea = new JTextArea();
		Font font = new Font("Verdana", Font.BOLD, 20);
		messageArea.setFont(font);
		typeMessage.setFont(font);
		typeMessage.addKeyListener(this);
		messageArea.setBackground(Color.cyan);
		messageArea.setEditable(false);
		
		sendBtn = new JButton("Send");
		endBtn = new JButton("End Chat");
		IpLookupBtn = new JButton("Lookup IP Address");
		sendBtn.addActionListener(this);
		endBtn.addActionListener(this);
		IpLookupBtn.addActionListener(this);
		
		JPanel typeMessagePanel = new JPanel(new BorderLayout());
		typeMessagePanel.add(typeMessage, BorderLayout.CENTER);
		typeMessagePanel.add(IpLookupBtn, BorderLayout.WEST);
		typeMessagePanel.add(sendBtn, BorderLayout.EAST);
		typeMessagePanel.add(endBtn, BorderLayout.SOUTH);
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(messageArea, BorderLayout.CENTER);
		mainPanel.add(typeMessagePanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
	}

	/** send message to classmate 
	 * @throws IOException **/
	private void sendMessage() throws IOException
	{
		DatagramSendReceive datagramClient = new DatagramSendReceive();

		String message = typeMessage.getText();
		
		//get ip address desired
		//String ip = MainWindow.ipTxt.getText();
		
		//send message on port 3000
		datagramClient.send(64000, message, title);  
		
	
		if(!messageArea.getText().equals(""))
		{
			message = "\n" +message;
		}
	
		messageArea.setText(messageArea.getText()+message);
		
		//reset text in type
		typeMessage.setText("");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//if send button is pressed, send message
		if(e.getSource() == sendBtn && ! typeMessage.getText().equals(""))
		{
			try {
				sendMessage();
			} catch (IOException exception) {
				
				exception.printStackTrace();
			}
		}
		
		//if end button is pressed, end chat session
		else if (e.getSource() == endBtn)
		{
			setVisible(false);
			dispose();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		//send message after enter key is pressed
		if(code == KeyEvent.VK_ENTER)
		{
			try {
				sendMessage();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	public void receiveMessage() 
	{
		final DatagramSendReceive datagramServer = new DatagramSendReceive();

		new Thread() {
			public void run() {
				datagramServer.receive(64000);

			}
		}.start();
	}
	
//	public static void main(String[] args) throws Exception
//	{
//
////		DatagramSendReceive datagramServer = new DatagramSendReceive();
////
////		new Thread() {
////			public void run() {
////				datagramServer.receive(3000);
////
////			}
////		}.start();
//
////		DatagramSendReceive datagramClient = new DatagramSendReceive();
////
////		//send message on port 3000
////		datagramClient.send(3000, "This is the message.");
//	}
	
}
