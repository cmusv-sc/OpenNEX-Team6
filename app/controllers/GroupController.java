package controllers;

//import models.UserPlay;
import models.DAOFactory;
import models.UserGroup;
//import models.DAOFactory;
//import models.GroupDAO;

import models.UserGroupDAO;
import play.*;
import play.mvc.*;
import views.html.*;

public class GroupController extends Controller {

    public static Result createGroup(String groupName, String groupDescription, String username){
        // Create group in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserGroupDAO userGroupDAO = daoFactory.getGroupDAO();
        models.UserGroup group = new models.UserGroup(groupName, groupDescription, username);

        // render groups 
        return ok(views.html.group.render(group));
    }

}