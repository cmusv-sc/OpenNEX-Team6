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
    final static Form<Project> signupForm = form(Project.class);
  
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
        
        return ok(summary.render(new Project()));
    }
  
}