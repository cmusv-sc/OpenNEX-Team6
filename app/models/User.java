package models;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vivenkat.
 */

@Entity
public class User extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Email
    @Column(unique = true)
    @Constraints.Required(groups = { SignIn.class, SignUp.class, Update.class })
    public String email;

    @Constraints.Required(groups = { SignIn.class, SignUp.class, Update.class })
    public String password;
    
    @ManyToOne
    public int session;
    
    @ManyToOne
    public int userGroup;

    // add the list of messages/notifications

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );

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
    
    

}