package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * Created by gautammadaan on 4/27/15.
 */
@Entity
public class Session extends Model {

    @Id
    @Constraints.Min(10)
    private int id;
    
    @Constraints.Required
    private String topic;
    
    @Constraints.Required
    private String description;
    
    private List<String> users;
    
    @Constraints.Required
    private String admin;

    public Session(String topic, String description, String createdBy) {
        this.topic = topic;
        this.description = description;
        this.admin = createdBy;
    }

    /* Retrieve object state */
    public int getSessionID(){ return id; };

    public List<String> getSessionUsers() {
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

    public void setSessionUsers(List<String> sessionUsers){ this.users = sessionUsers; }

    public void setSessionID(int sessionID){ this.id = sessionID; }

    public void setSessionTopic(String sessionTopic){ this.topic = sessionTopic; }

    public void setSessionDescription(String sessionDescription){ this.description = sessionDescription; }

    public void setAdmin(String admin){ this.admin = admin; }

}