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
                preparedStatement.setString(1, session.getSessionTopic());
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
                    if (session == null)
                        session = new Session(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    System.out.println(session.getSessionTopic());
                    break;
                }
                // get users of this session
                if (session != null) {
                    session.setSessionUsers(getUsersForSession(session.getSessionID()));
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
    public List<String> getSessionsForUser(String userName) throws SQLException {
        if (userName.trim() != "" && userName != null){
            // get user Id
            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.findByName(userName);
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_USER_SESSIONS);
            // get user ID
            preparedStatement.setLong(1, user.id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> sessions = new ArrayList<String>();
            while (resultSet.next()){
                String session = resultSet.getString(3);
                sessions.add(session);
            }
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
    private List<User> getUsersForSession(int id) throws SQLException {
        System.out.println("Inside Get USers");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSION_USERS);
        preparedStatement.setInt(1, id);
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
}
