package Homework4;

import java.net.SocketException;
import java.util.Scanner;

public class ThreadDriver {
	
	
	static int[] ip = {127, 0, 0, 1};//loopback address
	static int port = 63000;
	
	static DatagramSendReceive receiveThread = new DatagramSendReceive(true, port, ip[0], ip[1], ip[2], ip[3]);
	static DatagramSendReceive sendThread = new DatagramSendReceive(false, port, ip[0], ip[1], ip[2], ip[3]);
	
	public static void main(String[] args){

		//receiveThread.start();
		sendThread.start();
	}

}
