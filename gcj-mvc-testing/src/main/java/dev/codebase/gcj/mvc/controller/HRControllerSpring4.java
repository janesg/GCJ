package dev.codebase.gcj.mvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.codebase.gcj.mvc.model.Employee;

@RestController
public class HRControllerSpring4 {

    // Automatic type inference during generic class instantiation : as of Java SE 7
    private Map<Long, Employee> database = new HashMap<>();
    
    public HRControllerSpring4() {
        loadSampleData();
    }

    private void loadSampleData() {
        Employee e = new Employee(1);
        e.setName("Alexis Sanchez");
        e.setSalary(new BigDecimal("130000.00"));
        database.put(e.getId(), e);
        
        e = new Employee(2);
        e.setName("Alex Oxlade-Chamberlain");
        e.setSalary(new BigDecimal("65000.00"));
        database.put(e.getId(), e);
                
        e = new Employee(3);
        e.setName("Theo Walcott");
        e.setSalary(new BigDecimal("110000.00"));
        database.put(e.getId(), e);
    }
    
    @RequestMapping(value="/employeesSpring4/{id}", method=RequestMethod.GET)
    public Employee retrieve(@PathVariable long id) {
        return database.get(id);
    }
    
    @RequestMapping(value="/employeesSpring4", method=RequestMethod.GET)
    public List<Employee> retrieveAll() {
        
        // Introduce a sleep to see effect on asynchronous request logging timestamps
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return new ArrayList<Employee>(database.values());
    }
    
}
