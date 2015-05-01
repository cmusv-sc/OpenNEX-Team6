package models;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivenkat.
 */

@Entity
public class User extends Model {

    @Id
    @Constraints.Min(10)
    private Long id;

    @Constraints.Email
    @Column(unique = true)
    @Constraints.Required(groups = { SignIn.class, SignUp.class, Update.class })
    private String email;

    @Constraints.Required(groups = { SignIn.class, SignUp.class, Update.class })
    private String password;
    
    @OneToOne
    private Session session;
    
    @OneToOne
    private UserGroup userGroup;

    @OneToOne
    private Task task;
    
    @OneToOne
    private Project project;
    // add the list of messages/notifications

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );

    public static User byId(Long id) {
        return find.where()
                .eq("id", id)
                .findUnique();
    }

    public static User byEmail(String email) {
        return find.where()
                .eq("email", email)
                .findUnique();
    }


    public static User byEmailAndPassword(String email, String password) {
        return find.where()
                .eq("email", email)
                .eq("password", password)
                .findUnique();
    }

    public List<ValidationError> validate(Class group) {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (group == SignIn.class) {
            User user = byEmailAndPassword(email, password);

            if (user == null) {
                errors.add(new ValidationError("", "Invalid email or password."));
            }
        } else if (group == SignUp.class) {
            if (User.byEmail(email) != null) {
                errors.add(new ValidationError("email", "This email is already registered."));
            }
        }

        return errors.isEmpty() ? null : errors;
    }

    public interface SignIn { }

    public interface SignUp { }

    public interface Update { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Session getSession() {
		return this.session;
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

    public Project getProject(){
        return this.project;
    }

    public void setProject(Project project){
        this.project = project;
    }
    
    

}