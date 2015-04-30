package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.login.*;

import models.*;

public class Login extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<UserFake> loginForm = form(UserFake.class, UserFake.DoLog.class);
  
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
        Form<UserFake> filledForm = loginForm.bindFromRequest();
        
        if (filledForm.get().password.equals("password")) {
            filledForm.reject("password", "Your username or password was incorrect");
        }
        
        if(filledForm.hasErrors()) {
            return badRequest(form.render(filledForm));
        } else {
            UserFake created = filledForm.get();
            return ok(summary.render(created));
        }
    }
  
}