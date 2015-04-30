package controllers;

import models.DAOFactory;
import models.User;
import models.UserGroup;

import models.UserGroupDAO;
import play.*;
import play.mvc.*;
import views.html.*;

import java.util.List;

public class GroupController extends Controller {

    public static Result createGroup(String groupName, String groupDescription, User admin){
        // Create group in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserGroupDAO userGroupDAO = daoFactory.getGroupDAO();
        models.UserGroup group = new models.UserGroup(groupName, groupDescription, admin);

        // render groups 
        return ok(views.html.group.render(group));
    }

    public static Result deleteGroup(long groupId){
    	// todo implement this
    	return ok("");
    }

    public static Result listGroups(){
    	// Get groups
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserGroupDAO userGroupDAO = daoFactory.getGroupDAO();
        List<models.UserGroup> groups = userGroupDAO.loadGroups();

        // todo implement this
        return ok("");
    } 

    public static Result displayGroup(long groupId){
        // Get group details
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserGroupDAO userGroupDAO = daoFactory.getGroupDAO();
        models.UserGroup group = userGroupDAO.getGroupById(groupId);

        // render groups
        return ok(views.html.group.render(group));
    }
}