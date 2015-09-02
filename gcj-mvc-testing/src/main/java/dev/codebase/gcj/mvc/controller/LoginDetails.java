package dev.codebase.gcj.mvc.controller;

import java.time.LocalDate;

public class LoginDetails {

    private String user;
    private LocalDate loginDateTime;
    
    public LoginDetails(String user, LocalDate loginDateTime) {
        super();
        this.user = user;
        this.loginDateTime = loginDateTime;
    }
    
    public String getUser() {
        return user;
    }
    
    public LocalDate getLoginDateTime() {
        return loginDateTime;
    }
        
}
