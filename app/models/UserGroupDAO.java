package models;

import java.sql.SQLException;
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

<<<<<<< HEAD
	List<Project> findProjectsByUserGroupId(Long userId) throws Exception;

	List<User> findUsersByUserGroupId(Long userGroupId) throws SQLException;
=======
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

>>>>>>> 19dc82f4036ec87d17234f84e95fa9295da9b5aa
}
