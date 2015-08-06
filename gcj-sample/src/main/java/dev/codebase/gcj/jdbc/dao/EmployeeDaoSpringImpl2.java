package dev.codebase.gcj.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import dev.codebase.gcj.jdbc.model.Employee;

@Repository
public class EmployeeDaoSpringImpl2 implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoSpringImpl2.class);
    
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

    @Override
    public Employee getEmployeeByIdUsingFunction(int id) {

        SqlParameterSource in = new MapSqlParameterSource().addValue("emp_id", id);
        
        SimpleJdbcCall jdbcCall = 
                new SimpleJdbcCall(jdbcTemplate.getDataSource())
                    .withCatalogName("PKG_EMPLOYEE")
                    .withFunctionName("GET_NAME");
        
        Map<String, Object> result = jdbcCall.execute(in);
        
        // Return parameter always called 'return' for Oracle...other DBs differ
        LOGGER.info("Returned Map : " + result.toString());
        
        jdbcCall = 
                new SimpleJdbcCall(jdbcTemplate.getDataSource())
                    .withCatalogName("PKG_EMPLOYEE")
                    .withFunctionName("GET_NAME2");
        
        result = jdbcCall.execute(in);
        
        LOGGER.info("Returned Map 2 : " + result.toString());
        
        return new Employee(id, (String) result.get("EMP_NAME"));
    }

}
