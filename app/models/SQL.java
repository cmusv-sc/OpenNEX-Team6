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

    public static final String LOAD_USERS = "SELECT * FROM " + SA_USERS;

    public static final String GET_USERSIDS_FOR_PROJECTID = "select USER_ID from PROJECT where id = ?";

    public static final String GET_USERSIDS_FOR_USERGROUPID = "select USER_ID from USER_GROUP where id = ?";

    public static final String GET_USERS_FOR_USERID = "select id, email from USER where id = ?";

    public static final String GET_USERS_FOR_SESSIONID = "SELECT * FROM " + SA_USERS
            + " WHERE SESSION_ID = ?";


    /************************************ Session *********************************/

    public static final String GET_SESSION_BY_TOPIC = "SELECT * FROM "
            + SA_SESSIONS + " WHERE topic = ?";

    public static final String GET_SESSIONS_FOR_ID = "select id, topic, description, admin from SESSION where id = ?";

    public static final String GET_SESSIONID_FOR_USERID = "SELECT session_id FROM " + SA_USERS
            + " WHERE id = ?";

    public static final String GET_SESSIONID_FOR_GROUPID = "SELECT session_id FROM " + SA_GROUPS
            + " WHERE id = ?";

    public static final String GET_SESSIONID_FOR_PROJECTID = "SELECT session_id FROM " + SA_PROJECTS
            + " WHERE id = ?";

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

    public static final String GET_GROUP_BY_ID = "SELECT * FROM " + SA_GROUPS + "WHERE ID = ?";

    public static final String GET_GROUP_BY_USER_ID = "SELECT * FROM " + SA_GROUPS + "WHERE ADMIN_ID = ?";

    public static final String GET_GROUP_BY_SESSION_ID = "SELECT * FROM " + SA_GROUPS + "WHERE SESSION_ID = ?";

    public static final String GET_GROUP_BY_PROJECT_ID = "SELECT * FROM " + SA_GROUPS + "WHERE PROJECT_ID = ?";

    public static final String GET_USERGROUP_FOR_ID = "select id, GROUP_NAME, GROUP_DESCRIPTION, admin from USER_GROUP where id = ?";

    /************************************ Project *********************************/

    public static final String INSERT_PROJECT = "INSERT INTO " + SA_PROJECTS
            + " (description, session_id, user_group_id) VALUES (?,?,?)";

    public static final String GET_PROJECTIDS_FOR_USERID = "select PROJECT_ID from USER where id = ?";


    public static final String GET_PROJECTIDS_FOR_USERGROUPID = "select PROJECT_ID from USER_GROUP where id = ?";

    public static final String GET_PROJECTS_FOR_PROJECTID = "select id, description from PROJECT where id = ?";

    public static final String FIND_PROJECT_BY_PROJECTID = "select * from PROJECT where id = ?";

    /************************************ Others **********************************/

	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";
}
