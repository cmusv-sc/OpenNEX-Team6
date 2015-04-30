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

	List<Project> findProjectsByUserGroupId(Long userId) throws Exception;

	List<User> findUsersByUserGroupId(Long userGroupId) throws SQLException;

	List<UserGroup> getGroupsByUserId(long userId);

	List<UserGroup> getGroupsByProjectId(long projectId);

	List<UserGroup> getGroupsBySessionId(long sessionId);

	User byID(Long id);

	UserGroup getGroupById(long groupId);
}
