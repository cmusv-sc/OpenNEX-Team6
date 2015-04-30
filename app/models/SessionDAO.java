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
     * Get all sessions for user
     * @param userName
     * @return
     */
    List<Session> getSessionsForUser(String userName) throws SQLException;

    /**
     * Get all sessions for a project
     * @param projectId
     * @return
     * @throws SQLException
     */
    List<Session> getSessionsForProject(Long projectId) throws SQLException;

    /**
     * Get all sessions for a group
     * @param groupId
     * @return
     * @throws SQLException
     */
    List<Session> getSessionsForGroup(Long groupId) throws SQLException;

}