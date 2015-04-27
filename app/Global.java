import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import play.*;
import play.db.DB;

public class Global extends GlobalSettings {
	
	private static boolean DB_TABLES_EXIST = false;
	private static List<String> CREATE_TABLE_LST;

	static {
		CREATE_TABLE_LST = new ArrayList<String>();
		CREATE_TABLE_LST.add(models.SQL.CREATE_USERS);
		CREATE_TABLE_LST.add(models.SQL.CREATE_GROUPS);
	}
	
	  @Override
	  public void onStart(Application app) {
		  try {
				initializeDatabase();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    Logger.info("Application has started");
	  }  
	  
	  @Override
	  public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	  }  
	  
	  private static void initializeDatabase() throws SQLException {
			if (DB_TABLES_EXIST) {
				return;
			}

			final String CORE_TABLE_NAME = models.SQL.SA_USERS;

			try (Connection conn = DB.getConnection();
					Statement stmt = conn.createStatement();) {
				if (!doesTableExistInDB(conn, CORE_TABLE_NAME)) {
					Logger.info("Creating tables in database ...");
					for (String query : CREATE_TABLE_LST) {
						Logger.debug("Executing query: " + query);
						boolean status = stmt.execute(query);
						Logger.debug("Query execution completed with status: "
								+ status);
					}

					Logger.info("Tables created successfully");
				} else {
					Logger.info("Tables already exist in database. Not performing any action.");
				}

				DB_TABLES_EXIST = true;
			}
	  }
	  
	  private static boolean doesTableExistInDB(Connection conn, String tableName)
				throws SQLException {
			if (conn == null || tableName == null || "".equals(tableName.trim())) {
				Logger.error("Invalid input parameters. Returning doesTableExistInDB() method with FALSE.");
				return false;
			}

			boolean tableExists = false;

			final String SELECT_QUERY = models.SQL.CHECK_TABLE_EXISTS_IN_DB;

			ResultSet rs = null;
			try (PreparedStatement selectStmt = conn.prepareStatement(SELECT_QUERY)) {
				selectStmt.setString(1, tableName.toUpperCase());
				rs = selectStmt.executeQuery();
				int tableCount = 0;
				if (rs.next()) {
					tableCount = rs.getInt(1);
				}

				if (tableCount > 0) {
					tableExists = true;
				}
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
			
			return tableExists;
		}
}


