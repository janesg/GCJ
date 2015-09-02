package dev.codebase.gcj.mvc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.codebase.gcj.mvc.model.Employee;

@Controller
public class HRController {

    // Automatic type inference during generic class instantiation : as of Java SE 7
    private Map<Long, Employee> database = new HashMap<>();
    
    public HRController() {
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
    
    @RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Employee retrieve(@PathVariable long id) {
        return database.get(id);
    }
    
    @RequestMapping(value="/employees", method=RequestMethod.GET)
    @ResponseBody
    public List<Employee> retrieveAll() {
        return new ArrayList<Employee>(database.values());
    }
    
}
