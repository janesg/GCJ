package dev.codebase.gcj.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import dev.codebase.gcj.mvc.dao.LoginDao;

public class LoginServiceImpl implements LoginService {

    private String userId;
    private String password;
    
    @Autowired
    private LoginDao dao;
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean isValid() {
        if (userId != null && password != null) {
            return password.equals(dao.getUserPassword(userId));
        } else {
            return false;
        }
    }

    @Override
    public String retrieveName() {
        return userId;
    }
}
