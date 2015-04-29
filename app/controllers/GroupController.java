package controllers;

import models.User;
import models.Group;
import models.DAOFactory;
import models.GroupDAO;

import play.*;
import play.mvc.*;

import views.html.*;

public class GroupController extends Controller {

    public static Result createGroup(String groupName, String groupDescription, String username){
        // Create group in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        GroupDAO groupDAO = daoFactory.getGroupDAO();
        models.Group group = new models.Group(groupName, groupDescription, username);

        // render groups 
        return ok(views.html.group.render(group));
    }

}