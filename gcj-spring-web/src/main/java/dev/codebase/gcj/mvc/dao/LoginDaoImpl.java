package dev.codebase.gcj.mvc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDaoImpl implements LoginDao {

    private final JdbcTemplate jdbcTemplate;
    
    public LoginDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getUserPassword(String userId) {
        if (userId == null || userId.trim().length() == 0) {
            return null;
        }
        
        if (!isExistingUser(userId)) {
            return null;
        }
        
        String sql = "select password from sec_user where userId = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }

    private boolean isExistingUser(String userId) {
        if (userId == null || userId.trim().length() == 0) {
            return false;
        }
        
        String sql = "select count(*) from sec_user where userId = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, userId);
    }
    
}
