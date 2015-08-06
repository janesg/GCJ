package dev.codebase.gcj.spring;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.codebase.gcj.hibernate.service.EmployeeService;
import dev.codebase.gcj.hibernate.model.Employee;

public class EmployeeSystemHibernate {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSystemHibernate.class);
    
    public static void main(String[] args) {
        
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(args);        
        
        EmployeeService empService = ctx.getBean("employeeServiceImpl", EmployeeService.class);
        
        Employee emp = new Employee();
        emp.setFirstName("Mickey");
        emp.setLastName("Mouse");
        emp.setJobTitle("Disney Character");
        emp.setSalary(new BigDecimal("19345.53"));

        Integer id = empService.insertEmployee(emp);
        
        LOGGER.info("Id of new employee 'Mickey Mouse' : " + (id != null ? id : "NULL"));

        List<Employee> emps = empService.getAllEmployees();

        LOGGER.info("Employee Count <pre-delete> : " + emps.size());            

        for (Employee e : emps) {
            LOGGER.info("Employee : " + e);            
        }
        
        empService.deleteEmployee(id);
        
        emps = empService.getAllEmployees();
        
        LOGGER.info("Employee Count <post-delete> : " + emps.size());            

        ctx.close();
    }

}
