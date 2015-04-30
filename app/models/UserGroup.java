package models;

import java.util.List;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class UserGroup extends Model { 
		
	@Id
    @Constraints.Min(10)
    private Long id;

    @Column
	@Constraints.Required
	private String groupName; 

    @Constraints.Required
    private String groupDescription;
    
	@Constraints.Required
	private String groupName; 
	
	@OneToOne
    public User users;
	
	@OneToOne
    private User admin;
	
	@OneToOne
	private Session session;
	
	@OneToOne
	private Project project;

	@OneToOne
	private Project project;

	public UserGroup(){};

	public UserGroup(String groupName, 
					 String groupDescription,
					 User admin){
		this.groupName = groupName; 
		this.groupDescription = groupDescription;
		this.admin = admin; 
	}; 

	public void setGroupId(long groupId){
		this.id = groupId; 
	}

	public void setGroupDescription(String groupDescription){
		this.groupDescription = groupDescription; 
	}

	public void setGroupName(String groupName){
		this.groupName = groupName; 
	}

    public void setAdmin(User admin){
        this.admin = admin;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public void setSession(Session session) {
        this.session = session;
    }

	public long getGroupId(){
		return id; 
	}

	public String getGroupDescription(){
		return groupDescription; 
	}

	public String getGroupName(){
		return groupName;
	}

    public User getAdmin(){
        return this.admin;
    }

    public User getUsers() {
        return this.users;
    }

    public Session getSession() {
        return this.session;
    }

	public String toString(){
		return "Group [group Id = " + id + ", group name = " + groupName + 
			   ", group description = " + groupDescription + "]"; 
	}
    
}
