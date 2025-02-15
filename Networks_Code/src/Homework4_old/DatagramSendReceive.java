package Homework4_old;

import java.net.*;
import java.io.*;


/*
 * Homework 4:
 * Write a Java program that uses java.net.DatagramSocket which is 
 * able to send and receive java.net.DatagramPackets. 
 * The content of the packets will be Strings.
 *  Your class must be named DatagramSendReceive. 
 */


public class DatagramSendReceive extends Thread {

	
	DatagramSocket socket;
	byte[] loopback = {(byte)127, (byte)0, (byte)0, (byte)1};
	byte[] ip = {(byte)192, (byte)168, (byte)1, (byte)109};
	
	
	
	public void createSocket(int port) throws SocketException{
		socket = new DatagramSocket(port);	
	}

	
	public static DatagramPacket createDatagram(String message, int port, byte[] ip) throws UnknownHostException{
		InetAddress ip_address = InetAddress.getByAddress(ip);
		byte[] message_bytes = message.getBytes();
		DatagramPacket datagramPacket = new DatagramPacket(message_bytes, message_bytes.length, ip_address, port);
		return datagramPacket;
	}

	public void send(String message, int port, byte[] ip){

		while(true){
		try {
			createSocket(port);
			DatagramPacket datagramPacket = createDatagram(message, port, ip);
			socket.send(datagramPacket);
			socket.close();

			//DatagramPacket dgpacket = new DatagramPacket();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void init(){
		DatagramSendReceive receiveThread = new DatagramSendReceive();
		receiveThread.start();
		System.out.println("inside init");
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
		try {
			createSocket(63000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while(true){
			try {

				socket.receive(receivePacket);

				String message = new String(receivePacket.getData());
				System.out.println(message);
				if(message.equals("bye")){
					socket.close();
					System.out.println("socket closed");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	/** found on stack overflow:
	public static void main(String[] args) throws Exception{

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
		//this will be used for user input

		DatagramSocket clientSocket = new DatagramSocket();

		InetAddress IPAddress = InetAddress.getByName("localhost");
		//getting my own ip address

		byte[] send_message_bytes = new byte[1024];
		byte[] receive_message_bytes = new byte[1024];
		String message_string = input.readLine();
		send_message_bytes = message_string.getBytes();//convert message to byte array

		//convert byte array to DatagramPacket
		//bind to port 9876 on machine with given IP address
		DatagramPacket send_packet = new DatagramPacket(send_message_bytes, send_message_bytes.length, IPAddress, 9876);
		clientSocket.send(send_packet);
		//i am currently sending the message to myself

		DatagramPacket receivePacket = new DatagramPacket(receive_message_bytes, receive_message_bytes.length);
		clientSocket.receive(receivePacket);

		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("from server: " + modifiedSentence);
		clientSocket.close();
	}
	 */
}
