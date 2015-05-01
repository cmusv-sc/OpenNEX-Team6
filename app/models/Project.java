package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Project extends Model {
	
	@Id
    @Constraints.Min(10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    @Constraints.Required
    public String name;

    @Column
    @Constraints.Required
    public String description;
    
    @OneToOne
    public Session session;
    
    @OneToOne
    public UserGroup userGroup;
    
    @OneToOne
    public User user;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public static Finder<Long, Project> find = new Finder<Long, Project>(
            Long.class, Project.class
    );

    public static Project byId(Long id) {
        return find.where()
                .eq("id", id)
                .findUnique();
    }

    public static Project byDescription(String description) {
        return find.where()
                .eq("description", description)
                .findUnique();
    }

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

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
