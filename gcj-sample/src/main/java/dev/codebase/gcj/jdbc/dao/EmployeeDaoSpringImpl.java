package dev.codebase.gcj.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.codebase.gcj.jdbc.model.Employee;

@Repository
public class EmployeeDaoSpringImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoSpringImpl.class);

    @Autowired
    DataSource dataSource;
    
    @Override
    public Employee getEmployeeById(int id) {
        Connection conn = null;
        Employee emp = null;
        
        try {
            conn = dataSource.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("select * from EMPLOYEE where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                emp = new Employee(id, rs.getString("name"));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        
        return emp;
    }

    @Override
    public void insertEmployee(Employee employee) {
        Connection conn = null;
        
        try {
            conn = dataSource.getConnection();
            
            Statement stm = conn.createStatement();
            String sql = "insert into EMPLOYEE (id, name) values (" + 
                         employee.getId() + ", '" + employee.getName() + "')";
            LOGGER.info("SQL : " + sql);
            stm.executeQuery(sql);
            stm.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        
    }

    @Override
    public void deleteEmployee(int id) {
        Connection conn = null;
        
        try {
            conn = dataSource.getConnection();
            
            Statement stm = conn.createStatement();
            stm.executeQuery("delete from EMPLOYEE where id = " + id);
            stm.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        
    }

    @Override
    public void insertEmployees(List<Employee> employees) {
        throw new UnsupportedOperationException("Couldn't be bothered to implement change to interface");
    }

    @Override
    public void deleteEmployees(List<Integer> ids) {
        throw new UnsupportedOperationException("Couldn't be bothered to implement change to interface");
    }

    @Override
    public int getEmployeeCount() {
        throw new UnsupportedOperationException("Couldn't be bothered to implement change to interface");
    }

}
