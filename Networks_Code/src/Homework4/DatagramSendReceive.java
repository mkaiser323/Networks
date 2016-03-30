package Homework4;

import java.net.*;
import java.util.Scanner;
import java.io.*;


/*
 * Homework 4:
 * Write a Java program that uses java.net.DatagramSocket which is 
 * able to send and receive java.net.DatagramPackets. 
 * The content of the packets will be Strings.
 *  Your class must be named DatagramSendReceive. 
 */


public class DatagramSendReceive extends Thread {

	static DatagramSocket socket;
	static int port; 
	DatagramSendReceive receiveThread, sendThread;
	boolean isReceive;//true for receiving thread, false for sending thread
	byte[] ip = {(byte)127, (byte)0, (byte)0, (byte)1};



	/**
	 * This constructor is used to create and start a thread. 
	 * 
	 * @param isReceive: if true, the thread will be treated as a receiving thread. 
	 * @param port: The port number will be used to create the socket
	 * 
	 * the ip address of the other device that you are communicating with, in the form a.b.c.d
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public DatagramSendReceive(boolean isReceive, int port, int a, int b, int c, int d){
		this.isReceive = isReceive;
		setSocket(port);
		setIP(a, b, c, d);
		//this.start();
	}

	public void setSocket(int port){
		
		if(socket != null){
			socket.close();
		}
		
		try {
			createSocket(port);
			DatagramSendReceive.port = port;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setIP(int a, int b, int c, int d){
		ip[0] = (byte)a;
		ip[1] = (byte)b;
		ip[2] = (byte)c;
		ip[3] = (byte)d;
	}


	private void createSocket(int port) throws SocketException{
		socket = new DatagramSocket(port);	
	}


	@Override
	public void run() {
		
		if(isReceive){
			receive();
					} else {
			send();
		}
	}
	
	private static DatagramPacket createDatagram(String message, int port, byte[] ip) throws UnknownHostException{
		InetAddress ip_address = InetAddress.getByAddress(ip);
		byte[] message_bytes = message.getBytes();
		DatagramPacket datagramPacket = new DatagramPacket(message_bytes, message_bytes.length, ip_address, port);
		return datagramPacket;
	}

	private void send(){
		String message;
		while(true){
			try {
				System.out.println("Type a message and hit enter to send. Type 'quit' to quit: ");
				Scanner keyboard = new Scanner(System.in);
				message = keyboard.nextLine();
				if (message.equalsIgnoreCase("quit")){
					keyboard.close();
					socket.close();
					break;
				}
				DatagramPacket datagramPacket = createDatagram(message, port, ip);
				socket.send(datagramPacket);

			} catch (SocketException e) {
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void receive(){
		DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
		try {
			createSocket(port);
			System.out.println("Receive Thread is ready. Waiting for messages...");
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {

				socket.receive(receivePacket);

				String message = new String(receivePacket.getData());
				System.out.println(message);
				receivePacket = new DatagramPacket(new byte[1024], 1024);
				if(message.equals("bye")){
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				socket.close();
			}
		}
	
	}
}
