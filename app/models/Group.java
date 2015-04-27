package models;

import java.util.ArrayList;
import play.*;
import javax.persistence.*;

@Entity
public class Group {

	private long groupId;  
	private String groupDescription; 
	private String groupName; 

	public Group(long groupId,
				 String groupName, 
				 String groupDescription){
		this.groupId = groupId; 
		this.groupName = groupName; 
		this.groupDescription = groupDescription;
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

	public long getGroupId(){
		return groupId; 
	}

	public String getGroupDescription(){
		return groupDescription; 
	}

	public String getGroupName(){
		return groupName;
	}

	public String toString(){
		return "Group [group Id = " + groupId + ", group name = " + groupName + 
			   ", group description = " + groupDescription + "]"; 
	}
    
}
