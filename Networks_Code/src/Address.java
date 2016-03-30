import java.net.InetAddress;


public class Address {

	public static void main(String[] args){
		
		
		try{
			InetAddress myAddress = InetAddress.getLocalHost();
			System.out.println(myAddress.getHostAddress());
		} catch (Exception e){
			System.out.println(e);
		}
	}
}
