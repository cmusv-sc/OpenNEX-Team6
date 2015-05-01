package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class UserGroup extends Model { 
		
	@Id
    @Constraints.Min(10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
	@Constraints.Required
	private String groupName; 

    @Constraints.Required
    private String groupDescription; 
	
	@OneToOne
    public User user;
	
	@OneToOne
    private User admin;
	
	@OneToOne
	private Session session;
	
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

    public void setUser(User users) {
        this.user = users;
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

    public User getUser() {
        return this.user;
    }

    public Session getSession() {
        return this.session;
    }

	public String toString(){
		return "Group [group Id = " + id + ", group name = " + groupName + 
			   ", group description = " + groupDescription + "]"; 
	}

    public Project getProject(){
        return this.project;
    }

    public void setProject(Project project){
        this.project = project;
    }
    
}
