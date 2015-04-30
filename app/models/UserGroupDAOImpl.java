package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}
