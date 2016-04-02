package Homework4;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/*
 * Homework 5:
 * 1- main window for initiating chat sessions
 * 2- separate windows for each chat session (this class)
 * 3- each window will have the following components:
 * 	a- title of the window will be the IP address of the other side of the message
 * 	b- a section to display the messaging interaction; both sent and received messages
 * 	c- section to type the reply
 * 	d- button to send the reply
 * 	e- button to close and end the messaging session
 * 
 */



/**
 * This window will open up every time a new chat session is initiated
 * @author mahedikaiser
 *
 */

public class NewSession extends JFrame {

	static final int width = 800, height = 600;
	
	public NewSession(String title){
		Frame window = new Frame(title);
		//window.add(this);
		window.setSize(width, height);
		window.setVisible(true);
		window.setResizable(true);
		window.addWindowListener(new WindowAdapter() { //allows window to close on X
		      public void windowClosing(WindowEvent e) {System.exit(0);} 
		    });
	}
	
	public static void main(String[] args){
		NewSession test = new NewSession("test");
	}
	
}
