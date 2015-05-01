package controllers;

import models.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.login.*;

import models.*;

public class Login extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<models.User> loginForm = form(models.User.class);
  
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
        Form<models.User> filledForm = loginForm.bindFromRequest();
        
        if (filledForm.get().getPassword().equals("password")) {
            filledForm.reject("password", "Your username or password was incorrect");
        }
        
        if(filledForm.hasErrors()) {
            return badRequest(form.render(filledForm));
        } else {
            User user = User.byEmail(filledForm.get().getEmail());
            return ok(summary.render(filledForm.get(), user.getSession()));
        }
    }
}