package chat_server_client_4;

import java.util.Scanner;

/**This is a partial OneTimePad…. to use as an example. 
 *You will need to modify the encrypt and decrypt methods below
 *Keep in mind that if you copy from docs into eclipse the quote marks and other characters *will not be read properly by the IDE so you must find/replace or retype them.
 *Don’t just copy paste.
 */


public class OneTimePad {
	private static final char[] abcVal = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', 
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0','1','2','3','4','5','6','7','8','9' };
	private String plainMessage ="";
	private String encryptedMessage = "";
	private String currentKey="";	
	
	
	public OneTimePad() {
		// TODO Auto-generated constructor stub
		plainMessage="NO MESSAGE";
		currentKey= this.generateKey(plainMessage);
	}
	public OneTimePad(String msg) {
		// TODO Auto-generated constructor stub		
		plainMessage=msg;
		currentKey= generateKey(msg);
		encryptedMessage = encrypt(msg);
	}
	public String generateKey(String msg){
		String key ="";
		for(int i=0; i<msg.length(); i++){
			key= key+ abcVal[(int)(Math.random()*10)];
		}
		return key;
	}
	public String getEncryptedMessage(){
		return this.encryptedMessage;
	}
	public void setKey(String k){
		currentKey=k;
	}
	public String getKey(){
		return this.currentKey;
	}
	public String encrypt(String plainMsg){
		String encMsg="";
		for(int i=0; i<plainMsg.length(); i++){
			System.out.println("inside encrypt for loop at "+i+" value is "+plainMsg.charAt(i));
			//assign numerical value to each character in the plain message
			int numForPlainChar = this.getNumberForChar(plainMsg.charAt(i));
			int numForKeyChar = this.getNumberForChar(currentKey.charAt(i));   //System.out.println("numForKeyChar at "+i+" is "+ numForKeyChar  + "    numForPlainChar at "+i+" is "+ numForPlainChar);
			//add the key to the plain message or whatever your algorithm for encryption is ...
			int valOfEncrChar = numForPlainChar + numForKeyChar;//math operations using numForPlainChar and numForKeyChar   //System.out.println("valOfEncrChar at "+i+" is "+ valOfEncrChar);
					//modulus array abcVal length to 
			int numForEncChar = Math.abs(valOfEncrChar % abcVal.length);//absolute value to prevent out of bounds //System.out.println("numForEncChar at "+i+" is "+ numForEncChar);//for debugging
			//find the letter for that number from the abcVal array put it in the encMsg string
			encMsg = encMsg+ abcVal[numForEncChar];
		}
		System.out.println("encrypted message is" + encMsg);
		return encMsg;
	}
	private int getNumberForChar(char c){
		int n = -1;
		for(int i=0; i<abcVal.length; i++){
			if(c == (abcVal[i])){
				return i;
			}
		}
		return n;
	 
	}
	public String decrypt(String encMsg){
		String decMsg="";
		for(int i=0; i<encMsg.length(); i++){
			if(plainMessage.charAt(i) == ' '){
				decMsg += " ";
			} else {
			//assign numerical value to each character in the encrypted message
			int numForEncChar = this.getNumberForChar(encMsg.charAt(i));
			System.out.println("numForEncChar at "+i+" is: " +numForEncChar);
			//assign numerical value to each character in the key 
			int numForKeyChar = this.getNumberForChar(currentKey.charAt(i));
			System.out.println("numForKeyChar  at "+i+" is: " +numForKeyChar);
			//subtract key from encrypted message, or whatever your decryption algorithm is
			int valOfCharAtIndex = numForEncChar -  numForKeyChar;// math operations using numForEncChar and  numForKeyChar;   
			System.out.println("valOfCharAtIndex at "+i+" is: " + valOfCharAtIndex);
			//modulus array abcVal length to decrypt it
			int numForDecChar = (valOfCharAtIndex % abcVal.length + abcVal.length) % abcVal.length;
			
			System.out.println("numForDecChar at "+i+" is: " +numForDecChar +"\n\n");
			//find the letter for that number from the abcVal array and append it to the decMsg string
			decMsg = decMsg + abcVal[numForDecChar];
			}
		}
		System.out.println("the decrypted message is: " + decMsg);
		
		return decMsg;

	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter an message; i will display the encrypted message, then decrypt the encrypted message and show you the original message: ");
		String message = in.nextLine();
		
		OneTimePad otp = new OneTimePad(message);
		
		String encryptedMessage = otp.getEncryptedMessage();
		
		System.out.println("Encrypted message: "+ encryptedMessage);
		
		System.out.println("Key: " + otp.getKey());
		
		System.out.println("Decrypted message: " + otp.decrypt(encryptedMessage));
	
		System.out.println((-22%26 + 26)%26);
		
		
		
	}
	
}