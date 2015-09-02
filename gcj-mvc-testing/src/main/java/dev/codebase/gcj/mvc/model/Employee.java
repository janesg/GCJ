package dev.codebase.gcj.mvc.model;

import java.math.BigDecimal;

public class Employee {

    private long id;
    private String name;
    private BigDecimal salary;
    
    public Employee(long id) {
        super();
        this.id = id;
    }

    public BigDecimal getSalary() {
        return salary;
    }
    
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }    
    
    public void setName(String name) {
        this.name = name;
    }
    
}
