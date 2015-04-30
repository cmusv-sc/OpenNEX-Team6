package models;

import javax.validation.*;

import play.data.validation.Constraints.*;

public class UserFake {
   
    public interface All {}
    public interface DoLog {}

    @Required(groups = {All.class})
    @MinLength(value = 4, groups = {All.class})
    public String username;
    
    @Required(groups = {All.class})
    @Email(groups = {All.class})
    public String email;
    
    @Required(groups = {All.class})
    @MinLength(value = 6, groups = {All.class})
    public String password;
    
    public UserFake() {}
    
    public UserFake(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
}