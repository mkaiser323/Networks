package Homework5;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener, KeyListener{
	private JTextField ipTxt;
	private JPanel mainPanel;
	private JLabel ipLbl;
	private JButton startChatBtn;
	private DatagramSendReceive server;
	private static int position = 0; //position of classmate in hashmap
	private static HashMap<Integer, String> classmates = new HashMap<Integer, String>(); //list of classmates
	private DatagramSocket sendSocket;
	private InetAddress otherIP;
	private int otherPort;
	
	/** default values for main window **/
	public MainWindow()
	{
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initializeComponents();
		pack();
		setVisible(true);
		setResizable(false);
		
		server = new DatagramSendReceive();
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
	
	private void startChat()
	{
		//don't start a chat window with empty string
		if(! ipTxt.getText().equals(""))
		{
			//set name of the person to title of window 
			String title = ipTxt.getText();
			
			//save name of classmate to the list
			classmates.put(position, title);
			position++;
			
			//create chat new window
			new ChatWindow(title);
			
//			InetAddress myAddress = InetAddress.getLocalHost();
			//clear the text in the text box 
			ipTxt.setText("");
			System.out.println(classmates.values());
		}
	}

	/** create new chat window when "start chat" button is pressed **/
	public void actionPerformed(ActionEvent e) 
	{
		startChat();
	}
	
	public static void main(String[] args) 
	{
		new MainWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		//start chat after enter key is pressed
		if(code == KeyEvent.VK_ENTER)
		{
			startChat();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {	
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
