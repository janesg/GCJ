package dev.codebase.gcj.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.codebase.gcj.jdbc.dao.EmployeeDao;
import dev.codebase.gcj.jdbc.model.Employee;

public class EmployeeSystem {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSystem.class);
    
    public static void main(String[] args) {
        
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(args);        
        
        EmployeeDao dao = ctx.getBean("employeeDaoSpringImpl", EmployeeDao.class);
        
        Employee emp = dao.getEmployeeById(4);
        
        assert emp == null : "Employee with an Id of 4 already exists";
        
        dao.insertEmployee(new Employee(4, "Bob"));
        emp = dao.getEmployeeById(4);
        
        assert emp != null && emp.getName().equals("Bob") : "Not able to query for Bob";
        
        LOGGER.info("Retrieved employee : " + emp);
        
        dao.deleteEmployee(emp.getId());
        
        emp = dao.getEmployeeById(4);
        
        assert emp == null : "Employee Bob still exists";
        
        ctx.close();
    }

}
