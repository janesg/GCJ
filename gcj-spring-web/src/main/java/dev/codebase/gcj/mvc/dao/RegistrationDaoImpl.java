package dev.codebase.gcj.mvc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class RegistrationDaoImpl implements RegistrationDao {

    private final JdbcTemplate jdbcTemplate;
    
    public RegistrationDaoImpl(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isExistingUser(String userId) {
        if (userId == null || userId.trim().length() == 0) {
            return false;
        }
        
        String sql = "select count(*) from sec_user where userId = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, userId);
    }

    @Override
    public void create(String userId, String password, String firstName, String lastName) {
        String sql = "insert into sec_user (userId, password, firstName, lastName) values (?, ?, ?, ?)";
        int rowCount = jdbcTemplate.update(sql, userId, password, firstName, lastName);
        
        if (rowCount != 1) {
            throw new RuntimeException("Failed to create user");
        }
    }

}
