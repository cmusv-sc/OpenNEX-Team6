package models;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautammadaan on 4/27/15.
 */
@Entity
public class Session extends Model {

    @Id
    @Constraints.Min(10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Constraints.Required
    private String topic;
    
    @Constraints.Required
    private String description;

    @OneToOne
    private User user;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "session")
    private List<User> users;

    private String admin;

//    public Session(String topic, String description, String admin) {
//        this.topic = topic;
//        this.description = description;
//        this.admin = System.getProperty("user.name");
//    }

    public static Finder<Long, Session> find = new Finder<Long, Session>(
            Long.class, Session.class
    );

    public static Session byTopic(String topic) {
        return find.where()
                .eq("topic", topic)
                .findUnique();
    }


    public static Session byTopicAndDescription(String topic, String description) {
        return find.where()
                .eq("topic", topic)
                .eq("description", description)
                .findUnique();
    }

    public List<ValidationError> validate(Class group) {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (group == createSession.class) {
            Session session = byTopicAndDescription(topic, description);

            if (session == null) {
                errors.add(new ValidationError("", "Invalid email or password."));
            }
        } else if (group == getSession.class) {
            if (Session.byTopic(topic) != null) {
                errors.add(new ValidationError("email", "This email is already registered."));
            }
        }

        return errors.isEmpty() ? null : errors;
    }

    public interface createSession { }

    public interface getSession { }

    public interface Update { }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    /* Retrieve object state */
    public Long getId(){ return id; };

    public List<User> getUsers() {
        return this.users;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getAdmin(){
        return this.admin;
    }

    /* Set Object state */

    public void setUsers(List<User> users){ this.users = users; }

    public void setId(Long sessionID){ this.id = sessionID; }

    public void setTopic(String sessionTopic){ this.topic = sessionTopic; }

    public void setDescription(String sessionDescription){ this.description = sessionDescription; }

    public void setAdmin(String admin){ this.admin = admin; }

}