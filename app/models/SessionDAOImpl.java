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
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.CREATE_SESSION);
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
        if (sessionTopic.trim() != "" && sessionTopic != null) {
            try {
                // Get everything except users
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSION_BY_TOPIC);
                preparedStatement.setString(1, sessionTopic);

                ResultSet resultSet = preparedStatement.executeQuery();
                Session session = null;
                while (resultSet.next()) {
                    session = new Session(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    break;
                }
                // get users of this session
                if (session != null) {
                    session.setSessionUsers(getUsersForSession(session.getSessionID()));
                }
                connection.close();
                return session;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<String> getSessionsForUser(String useName) {
        return null;
    }

    /**
     * get users of a session
     * @param id
     * @return
     * @throws SQLException
     */
    private List<String> getUsersForSession(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_SESSION_USERS);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> sessionUsers = new ArrayList<String>();

        while (resultSet.next()) {
            String user = resultSet.getString(2);
            sessionUsers.add(user);
        }
        connection.close();
        return sessionUsers;
    }
}
