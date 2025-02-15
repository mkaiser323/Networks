package Homework4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainWindow extends JFrame implements ActionListener, KeyListener{

	
	private JTextField ipTxt;
	private JLabel ipLbl;
	private JButton startChatBtn;
	private JPanel mainPanel;
	
	private static int[] ip = {127, 0, 0, 1};
	public static int port = 64000;
	
	public static DatagramSendReceive receiveThread = new DatagramSendReceive(true, port, ip[0], ip[1], ip[2], ip[3]);
	
	public static void main(String[] args){
		new MainWindow();//main window to type in ip addresses and open up chat sessions
		receiveThread.start();//main thread for listening to new devices
	}
	
	/** default values for main window **/
	public MainWindow()
	{
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initializeComponents();
		pack();
		setVisible(true);
		setResizable(false);
		
	}
	
	/** Initialize all default components of the chat program **/
	private void initializeComponents() 
	{
		ipTxt = new JTextField(20);
		Font font = new Font("Verdana", Font.BOLD, 20);
		ipTxt.setFont(font);
		ipTxt.addKeyListener(this);
		ipLbl = new JLabel("Enter IP Address:");
		startChatBtn = new JButton("Start Chat");
		startChatBtn.addActionListener(this);
		
		mainPanel = new JPanel();
		mainPanel.add(ipLbl);
		mainPanel.add(ipTxt);
		mainPanel.add(startChatBtn);
		
		add(mainPanel);
		
	}
	
	
	private void startNewChat(){
		if(!ipTxt.getText().equals("")){
			
			//byte[] ip = {127, 0, 0, 1}; //ChatWindow.ipBytes(ipTxt.getText());
			
			
			System.out.println(ip);
			
			//create SendThread and receive thread for the new chat window:
			/**
			 * remember to change the ip addresses to be set dynamically depending on the ip address typed in the MainWindow
			 */			
			System.out.println("Right before creating window: " +ipTxt.getText());
			new ChatWindow(ipTxt.getText(), ipTxt.getText(), port);
			System.out.println("Initial Chat Window created");
		}
	}
	
	private void boradcast(String name){
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER){
			startNewChat();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER){
			startNewChat();
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		startNewChat();
	}	
}
