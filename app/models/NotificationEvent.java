package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class NotificationEvent extends Model {
	@Id
    @Constraints.Min(10)
    public Long id;

    @Column
    @Constraints.Required
    private String description; 

    @ManyToMany
	private List<User> users; 

    @ManyToMany
	private List<UserGroup> groups; 

    @ManyToMany
	private List<Session> sessions; 

    @ManyToMany
	private List<Project> projects; 

	public NotificationEvent(List<User> users, 
							 List<UserGroup> groups,
							 List<Session> sessions,
							 List<Project> projects,
							 String description){
		this.users = users;
		this.groups = groups;
		this.sessions = sessions;
		this.projects = projects;
		this.description = description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public List<User> getUsers(){
		return users; 
	}

	public List<UserGroup> getGroups(){
		return groups; 
	}

	public List<Session> getSessions(){
		return sessions; 
	}

	public List<Project> getProjects(){
		return projects; 
	}

	public String getDescription(){
		return description; 
	}

}