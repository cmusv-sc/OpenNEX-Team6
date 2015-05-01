package controllers;

import models.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.makesession.*;

import models.*;

public class MakeSession extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<models.Session> signupForm = form(models.Session.class);
  
    /**
     * Display a blank form.
     */ 
    public static Result blank() {
        return ok(form.render(signupForm));
    }

    /**
     * Handle the form submission.
     */
    public static Result submit() {
        Form<models.Session> sessionForm =  signupForm.bindFromRequest();
        models.Session session = sessionForm.get();
        session.setAdmin(System.getProperty("user.name"));
        User user = User.byId(1L);
        session.setUser(user);
        session.save();
        user.setSession(session.byTopic(session.getTopic()));
        user.save();
        return ok(summary.render(session));
    }
}