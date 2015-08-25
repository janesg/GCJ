package dev.codebase.gcj.testing;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
        
    public Employee(int id) {
        super();
        this.id = id;
    }

    public Employee(int id, String firstName, String lastName, BigDecimal salary) {
        this(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = (salary != null ? salary : BigDecimal.ZERO);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Employee)) {
            return false;
        }
        
        Employee other = (Employee) obj;
        
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + 
               ", lastName=" + lastName + ", salary=" + salary  + "]";
    }
    
    
    
}
