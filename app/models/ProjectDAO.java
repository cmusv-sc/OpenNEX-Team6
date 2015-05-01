package models;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDAO {
	/**
	 * This method will save the information of the user into the database.
	 * 
	 * @param userPO
	 *            - User information to be saved.
	 * @throws Exception 
	 */
	void save(Project project) throws Exception;
	
	List<User> findUsersByProjectId(Long projectId) throws SQLException;
	
	Project findProjectByProjectId(Long projectId) throws SQLException;

}
