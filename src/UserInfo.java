import java.util.Scanner;

public class UserInfo {
	
	private String id;
	private String name;
	
//	public UserInfo() {
//		String surname=askName();
//		generateId(isNameValid(),surname);
//		System.out.println("Welcome "+ name + ", your ID is " + id + "\n\n");
//	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	//askName method prompt for name 
	//isNameValid check if there's at least one space in the firstName given
	//generateId check if isNameValid return true then id=first character of name
	public void generateUserId(String firstName,String surname) {
		
		firstName=firstName.trim();
		surname=surname.trim();
		
		name= firstName +" "+ surname;
		if(firstName.contains(" "))
			id=name.charAt(0) + surname;
		else id="guest";
	}
}
