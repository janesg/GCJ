package dev.codebase.gcj.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.codebase.gcj.hibernate.dao.EmployeeDao;
import dev.codebase.gcj.hibernate.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;
    
    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public Integer insertEmployee(Employee employee) {
        return dao.insertEmployee(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        dao.deleteEmployee(id);        
    }

}
