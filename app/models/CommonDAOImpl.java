package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.db.DB;

public class CommonDAOImpl {
	/**
	 * Utility method to close a ResultSet.
	 * 
	 * @param rs
	 *            - ResultSet to be closed
	 * @throws Exception 
	 */
	protected void closeResultSet(ResultSet rs) throws Exception {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new Exception("Error when closing ResultSet", e);
			}
		}
	}

	/**
	 * Utility method to get a Connection to the database.
	 * 
	 * @return - Connection to the database.
	 * 
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
		Connection connection = DB.getConnection();
		return connection;
	}

	protected void handleException(Exception e) throws Exception {
		throw new Exception(e);
	}

	protected void executeStatement(PreparedStatement stmt) throws SQLException {
		int rowUpdated = stmt.executeUpdate();
	}
}
