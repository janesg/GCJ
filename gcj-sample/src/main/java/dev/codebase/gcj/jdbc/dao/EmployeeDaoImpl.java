package dev.codebase.gcj.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.codebase.gcj.jdbc.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    
    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public Employee getEmployeeById(int id) {
        Connection conn = null;
        Employee emp = null;
        
        registerDriver();
        
        try {
            conn = DriverManager.getConnection(DB_URL, "gcj", "darwin");
            
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
        
        registerDriver();
        
        try {
            conn = DriverManager.getConnection(DB_URL, "gcj", "darwin");
            
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
        
        registerDriver();
        
        try {
            conn = DriverManager.getConnection(DB_URL, "gcj", "darwin");
            
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
