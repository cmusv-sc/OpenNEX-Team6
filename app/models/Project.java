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
    public Session session;
    
    @OneToOne
    public UserGroup userGroup;
    
    @OneToOne
    public User user;

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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUsers(User user) {
		this.user = user;
	}
    
    
}
