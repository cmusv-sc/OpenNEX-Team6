package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
