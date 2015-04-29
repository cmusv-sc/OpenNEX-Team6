package models;

import java.util.List;
import play.*;
import javax.persistence.*;

@Entity
public class Group {

	private long groupId;  
	private String groupDescription; 
	private String groupName; 
	private List<String> members;
    private String admin;

	public Group(String groupName, 
				 String groupDescription,
				 String username){
		this.groupName = groupName; 
		this.groupDescription = groupDescription;
		this.admin = username; 
	}; 

	public void setGroupId(long groupId){
		this.groupId = groupId; 
	}

	public void setGroupDescription(String groupDescription){
		this.groupDescription = groupDescription; 
	}

	public void setGroupName(String groupName){
		this.groupName = groupName; 
	}

    public void setAdmin(String admin){
        this.admin = admin;
    }

    public void SetMembers(List<String> members) {
        this.members = members;
    }

	public long getGroupId(){
		return groupId; 
	}

	public String getGroupDescription(){
		return groupDescription; 
	}

	public String getGroupName(){
		return groupName;
	}

    public String getAdmin(){
        return this.admin;
    }

    public List<String> getMembers() {
        return this.members;
    }

	public String toString(){
		return "Group [group Id = " + groupId + ", group name = " + groupName + 
			   ", group description = " + groupDescription + "]"; 
	}
    
}
