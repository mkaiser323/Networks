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
	private DatagramPacket datagramPacket;//this will be used to send the datagram packet through the thread

	private byte[] otherIp;
	private int port = 64000;

	public ChatWindow(String title, DatagramSendReceive sendThread, DatagramSendReceive receiveThread){

		this.otherIp = ipBytes(title);
		this.sendThread = sendThread;//for now, I am not starting the sendthread because i do not need its run method
		this.receiveThread = receiveThread;
		receiveThread.start();//starting this thread right away because it has to start listening right away
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initializeComponents();
		setVisible(true);
	}

	private void initializeComponents(){

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


	private void sendMessage() throws IOException, UnknownHostException{

		//first, update the chat window
		String message = typeMessage.getText();
		messageArea.setText(messageArea.getText() + "\n"+ message);

		typeMessage.setText("");

		//next, send it through the socket in the following format: thread.socket.send(datagramPacket)
		sendThread.socket.send(DatagramSendReceive.createDatagram(message, port, otherIp));


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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private byte[] ipBytes(String ip_string){
		String[] ip_string_array = ip_string.split(".");
		byte[] ip_bytes = new byte[4];
		for(int i = 0; i < ip_bytes.length; i++){
			ip_bytes[i]=(byte) Integer.parseInt(ip_string_array[i]);
		}
		return ip_bytes;
	}
	
}
