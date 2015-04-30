package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl extends CommonDAOImpl implements ProjectDAO {

	@Override
	public void save(Project project) throws Exception {
		if (project != null) {
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERT_PROJECT);
                preparedStatement.setString(1, project.getDescription());
                preparedStatement.setLong(2, project.getSession().getSessionID());
                preparedStatement.setLong(3, project.getUserGroup().getGroupId());

                executeStatement(preparedStatement);
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		else {
			throw new Exception("Project is null");
		}
	}

	@Override
	public List<User> findUsersByProjectId(Long projectId) throws SQLException {
		List<Long> userIds = new ArrayList<Long>();
		List<User> result = new ArrayList<User>();
		if(projectId != null) {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_USERSIDS_FOR_PROJECTID);
			preparedStatement.setLong(1, projectId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				userIds.add(resultSet.getLong(1));
			}
			
			for(Long id: userIds) {
				PreparedStatement getPreparedStatement = connection.prepareStatement(SQL.GET_USERS_FOR_USERID);
				preparedStatement.setLong(1, id);
				ResultSet userResultSet = getPreparedStatement.executeQuery();
				User user = new User();
				user.setId(userResultSet.getLong(1));
				user.setEmail(userResultSet.getString(2));
				result.add(user);
			}
			
			return result;
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Project findProjectByProjectId(Long projectId) throws SQLException {
		Project project = new Project();
		Session session = null;
		UserGroup userGroup = null;
		User user = new User();
		Long session_id = 0L;
		Long userGroupId = 0L;
		Long userId = 0L;
		if(projectId != null) {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL.FIND_PROJECT_BY_PROJECTID);
			preparedStatement.setLong(1, projectId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				project.setId(resultSet.getLong(1));
				project.setDescription(resultSet.getString(2));
				session_id = resultSet.getLong(3);
				userGroupId = resultSet.getLong(4);
				userId = resultSet.getLong(5);
			}
			
			PreparedStatement preparedStatement1 = connection.prepareStatement(SQL.GET_SESSIONS_FOR_ID);
			preparedStatement1.setLong(1, session_id);
			ResultSet resultSet1 = preparedStatement1.executeQuery();
			
			while(resultSet.next()) {
				session = new Session(resultSet1.getString(2), resultSet1.getString(3), resultSet1.getString(4));
			}
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(SQL.GET_USERGROUP_FOR_ID);
			preparedStatement2.setLong(1, userGroupId);
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			
			while(resultSet.next()) {
				//userGroup = new UserGroup(resultSet2.getString(2), resultSet2.getString(3), resultSet2.getString(4));
			}
			
			PreparedStatement preparedStatement3 = connection.prepareStatement(SQL.GET_USERS_FOR_USERID);
			preparedStatement3.setLong(1, userId);
			ResultSet resultSet3 = preparedStatement3.executeQuery();
			
			while(resultSet.next()) {
				user.setId(resultSet3.getLong(1));
				user.setEmail(resultSet3.getString(2));
			}
			
			project.setSession(session);
			//project.setUserGroup(userGroup);
			project.setUsers(user);
			
			return project;
		}
		
		return null;
	}
}
