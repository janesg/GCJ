package dev.codebase.gcj.testing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEmployeeDaoImpl implements EmployeeDao {

    private Map<Integer, Employee> emps;
    
    public InMemoryEmployeeDaoImpl() {
        super();
        emps = Collections.synchronizedMap(new HashMap<Integer, Employee>());
    }
    
    @Override
    public boolean createEmployee(Employee employee) {
        if (!emps.containsKey(employee.getId())) {
            emps.put(employee.getId(), employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (emps.containsKey(employee.getId())) {
            emps.put(employee.getId(), employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Employee deleteEmployee(int id) {
        return emps.remove(id);
    }

    @Override
    public Employee findEmployeeById(int id) {
        return emps.get(id);
    }

}
