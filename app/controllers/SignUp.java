package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.signup.*;

import models.*;

public class SignUp extends Controller {
    
    /**
     * Defines a form wrapping the User class.
     */ 
    final static Form<models.User> signupForm = form(models.User.class);
  
    /**
     * Display a blank form.
     */ 
    public static Result blank() {
        return ok(form.render(signupForm));
    }
  
    /**
     * Display a form pre-filled with an existing account.
     */
//    public static Result edit() {

//        return ok(form.render(signupForm.fill(existingUser)));
//    }
  
    /**
     * Handle the form submission.
     */
    public static Result submit() {
        Form<models.User> filledForm = signupForm.bindFromRequest();
        
        // Check accept conditions
        if(!"true".equals(filledForm.field("accept").value())) {
            filledForm.reject("accept", "You must accept the terms and conditions");
        }
        
        // Check repeated password
        if(!filledForm.field("password").valueOr("").isEmpty()) {
            if(!filledForm.field("password").valueOr("").equals(filledForm.field("repeatPassword").value())) {
                filledForm.reject("repeatPassword", "Password don't match");
            }
        }
        
        // Check if the username is valid
//        if(!filledForm.hasErrors()) {
//            if(filledForm.get().username.equals("admin") || filledForm.get().username.equals("guest")) {
//                filledForm.reject("username", "This username is already taken");
//            }
//        }
        
        if(filledForm.hasErrors()) {
            return badRequest(form.render(filledForm));
        } else {
            User user = filledForm.get();
            user.save();
            return ok(summary.render(filledForm.get()));
        }
    }
  
}