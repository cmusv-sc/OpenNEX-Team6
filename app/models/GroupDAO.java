package models;

import java.util.List;

public interface GroupDAO {

	/**
	 * This method will save the information of the group into the database.
	 * 
	 * @param group
	 *            - group information to be saved.
	 */
	void save(Group group);

	/**
	 * This method will load all the groups in the database.
	 * 
	 * @return - List of all groups.
	 */
	List<Group> loadGroups();
}
