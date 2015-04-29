package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl extends CommonDAOImpl implements GroupDAO  {

	@Override
	public void save(Group group) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.CREATE_GROUPS);
            preparedStatement.setString(1, group.getGroupName());
            preparedStatement.setString(2, group.getGroupDescription());
            preparedStatement.setString(3, group.getAdmin());

            executeStatement(preparedStatement);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Group> loadGroups() {
		List<Group> result = new ArrayList<Group>();

		try {
			Connection connection = getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SQL.GET_GROUPS);
	        ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
	            Group group = new Group(resultSet.getString(1), 
	            						resultSet.getString(2), 
	            						resultSet.getString(3));
	            result.add(group);
	        }
	        connection.close();
	    } catch (SQLException e) {
            e.printStackTrace();
        }
        
	    return result;
	}

}
