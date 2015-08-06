package dev.codebase.gcj.hibernate.dao;

import java.util.List;

import dev.codebase.gcj.hibernate.model.Employee;

public interface EmployeeDao {
    
    List<Employee> getAllEmployees();
    
    Integer insertEmployee(Employee employee);
    
    void deleteEmployee(Integer id);
    
}
