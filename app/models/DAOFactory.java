package models;

public class DAOFactory {
	private static DAOFactory instance;

	/**
	 * Singleton instance access method to get the instance of the class to
	 * request a specific DAO implementation.
	 * 
	 * @return - DAOFactory instance
	 */
	public static final DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}

		return instance;
	}

	/**
	 * Method to get a new object implementing UserDAO
	 * 
	 * @return - Object implementing UserDAO
	 */
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	/**
	 * Method to get a new object implementing UserDAO
	 * 
	 * @return - Object implementing UserDAO
	 */
	public UserGroupDAO getGroupDAO() {
		return new UserGroupDAOImpl();
	}

	/**
	 * Get new object implementing Session DAO
	 *
	 * @return - Object implementing SessionDAO
	 */
	public SessionDAO getSessionDAO(){ return new SessionDAOImpl(); }
}
