package models;

public class SQL {

	public static final String SA_USERS = "USER";
	public static final String SA_GROUPS = "USER_GROUP";
	public static final String SA_GROUP_TO_USERS = "SA_GROUP_TO_USERS";
	public static final String SA_SESSIONS = "SESSION";
	public static final String SA_SESSION_TO_USERS = "SA_SESSION_TO_USERS";
	public static final String SA_PROJECTS = "PROJECT";

    /************************************* Users **********************************/

    public static final String CREATE_USERS = "create table IF NOT EXISTS "
        + SA_USERS + " ( user_id IDENTITY PRIMARY KEY,"
        + " user_name VARCHAR(100)," + " role_type VARCHAR(100) )";

    public static final String INSERT_USER = "INSERT INTO " + SA_USERS + "(email, password, session_id, user_group_id) VALUES (?,?,?,?)";

    public static final String LOAD_USERS = "SELECT email FROM " + SA_USERS;

    /************************************ Session *********************************/

//    public static final String CREATE_SESSION = "create table IF NOT EXISTS "
//        + SA_SESSIONS + " ( Id IDENTITY PRIMARY KEY AUTO_INCREMENT,"
//        + " topic VARCHAR(100)," + " description VARCHAR(512),"
//        + " admin VARCHAR(100) )";

    public static final String CREATE_SESSION_TO_USERS = "create table IF NOT EXISTS "
            + SA_SESSION_TO_USERS + " ( sessionId int NOT NULL,"
            + " userId int,"
            + " CONSTRAINT pk_sessionId PRIMARY KEY (sessionId,userId) )";

    public static final String GET_SESSION_BY_TOPIC = "SELECT * FROM "
            + SA_SESSIONS + " WHERE topic = ?";

//    public static final String Get_SESSION_BY_TOPIC =

    public static final String GET_USER_SESSIONS = "SELECT SA_SESSION.topic FROM "
            + SA_SESSIONS + " LEFT JOIN " + SA_SESSION_TO_USERS
            + " ON SA_SESSION_TO_USERS.sessionId = SA_SESSION.Id"
            + " WHERE SA_SESSION_TO_USERS.userId = ?";

    public static final String GET_SESSION_USERS = "SELECT * FROM " + SA_USERS
            + " WHERE SESSION_ID = ?";

    public static final String INSERT_SESSION = "INSERT INTO " + SA_SESSIONS +
            "(topic, description, admin) VALUES (?,?,?)";

    /************************************ Groups **********************************/

    public static final String CREATE_GROUPS = "create table IF NOT EXISTS "
        + SA_GROUPS + " ( group_id IDENTITY PRIMARY KEY AUTO_INCREMENT,"
        + " group_name VARCHAR(100)," + " group_description VARCHAR(512),"
        + " group_admin VARCHAR(100) )";

    public static final String INSERT_GROUP = "INSERT INTO " + SA_GROUPS +
            "(group_name, group_description, group_admin) VALUES (?,?,?)";

    public static final String CREATE_GROUP_TO_USERS = "create table IF NOT EXISTS "
			+ SA_GROUP_TO_USERS + " ( group_id int NOT NULL,"
			+ " user VARCHAR(100),"
			+ " CONSTRAINT pk_groupId PRIMARY KEY (groupId,user) )";

    public static final String GET_GROUPS = "SELECT ALL FROM " + SA_GROUPS;

    /************************************ Project *********************************/

    public static final String INSERT_PROJECT = "INSERT INTO " + SA_PROJECTS
            + " (description, session_id, user_group_id) VALUES (?,?,?)";

    /************************************ Others **********************************/

	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";

}
