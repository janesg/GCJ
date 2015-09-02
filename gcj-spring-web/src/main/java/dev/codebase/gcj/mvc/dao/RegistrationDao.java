package dev.codebase.gcj.mvc.dao;

public interface RegistrationDao {
    
    boolean isExistingUser(String userId);
    
    void create(String userId, String password, String firstName, String lastName);
    
}
