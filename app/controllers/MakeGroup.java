package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.makegroup.*;

import models.*;

public class MakeGroup extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<models.UserGroup> signupForm = form(models.UserGroup.class);
  
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
        Form<models.UserGroup> filledForm = signupForm.bindFromRequest();
        System.out.println(filledForm);
        UserGroup userGroup = filledForm.get();
        User user = User.byId(1L);
        userGroup.setUser(user);
        userGroup.setSession(user.getSession());
        userGroup.setProject(user.getProject());
        userGroup.save();
        user.setUserGroup(userGroup);
        user.save();
        return ok(summary.render(userGroup));
    }
  
}