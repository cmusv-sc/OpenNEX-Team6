package models;

import java.util.List;

public interface UserGroupDAO {

	/**
	 * This method will save the information of the group into the database.
	 * 
	 * @param group
	 *            - group information to be saved.
	 */
	void save(UserGroup group);

	/**
	 * This method will load all the groups in the database.
	 * 
	 * @return - List of all groups.
	 */
	List<UserGroup> loadGroups();

	/**
	 * This method will load a specific group in the database.
	 * 
	 * @return - group
	 * 				- group associated with groupId
	 */
	UserGroup getGroupById(long groupId);	

}
