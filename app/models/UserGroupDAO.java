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

	/**
	 * This method will load all the groups associated to a user in the database.
	 * 
	 * @return - List of all groups associated to the user.
	 */
	List<UserGroup> getGroupsByUserId(long userId);

	/**
	 * This method will load all the groups associated to a session in the database.
	 * 
	 * @return - List of all groups associated to the session.
	 */
	List<UserGroup> getGroupsBySessionId(long sessionId);

	/**
	 * This method will load all the groups associated to a project in the database.
	 * 
	 * @return - List of all groups associated to the project.
	 */
	List<UserGroup> getGroupsByProjectId(long projectId);

}
