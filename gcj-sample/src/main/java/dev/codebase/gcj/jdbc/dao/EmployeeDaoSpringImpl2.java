package dev.codebase.gcj.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import dev.codebase.gcj.jdbc.model.Employee;

@Repository
public class EmployeeDaoSpringImpl2 implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Employee getEmployeeById(int id) {
        
        String query = "select * from EMPLOYEE where id = ?";
        
        Employee emp = jdbcTemplate.query(
                            query, 
                            new Object[] {id},
                            new ResultSetExtractor<Employee>() {
                                @Override
                                public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
                                    return rs.next() ? new Employee(rs.getInt("id"), rs.getString("name")) : null;
                                }
                            });
        
        return emp;
    }

    @Override
    public void insertEmployee(Employee employee) {

        String sql = "insert into EMPLOYEE (id, name) values (?, ?)";
        
        int ret = jdbcTemplate.update(
                            sql, 
                            new Object[] {employee.getId(), employee.getName()},
                            new int[] {Types.INTEGER, Types.VARCHAR});
        
        assert ret == 1 : "Employee not inserted";
        
    }

    @Override
    public void deleteEmployee(int id) {

        String sql = "delete from EMPLOYEE where id = ?";
        
        int ret = jdbcTemplate.update(
                            sql, 
                            new Object[] {id});
        
        assert ret == 1 : "Employee not deleted";
        
    }

    @Override
    public void insertEmployees(final List<Employee> employees) {

        String sql = "insert into EMPLOYEE (id, name) values (?, ?)";
        
        jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {                    
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Employee emp = employees.get(i);
                        ps.setInt(1, emp.getId());
                        ps.setString(2, emp.getName());
                    }
                    
                    @Override
                    public int getBatchSize() {
                        return employees.size();
                    }
                });
        
    }

    @Override
    public void deleteEmployees(List<Integer> ids) {
        
        String sql = "delete from EMPLOYEE where id = ?";
        
        jdbcTemplate.batchUpdate(
                sql,
                new BatchPreparedStatementSetter() {                    
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, ids.get(i));
                    }
                    
                    @Override
                    public int getBatchSize() {
                        return ids.size();
                    }
                });
                
    }

    @Override
    public int getEmployeeCount() {
        
        Integer result = jdbcTemplate.queryForObject("select count(*) from EMPLOYEE", Integer.class);
        
        return result != null ? result : 0; 
    }

}
