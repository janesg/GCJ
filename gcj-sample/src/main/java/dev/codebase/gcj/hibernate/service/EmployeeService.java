package dev.codebase.gcj.hibernate.service;

import java.util.List;

import dev.codebase.gcj.hibernate.model.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    
    Integer insertEmployee(Employee employee);
    
    void deleteEmployee(Integer id);
}
