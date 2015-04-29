package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class Project extends Model {
	
	@Id
    @Constraints.Min(10)
    public Long id;

    @Column
    @Constraints.Required
    public String description;
    
    @OneToOne
    public int session;
    
    @OneToOne
    public int userGroup;
    
    @OneToOne
    public List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
    
    
}
