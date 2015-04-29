package controllers;

import models.DAOFactory;
import models.SessionDAO;
import play.mvc.*;
import views.html.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautammadaan on 4/28/15.
 */
public class Session extends Controller {

    public static Result index(String sessionTopic){
        // Get session details
        DAOFactory daoFactory = DAOFactory.getInstance();
        SessionDAO sessionDAO = daoFactory.getSessionDAO();
        models.Session session = sessionDAO.getSessionFromTopic(sessionTopic);

        // Pass session details to view
        return ok(views.html.session.render(session));
    }

    public static Result createSession(String topic, String description, String userName){
        // Create session in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        SessionDAO sessionDAO = daoFactory.getSessionDAO();
        models.Session session = new models.Session(topic, description, userName);

        // add session user to list
        List<String> sessionUsers = new ArrayList<String>();
        sessionUsers.add(userName);
        session.setSessionUsers(sessionUsers);
        sessionDAO.save(session);

        // render session tempelate
        return ok(views.html.session.render(session));
    }
}
