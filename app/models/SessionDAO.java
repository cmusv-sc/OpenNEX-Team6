package models;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gautammadaan on 4/27/15.
 */
public interface SessionDAO {


    /**
     * Save session in Database
     * @param session
     */
    void save(Session session);

    /**
     * Get Session details by session topic
     * @param sessionTopic
     * @return
     */
    Session getSessionFromTopic(String sessionTopic);

    /**
     * Get all session descriptions for user
     * @param userName
     * @return
     */
    List<String> getSessionsForUser(String userName) throws SQLException;

}