package dev.codebase.gcj.testing;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;
    
    @Override
    public Employee findEmployee(int id) {
        return dao.findEmployeeById(id);
    }

    @Override
    public Employee createEmployee(int id, String firstName, String lastName, BigDecimal salary) {
        Employee e = new Employee(id, firstName, lastName, salary);
        
        if (dao.createEmployee(e)) {
            dao.sayHiToEmployee(e);
            return e;
        } else {
            return null;
        }
    }

    @Override
    public void updateEmployee(Employee e) {
        dao.updateEmployee(e);
    }

    @Override
    public void deleteEmployee(int id) {
        dao.deleteEmployee(id);
    }
    
}
