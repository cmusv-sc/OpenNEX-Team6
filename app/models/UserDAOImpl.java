package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                preparedStatement.setLong(3, user.getSession().getId());
                preparedStatement.setLong(4, user.getUserGroup().getGroupId());

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
	public List<User> loadUsers() throws SQLException {
		Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL.LOAD_USERS);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<User>();
        
        while(resultSet.next()) {
        	User user = new User();
        	user.setId(resultSet.getLong(1));
        	user.setEmail(resultSet.getString(2));
        	users.add(user);
        }
        connection.close();
        return users;
	}

	@Override
	public User findByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Project> findProjectsByUserId(Long userId) throws Exception {
		List<Long> projectIds = new ArrayList<Long>();
		List<Project> result = new ArrayList<Project>();
		if(userId != null) {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_PROJECTIDS_FOR_USERID);
			preparedStatement.setLong(1, userId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				projectIds.add(resultSet.getLong(1));
			}
			
			for(Long id: projectIds) {
				PreparedStatement getPreparedStatement = connection.prepareStatement(SQL.GET_PROJECTS_FOR_PROJECTID);
				preparedStatement.setLong(1, id);
				ResultSet projectResultSet = getPreparedStatement.executeQuery();
				Project project = new Project();
				project.setId(projectResultSet.getLong(1));
				project.setDescription(projectResultSet.getString(2));
				result.add(project);
			}
			
			return result;
		}
		// TODO Auto-generated method stub
		return null;
	}

}
