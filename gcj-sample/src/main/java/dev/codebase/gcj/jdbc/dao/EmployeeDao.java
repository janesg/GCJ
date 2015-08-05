package dev.codebase.gcj.jdbc.dao;

import java.util.List;

import dev.codebase.gcj.jdbc.model.Employee;

public interface EmployeeDao {
    
    Employee getEmployeeById(int id);
    
    Employee getEmployeeByIdUsingFunction(int id);
    
    void insertEmployee(Employee employee);
    
    void deleteEmployee(int id);

    void insertEmployees(List<Employee> employees);
    
    void deleteEmployees(List<Integer> ids);

    int getEmployeeCount();
}
