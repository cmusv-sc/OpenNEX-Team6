package models;

import java.util.ArrayList;

@Entity
public class Group {

	private String groupDescription; 
	private String groupName; 
	private ArrayList<User> admins; 
	private ArrayList<User> members; 

	public Group(String groupName, 
				 String groupDescription,
				 ArrayList<User> admins,
				 ArrayList<User> members){
		this.groupName = groupName; 
		this.groupDescription = groupDescription;
		this.admins = admins; 
		this.members = members; 
	}; 

	public void setGroupDescription(String groupDescription){
		this.groupDescription = groupDescription; 
	}

	public void setGroupName(String groupName){
		this.groupName = groupName; 
	}

	public void setAdmins(ArrayList<User> admins){
		this.admins = admins; 
	}

	public void setMembers(ArrayList<User> members){
		this.members = members; 
	}

	public String getGroupDescription(){
		return groupDescription; 
	}

	public String getGroupName(){
		return groupName;
	}

	public ArrayList<User> getAdmins(){
		return admins; 
	}

	public ArrayList<User> getMembers(){
		return members; 
	}	
    
}