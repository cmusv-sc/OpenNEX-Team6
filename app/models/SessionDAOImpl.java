package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautammadaan on 4/27/15.
 */
public class SessionDAOImpl extends CommonDAOImpl implements SessionDAO {


    @Override
    public void save(Session session) {
        if (session != null) {
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERT_SESSION);
                preparedStatement.setString(1, session.getTopic());
                preparedStatement.setString(2, session.getDescription());
                preparedStatement.setString(3, session.getAdmin());

                executeStatement(preparedStatement);
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Session getSessionFromTopic(String sessionTopic) {
        System.out.println(sessionTopic);
        if (sessionTopic.trim() != "" && sessionTopic != null) {
            try {
                // Get everything except users
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSION_BY_TOPIC);
                preparedStatement.setString(1, sessionTopic);
//                preparedStatement.setLong(2, 1);

                ResultSet resultSet = preparedStatement.executeQuery();
                Session session = null;
                List<User> users = new ArrayList<User>();
                while (resultSet.next()) {
                    if (session == null) {
                        session = new Session();
                        session.setAdmin(resultSet.getString(4));
                        session.setDescription(resultSet.getString(3));
                        session.setTopic(resultSet.getString(2));
                        session.setId(resultSet.getLong(1));
                    }
                    System.out.println(session.getTopic());
                    break;
                }
                // get users of this session
                if (session != null) {
                    session.setUsers(getUsersForSession(session.getId()));
                }
                else
                    System.out.println("Session is Null");
                connection.close();
//                System.out.println(session.getAdmin());
                return session;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Session> getSessionsForUser(String userName) throws SQLException {
        if (userName.trim() != "" && userName != null){
            // get Session Ids for the user
            List<Long> sessionIds = new ArrayList<Long>();
            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.findByName(userName);
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSIONID_FOR_USERID);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long sessionId = resultSet.getLong(4);
                sessionIds.add(sessionId);
            }

            List<Session> sessions = new ArrayList<Session>();

            // Get all sessions for the Ids
            sessions = getSessionForSessionIds(sessionIds, connection, preparedStatement);

            connection.close();
            return sessions;
        }
        return null;
    }

    @Override
    public List<Session> getSessionsForProject(Long projectId) throws SQLException {
        if (projectId != null){
            // get Session Ids for the Project
            List<Long> sessionIds = new ArrayList<Long>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSIONID_FOR_PROJECTID);
            preparedStatement.setLong(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long sessionId = resultSet.getLong(4);
                sessionIds.add(sessionId);
            }

            List<Session> sessions = new ArrayList<Session>();

            // Get all sessions for the Ids
            sessions = getSessionForSessionIds(sessionIds, connection, preparedStatement);

            connection.close();
            return sessions;
        }
        return null;
    }

    @Override
    public List<Session> getSessionsForGroup(Long groupId) throws SQLException {
        if (groupId != null){
            // get Session Ids for the Project
            List<Long> sessionIds = new ArrayList<Long>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSIONID_FOR_GROUPID);
            preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long sessionId = resultSet.getLong(4);
                sessionIds.add(sessionId);
            }

            List<Session> sessions = new ArrayList<Session>();

            // Get all sessions for the Ids
            sessions = getSessionForSessionIds(sessionIds, connection, preparedStatement);

            connection.close();
            return sessions;
        }
        return null;
    }

    /**
     * get users of a session
     * @param id
     * @return
     * @throws SQLException
     */
    private List<User> getUsersForSession(Long id) throws SQLException {
        System.out.println("Inside Get USers");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_USERS_FOR_SESSIONID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> sessionUsers = new ArrayList<User>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setEmail(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            sessionUsers.add(user);
        }
        connection.close();
        return sessionUsers;
    }

    /**
     * return list of sessions for a list of sessionIds
     * @param Ids
     * @param conn
     * @param stmt
     * @return
     * @throws SQLException
     */
    private List<Session> getSessionForSessionIds(List<Long> Ids, Connection conn, PreparedStatement stmt) throws SQLException {
        List<Session> sessions = new ArrayList<Session>();
        for (Long id : Ids) {
            stmt = conn.prepareStatement(SQL.GET_SESSIONS_FOR_ID);
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Session session = new Session();
                session.setAdmin(resultSet.getString(4));
                session.setDescription(resultSet.getString(3));
                session.setTopic(resultSet.getString(2));
                sessions.add(session);
            }
        }
        return sessions;
    }
}
