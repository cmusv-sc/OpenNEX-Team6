package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.makeproject.*;

import models.*;

public class MakeProject extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<models.Project> signupForm = form(models.Project.class);
  
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

       Form<models.Project> filledForm = signupForm.bindFromRequest();
        System.out.println(filledForm);
        Project project = filledForm.get();
        User user = User.byId(1L);
        project.setUser(user);
        project.setSession(user.getSession());
        project.save();
        user.setProject(Project.byDescription(project.getDescription()));
        user.save();
        return ok(summary.render(project));
    }
  
}