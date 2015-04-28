package models;

import java.util.List;

/**
 * Created by gautammadaan on 4/27/15.
 */
public class Session {

    private int Id;
    private String topic;
    private String description;
    private List<String> users;
    private String admin;

    public Session(String topic, String description, String createdBy) {
        this.topic = topic;
        this.description = description;
        this.admin = createdBy;
    }

    /* Retrieve object state */
    public int getSessionID(){ return Id; };

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

    public void setSessionID(int sessionID){ this.Id = sessionID; }

    public void setSessionTopic(String sessionTopic){ this.topic = sessionTopic; }

    public void setSessionDescription(String sessionDescription){ this.description = sessionDescription; }

    public void setAdmin(String admin){ this.admin = admin; }

}