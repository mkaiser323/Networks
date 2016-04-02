package Homework5;
import java.net.*;

public class DatagramSendReceive {

	DatagramSocket socket;

	/**This method receives the datagram 
	 * 
	 * @param port
	 */
	public void receive(int port) {

		try {
			//receiving datagram
			//socket = new DatagramSocket(port);
		} catch (Exception e) {
		}

		byte[] buffer = new byte[1024];

		DatagramPacket datagramPacket = new DatagramPacket(buffer, 1024);

		// create thread for receiving messages
		try {
			socket.receive(datagramPacket);
		} catch (Exception e) {
		}

		String messageReceived = new String(datagramPacket.getData(), 0,
				datagramPacket.getLength());

		// print out message received from sender
		System.out.println(messageReceived);

		System.out.println("Message successfully received.");

		//		socket.close();
	}

	/**
	 * This method sends the datagram
	 * @param port, the port number
	 * @param message, the message being sent
	 */
	public void send(int port, String message, String ip) {

		try {

			socket = new DatagramSocket(port);//*****

			// get ip address of intended receiver
			InetAddress ipAddress = InetAddress.getByName(ip);

			DatagramPacket packet = new DatagramPacket(message.getBytes(),
					message.length(), ipAddress, port);

			// send packet
			socket.send(packet);

			System.out.println("Message successfully sent.");

		} catch (Exception e) {
		}

	}

	public static void main(String[] args) throws Exception {

		final DatagramSendReceive datagramServer = new DatagramSendReceive();

		new Thread() {
			public void run() {
				datagramServer.receive(64000);
			}
		}.start();

		DatagramSendReceive datagramClient = new DatagramSendReceive();

		//send message on port 64000
		datagramClient.send(64000, "test", "192.168.1.115");
	}

}
