package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends CommonDAOImpl implements UserDAO {

	@Override
	public void save(User user) throws Exception {
		if (user != null) {
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERT_USER);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setLong(3, user.getSession());
                preparedStatement.setLong(4, user.getUserGroup());

                executeStatement(preparedStatement);
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		else {
			throw new Exception("User is null");
		}
	}

	@Override
	public List<User> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
