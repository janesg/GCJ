package dev.codebase.gcj.testing;

import java.math.BigDecimal;

public interface EmployeeService {

    Employee createEmployee(int id, String firstName, String lastName, BigDecimal salary);
    
    void updateEmployee(Employee e);
    
    void deleteEmployee(int id);
    
    Employee findEmployee(int id);
}
