package Homework5;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea; //display text
import javax.swing.JTextField; //write text

public class SendAndReceive 
{
	static JFrame frame;
	static JPanel textFieldPanel = new JPanel();
	static TextArea textTypeArea;
	static TextField textDisplay = new TextField();
	//	static GridBagConstraints constraints = new GridBagConstraints();

	public static void displayGUI() 
	{
		JFrame frame = new JFrame("Window Title");

		frame.setSize(600, 400);

		frame.setResizable(true);

		//	 frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


		// create and format a JPanel for input field and the Send button

		JPanel textFieldPanel = new JPanel(new BorderLayout());
		JButton sendButton = new JButton();
		//textDisplay = new TextField();
		textTypeArea = new TextArea();

		//textDisplay.addActionListener(this);


		//	 textFieldPanel.add(textDisplay, BorderLayout.CENTER);


		textFieldPanel.add(sendButton, BorderLayout.EAST);

		frame.add(textFieldPanel, BorderLayout.SOUTH);

		frame.add(textTypeArea, BorderLayout.CENTER);

		frame.setVisible(true);

		//		frame = new JFrame("Window Title");
		//		frame.setLayout(new BorderLayout());
		//		
		//		textField = new JPanel();
		//
		//	    
		//	    //frame.add(box, BorderLayout.NORTH);
		//		
		////		//set layout for both panels
		////		northPanel.setLayout(new BorderLayout());
		////		southPanel.setLayout(new BorderLayout());
		//
		//		//place each panel in separate locations
		//		frame.add(textField, BorderLayout.NORTH);	
		//		textField.add(new JButton(), BorderLayout.SOUTH);
		//
		//		frame.setSize(600, 400);
		//
		//		frame.setLocationRelativeTo(null);
		//
		////		frame.getContentPane().add(BorderLayout.CENTER,northPanel);
		//		//frame.getContentPane().add(BorderLayout.SOUTH,southPanel);
		//		
		//		//frame.getContentPane().add(southPanel); 
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		//		frame.setVisible(true);


	}

	public static void receiveMessages()
	{
		textFieldPanel.add(textDisplay, BorderLayout.CENTER);

		//displayText = new TextField();
		textDisplay.setPreferredSize(new Dimension(600, 400));

		textFieldPanel.add(textDisplay);

		//	textDisplay.setEditable(false);
		//displayText.setText(typeText.getText());

	}

	public static void sendMessages()
	{
		////		typeText = new JTextArea();
		//		//textField.setPreferredSize(new Dimension(100, 100));
		//		textField.add(typeText);


	}

	public static void main (String[] args)
	{
		displayGUI();
		receiveMessages();
		//sendMessages();

		//		JLabel label = new JLabel("Buy some chocolate below!");
		//
		//		JButton button = new JButton();
		//
		//		button.setText("Chocolate Button!");

		//panel.add(label,BorderLayout.NORTH);

		//panel.add(button,BorderLayout.SOUTH);


	}



}
