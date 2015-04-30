package models;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	/**
	 * This method will save the information of the user into the database.
	 * 
	 * @param userPO
	 *            - User information to be saved.
	 * @throws Exception 
	 */
	void save(User user) throws Exception;

	/**
	 * This method will load all the users in the
	 * database.
	 * 
	 * @return - List of all users.
	 * @throws SQLException 
	 */
	List<User> loadUsers() throws SQLException;

	/**
	 * This method with search for a user by his userName in the database. The
	 * search performed is a case insensitive search to allow case mismatch
	 * situations.
	 * 
	 * @param userName
	 *            - User name to search for.
	 * 
	 * @return - UserPO with the user information if a match is found.
	 */
	User findByName(String userName);

	List<Project> findProjectsByUserId(Long userId) throws Exception;
}
