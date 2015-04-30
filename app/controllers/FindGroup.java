package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.findgroup.*;

import models.*;

public class FindGroup extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<UserGroup> loginForm = form(UserGroup.class);
  
    /**
     * Display a blank form.
     */ 
    public static Result blank() {
        return ok(form.render(loginForm));
    }
  
    /**
     * Handle the form submission.
     */
    public static Result submit() {
        return ok(sessionsummary.render(new UserGroup()));
    }
  
}