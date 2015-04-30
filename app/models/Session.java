package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * Created by gautammadaan on 4/27/15.
 */
@Entity
public class Session extends Model {

    @Id
    @Constraints.Min(10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Constraints.Required
    private String topic;
    
    @Constraints.Required
    private String description;

//    @OneToMany(cascade=CascadeType.ALL, mappedBy = "session")
    private List<User> users;
    
    @Constraints.Required
    private String admin;

    public Session(String topic, String description, String createdBy) {
        this.topic = topic;
        this.description = description;
        this.admin = createdBy;
    }

    /* Retrieve object state */
    public int getSessionID(){ return id; };

    public List<User> getSessionUsers() {
        return this.users;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSessionTopic() {
        return this.topic;
    }

    public String getAdmin(){
        return this.admin;
    }

    /* Set Object state */

    public void setSessionUsers(List<User> users){ this.users = users; }

    public void setSessionID(int sessionID){ this.id = sessionID; }

    public void setSessionTopic(String sessionTopic){ this.topic = sessionTopic; }

    public void setSessionDescription(String sessionDescription){ this.description = sessionDescription; }

    public void setAdmin(String admin){ this.admin = admin; }

}