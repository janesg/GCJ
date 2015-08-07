package dev.codebase.gcj.testing;

public interface EmployeeDao {
    
    boolean createEmployee(Employee employee);
    
    boolean updateEmployee(Employee employee);
    
    Employee deleteEmployee(int id);
    
    Employee findEmployeeById(int id);
}
