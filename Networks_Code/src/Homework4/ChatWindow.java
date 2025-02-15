package Homework4;

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
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatWindow extends JFrame implements KeyListener, ActionListener{

	private JTextField typeMessage;
	private JTextArea messageArea;
	private JButton sendBtn;
	private JButton endBtn;
	private JButton IpLookupBtn;
	private JPanel mainPanel;

	private DatagramSendReceive sendThread;//this will be used to access the associated thread
	private DatagramSendReceive receiveThread;//this will be used to access the associated thread

	private String otherIp;
	private int port = MainWindow.port;

	public ChatWindow(String otherIp, String ip, int port){
		
		String title = "IP: " + ip + " Port: " + port;
		this.otherIp = otherIp;
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		initializeComponents();
		setVisible(true);
		DatagramSendReceive.chatWindows.put(ip, this);
	}

	private void initializeComponents(){

		typeMessage = new JTextField();
		messageArea = new JTextArea();
		Font font = new Font("Verdana", Font.BOLD, 20);
		messageArea.setFont(font);
		typeMessage.setFont(font);
		typeMessage.addKeyListener(this);
		//messageArea.setBackground(Color.cyan);
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


	private void sendMessage() throws IOException, UnknownHostException{

		//first, update the chat window
		String message = typeMessage.getText();
		messageArea.setText(messageArea.getText() + "\n"+ message);

		typeMessage.setText("");

		//next, send it through the socket in the following format: thread.socket.send(datagramPacket)
		print("sending to port " + port + ", " + "ip " + otherIp);
		//byte[] otherIp = {(byte)127,(byte)0,(byte)0, (byte)1};
				
		DatagramSendReceive.socket.send(DatagramSendReceive.createDatagram(message, port, otherIp));
		print("message sent to port " + port + ", " + "ip " + otherIp);
	}
	
	
	public void receive(String message){
		messageArea.setText(messageArea.getText() + "\n"+ message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			sendMessage();
		} catch(Exception exception){

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER){
			try{
				sendMessage();
			} catch(Exception exception){
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
				int code = e.getKeyCode();
				if(code == KeyEvent.VK_ENTER){
					try{
						sendMessage();
					} catch(Exception exception){
					}
				}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static byte[] ipBytes(String ip_string){
		String[] ip_string_array = ip_string.split(".");
		byte[] ip_bytes = new byte[4];
		print("ip string: "+ip_string);
		print("inside ipBytes method");
		print("Array length: "+ ip_string.split(".").length);
		for(int i = 0; i < ip_string_array.length; i++){
			print("ip[i]: "+(byte) Integer.parseInt(ip_string_array[i]) + "");
			ip_bytes[i]=(byte) Integer.parseInt(ip_string_array[i]);
		}
		return ip_bytes;
	}
	
	static boolean debugging =  true;
	
	private static void print(String s){
		if(debugging){
			System.out.println(s);
		}
	}
	
	
	private static void ip(byte[] ip){
		System.out.println("IP: " +ip[0] + "." +ip[1] + "." +ip[2] + "." +ip[3]);
	}
	
}
