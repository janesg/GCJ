package dev.codebase.gcj.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import dev.codebase.gcj.mvc.dao.RegistrationDao;

public class RegistrationServiceImpl implements RegistrationService {

    static final String ENTER_USER = "Please enter user id";
    static final String ENTER_PASSWORD = "Please enter password";
    static final String ENTER_FIRST_NAME = "Please enter first name";
    static final String ENTER_LAST_NAME = "Please enter last name";
    static final String NO_SPECIAL_IN_NAME = "Name cannot contain special characters";
    static final String NO_NUMERIC_IN_NAME = "Name cannot contain numbers";
    static final String SPECIAL_IN_PASSWORD = "Password must contain at least one special character";
    static final String USER_EXISTS = "User id exists already";
    static final String CREATE_USER_FAILED = "Unable to create user";
        
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    
    @Autowired
    private RegistrationDao dao;
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDao(RegistrationDao dao) {
        this.dao = dao;
    }

    @Override
    public String getError() {
        
        if (isEmpty(userId)) {
            return ENTER_USER;
        }
        
        if (dao.isExistingUser(userId)) {
            return USER_EXISTS;
        }
        
        if (isEmpty(password)) {
            return ENTER_PASSWORD;
        }
        
        if (isEmpty(firstName)) {
            return ENTER_FIRST_NAME;
        }
        
        if (isEmpty(lastName)) {
            return ENTER_LAST_NAME;
        }
        
        if (isSpecial(firstName) || isSpecial(lastName)) {
            return NO_SPECIAL_IN_NAME;
        }
        
        if (isNumeric(firstName) || isNumeric(lastName)) {
            return NO_NUMERIC_IN_NAME;
        }
        
        if (!isSpecial(password)) {
            return SPECIAL_IN_PASSWORD;
        }
        
        try {
            dao.create(userId, password, firstName, lastName);
        } catch (Exception e) {
            return CREATE_USER_FAILED + ": " + userId;
        }
        
        return null;
    }

    private boolean isEmpty(String str) {
        return str == null ? true : str.trim().length() == 0;
    }

    private boolean isNumeric(String str) {
        
        if (str == null) {
            return false;
        }
        
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            
            // 0 -> 9
            if (c >= 48 && c <= 57) {
                return true;
            }
        }
        
        return false;
    }

    private boolean isSpecial(String str) {
        
        if (str == null) {
            return false;
        }
        
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            
            // A -> Z, a -> z, 0 -> 9
            if (!(c >= 65 && c <= 90) && !(c >= 97 && c <= 122) && !(c >= 48 && c <= 57)) {
                return true;
            }
        }
        
        return false;
    }

}
