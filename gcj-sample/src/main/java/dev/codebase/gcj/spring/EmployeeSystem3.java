package dev.codebase.gcj.spring;

import static com.google.common.collect.Collections2.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Function;

import dev.codebase.gcj.jdbc.dao.EmployeeDao;
import dev.codebase.gcj.jdbc.model.Employee;

public class EmployeeSystem3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSystem3.class);
    
    public static void main(String[] args) {
        
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(args);        
        
        EmployeeDao dao = ctx.getBean("employeeDaoSpringImpl3", EmployeeDao.class);
        
        Employee emp = dao.getEmployeeById(4);
        
        assert emp == null : "Employee with an Id of 4 already exists";
        
        dao.insertEmployee(new Employee(4, "Bob"));
        emp = dao.getEmployeeById(4);
        
        assert emp != null && emp.getName().equals("Bob") : "Not able to query for Bob";
        
        LOGGER.info("Retrieved employee : " + emp);
        
        emp = dao.getEmployeeByIdUsingFunction(4);
        
        assert emp != null && emp.getName().equals("Bob") : "Not able to query for Bob";
        
        LOGGER.info("Retrieved employee using package function : " + emp);
        
        dao.deleteEmployee(emp.getId());
        
        emp = dao.getEmployeeById(4);
        
        assert emp == null : "Employee Bob still exists";
        
        List<Employee> employees = new ArrayList<Employee>();
        
        for (int i = 5; i < 10; i++) {
            employees.add(new Employee(i, "Employee" + i));
        }
        
        int count = dao.getEmployeeCount();

        LOGGER.info("Initial employee count : " + count);
        
        dao.insertEmployees(employees);
        
        int newCount = dao.getEmployeeCount();
        
        LOGGER.info("Employee count after batch insertion : " + newCount);
        
        assert newCount == count + employees.size() : "Unexpected number of employees";
        
        List<Integer> ids = new ArrayList<Integer>(
                            transform(employees, 
                                      new Function<Employee,Integer>(){
                                          @Override
                                          public Integer apply(Employee emp) {
                                              return emp.getId();
                                          }    
                                      }));

        dao.deleteEmployees(ids);
        
        newCount = dao.getEmployeeCount();
        
        assert newCount == count : "Didn't manage to remove all the added employees";
                
        ctx.close();
    }

}
