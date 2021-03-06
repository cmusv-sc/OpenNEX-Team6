package controllers;

//import models.DAOFactory;
//import models.SessionDAO;
import models.DAOFactory;
import models.SessionDAO;
import models.User;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import views.html.findgroup.*;

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
        return ok(sessionsummary.render(session));
    }

    public static Result createSession(){
        // Create session in DB
        DAOFactory daoFactory = DAOFactory.getInstance();
        SessionDAO sessionDAO = daoFactory.getSessionDAO();

        final DynamicForm form = play.data.Form.form().bindFromRequest();
        final String topic = form.get("sessionTopic");
        final String description = form.get("sessionDescription");
        final String admin = "Gautam";//form.get("admin");

        models.Session session = new models.Session();
        session = new models.Session();
        session.setAdmin(admin);
        session.setDescription(description);
        session.setTopic(topic);
//
        // add session user to list
//        List<String> sessionUsers = new ArrayList<String>();
//        sessionUsers.add(admin);
//        session.setSessionUsers(sessionUsers);
        User user = new User();
        user.setId((long) 1);
        user.setEmail("admin@google.com");
        List<User> users = new ArrayList<User>();
        users.add(user);
        session.setUsers(users);
        sessionDAO.save(session);

        // render session tempelate
        return redirect("/session?topic=" + session.getTopic());
    }
}
