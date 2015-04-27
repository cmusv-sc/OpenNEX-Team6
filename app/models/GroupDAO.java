package models;

import java.util.List;

public interface GroupDAO {
	/**
	 * This method will save the information of the user into the database.
	 * 
	 * @param userPO
	 *            - User information to be saved.
	 */
	void save(Group group);

	/**
	 * This method will load all the users in the
	 * database.
	 * 
	 * @return - List of all users.
	 */
	List<Group> loadGroups();
}
