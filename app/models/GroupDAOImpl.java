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

            executeStatement(preparedStatement);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Group> loadGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
