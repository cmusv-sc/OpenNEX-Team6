package models;

public class SQL {

	public static final String SA_USERS = "SA_USERS";
	public static final String SA_GROUPS = "SA_GROUPS";
	public static final String SA_GROUP_TO_USERS = "SA_GROUP_TO_USERS";
	public static final String SA_SESSSIONS = "SA_SESSIONS";
	public static final String SA_SESSION_TO_USERS = "SA_SESSION_TO_USERS";


	public static final String CREATE_USERS = "create table IF NOT EXISTS "
			+ SA_USERS + " ( user_id IDENTITY PRIMARY KEY,"
			+ " user_name VARCHAR(100)," + " role_type VARCHAR(100) )";

	public static final String CREATE_GROUPS = "create table IF NOT EXISTS "
			+ SA_GROUPS + " ( group_id IDENTITY PRIMARY KEY AUTO_INCREMENT,"
			+ " group_name VARCHAR(100)," + " group_description VARCHAR(512),"
			+ " group_admin VARCHAR(100) )";

	public static final String CREATE_GROUP_TO_USERS = "create table IF NOT EXISTS "
			+ SA_GROUP_TO_USERS + " ( group_id int NOT NULL,"
			+ " user VARCHAR(100),"
			+ " CONSTRAINT pk_groupId PRIMARY KEY (groupId,user) )";

	public static final String CREATE_SESSION = "create table IF NOT EXISTS "
			+ SA_SESSSIONS + " ( Id IDENTITY PRIMARY KEY AUTO_INCREMENT,"
			+ " topic VARCHAR(100)," + " description VARCHAR(512) )"
			+ " admin VARCHAR(100) )";

	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";

	public static final String CREATE_SESSION_TO_USERS = "create table IF NOT EXISTS "
			+ SA_SESSION_TO_USERS + " ( sessionId int NOT NULL,"
			+ " user VARCHAR(100),"
			+ " CONSTRAINT pk_sessionId PRIMARY KEY (sessionId,user) )";

	public static final String INSERT_GROUP = "INSERT INTO " + SA_GROUPS + 
			"(group_name, group_description, group_admin) " + 
			"VALUES (" + VARCHAR(100) + ", " + VARCHAR(512) + ", " + VARCHAR(100) + ")"; 

	public static final String GET_SESSION_BY_TOPIC = "SELECT * FROM "
			+ SA_SESSSIONS + "WHERE topic = ?";

	public static final String GET_SESSION_USERS = "SELECT users FROM "
			+ SA_SESSION_TO_USERS + "WHERE Id = ?";

	public static final String GET_GROUPS = "SELECT ALL FROM " + SA_GROUPS;


}
