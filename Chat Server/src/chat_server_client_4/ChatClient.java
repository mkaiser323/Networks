package chat_server_client_4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 
 * Chat Client Version 4
 * sends and receives messages unlike the first chat client version
 * It creates a separate thread to receive the messages from the server
 *
 */
public class ChatClient implements Runnable{

	private Socket socket = null;
	private Thread thread = null;
	//private DataInputStream console = null;
	BufferedReader console = null; //d = new BufferedReader(new InputStreamReader(in))
	private DataOutputStream streamOut = null;
	private ChatClientThread client = null;
	private boolean done = false;
	private String line="";
	private boolean nameHasNotBeenSet = true;
	private UserName userName = null;
	
	public ChatClient(String serverName, int serverPort){
		System.out.println("Establishing connection to server "+serverName+ " on port "+ serverPort + " please wait...");
		try{
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected to socket: " + socket.getLocalPort());
			start();
			System.out.println("Your username is User" + socket.getLocalPort());
			System.out.println("To send a Private Message:"
					+ "\n1. Type 'PM' followed by a space"
					+ "\n2. Type in the ID number of the recipient, followed by another space"
					+ "\n3. Type in your message and hit enter");
		}
		catch(UnknownHostException uhe){
			System.out.println("Error Unknown Host: "+ uhe.getMessage());
		}
		catch(IOException ioe){
			System.out.println("Unexpected exception: "+ ioe.getMessage());
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub		
		while((thread!=null) && (!line.equalsIgnoreCase("bye"))){
			try{
				line = console.readLine();
				streamOut.writeUTF(line);
				streamOut.flush();
			}
			catch(IOException ioe){
				System.err.println("Sending error: " + ioe.getMessage());
			}
		}
	}

	public void start()throws IOException{
		console = new BufferedReader(new InputStreamReader(System.in));
		streamOut = new DataOutputStream(socket.getOutputStream());
		if(thread==null){
			client = new ChatClientThread(this, socket);//receives input from the server
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void handle(String msg){
		
		if(msg.equalsIgnoreCase("bye")){
			line="bye";
			stop();//not the deprecated stop().. but the ChatClient class stop method
		}
		else{
			System.out.println(msg);
		}
	}
	
	public void stop(){
		done=true;//set flag done to true instead of the deprecated .stop
		if(thread!=null){
			thread=null;
		}
		try{
			if(console !=null) console.close();
			if(streamOut !=null) streamOut.close();
			if(socket !=null) socket.close();
			if(client!=null){
				client = null;// = null; //instead of deprecated stop()
			}
		}catch(IOException ioe){
			System.err.println("Error closing....");
		}
	}
	
	public static void main(String[] args){
		ChatClient client = null;
		if(args.length !=2){
			System.out.println("To chat you must specify both a host and a port ");
		}
		else{
			client = new ChatClient(args[0],Integer.parseInt(args[1]));
		}
	}
}