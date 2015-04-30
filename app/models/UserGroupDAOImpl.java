package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.ebean.Model.Finder;

public class UserGroupDAOImpl extends CommonDAOImpl implements UserGroupDAO  {

	@Override
	public void save(UserGroup group) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERT_GROUP);
            preparedStatement.setString(1, group.getGroupName());
            preparedStatement.setString(2, group.getGroupDescription());
            preparedStatement.setLong(3, group.getAdmin().getId());

            executeStatement(preparedStatement);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<UserGroup> loadGroups() {
		List<UserGroup> result = new ArrayList<UserGroup>();

		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUPS);
	        ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserGroup group = new UserGroup(resultSet.getString(1), 
			            						resultSet.getString(2), 
			            						User.byId(resultSet.getLong(3)));
	            result.add(group);
	        }
	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return result;
	}
	
	@Override
	public List<Project> findProjectsByUserGroupId(Long userGroupID) throws Exception {
		List<Long> projectIds = new ArrayList<Long>();
		List<Project> result = new ArrayList<Project>();
		if(userGroupID != null) {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_PROJECTIDS_FOR_USERGROUPID);
			preparedStatement.setLong(1, userGroupID);

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
	
	@Override
	public List<User> findUsersByUserGroupId(Long userGroupId) throws SQLException {
		List<Long> userIds = new ArrayList<Long>();
		List<User> result = new ArrayList<User>();
		if(userGroupId != null) {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_USERSIDS_FOR_USERGROUPID);
			preparedStatement.setLong(1, userGroupId);

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
	
	private Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );
	
	public User byID(Long id) {
        return find.where()
                .eq("id", id).findUnique();
    }

	public UserGroup getGroupById(long groupId){
		UserGroup group = new UserGroup();
		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUP_BY_ID);
	        preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();

			group = new UserGroup(resultSet.getString(1), 
        						  resultSet.getString(2), 
        						  User.byId(resultSet.getLong(3)));

	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return group;		
	}

	public List<UserGroup> getGroupsByUserId(long userId){
		List<UserGroup> result = new ArrayList<UserGroup>();
		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUP_BY_USER_ID);
	        preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserGroup group = new UserGroup(resultSet.getString(1), 
			            						resultSet.getString(2), 
			            						User.byId(resultSet.getLong(3)));
	            result.add(group);
	        }
	        
	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return result;		
	}

	public List<UserGroup> getGroupsBySessionId(long sessionId){
		List<UserGroup> result = new ArrayList<UserGroup>();
		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUP_BY_SESSION_ID);
	        preparedStatement.setLong(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserGroup group = new UserGroup(resultSet.getString(1), 
			            						resultSet.getString(2), 
			            						User.byId(resultSet.getLong(3)));
	            result.add(group);
	        }
	        
	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return result;	
	}

	public List<UserGroup> getGroupsByProjectId(long projectId){
		List<UserGroup> result = new ArrayList<UserGroup>();
		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUP_BY_PROJECT_ID);
	        preparedStatement.setLong(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				UserGroup group = new UserGroup(resultSet.getString(1), 
			            						resultSet.getString(2), 
			            						User.byId(resultSet.getLong(3)));
	            result.add(group);
	        }
	        
	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return result;
	}


}
