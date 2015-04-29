package models;

public interface ProjectDAO {
	/**
	 * This method will save the information of the user into the database.
	 * 
	 * @param userPO
	 *            - User information to be saved.
	 * @throws Exception 
	 */
	void save(Project project) throws Exception;
}
