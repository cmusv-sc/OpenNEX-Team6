package models;

public class SQL {
	
	public static final String SA_USERS = "SA_USERS";
	public static final String SA_GROUPS = "SA_GROUPS";
	
	
	public static final String CREATE_USERS = "create table IF NOT EXISTS "
			+ SA_USERS + " ( user_id IDENTITY PRIMARY KEY,"
			+ " user_name VARCHAR(100)," + " role_type VARCHAR(100) )";
	
	public static final String CREATE_GROUPS = "create table IF NOT EXISTS "
			+ SA_GROUPS + " ( group_id IDENTITY PRIMARY KEY,"
			+ " group_name VARCHAR(100)," + " group_description VARCHAR(512) )";
	
	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";

}
