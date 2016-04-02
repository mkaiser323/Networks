package chat_server_client_4;

public class UserName {
	
	int ID = -1;
	String name = "";
	
	static int[] IDs = new int[50];
	static String[] names = new String[50];
	static int users = 0;
	
	public UserName(int _ID, String _name){
		
		System.out.println("adding new user at ID: " + _ID);
		ID = _ID;
		name = _name;
		IDs[users] = _ID;
		names[users] = _name;
		users++;		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		names[users-1] = name;
		
		System.out.println("user " + name + " with ID " + ID + "has been added at index " + (users-1));

	}
	
	//takes in an ID number, searches through the IDs array, uses the index to find the username
	public static String find(int _ID){
		int i;
		for(i = 0; i <= users; i++){
			if(IDs[i] == _ID){
				break;
			}
		}
		System.out.println("getting user " + names[i] + "for ID " + _ID + "at index " + i);
		System.out.println(names[0]);
		
		return names[i-1];
		
	}
	

}
