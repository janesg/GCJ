package dev.codebase.gcj.hibernate.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EMPLOYEE_INFO")
public class Employee {
    
    @Id
    @Column(name = "ID")
/*
 * Using this approach works, but sequence.nextVal() gets called twice for each 
 * insert, once by the 'BEFORE INSERT' trigger and once by Hibernate. Leads to skipped id values
 * 
    @GenericGenerator(name = "generator", 
                      strategy = "sequence-identity", 
                      parameters = @Parameter(name = "sequence", value = "EMPLOYEE_INFO_SEQ"))
    @GeneratedValue(generator = "generator")
*/
    // Instead use this custom generator
    // - https://forum.hibernate.org/viewtopic.php?f=1&t=973262&start=45
    @GenericGenerator(name = "triggerAssigned", 
                      strategy = "jpl.hibernate.util.TriggerAssignedIdentityGenerator")    
    @GeneratedValue(generator = "triggerAssigned")
    private Integer id;
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name = "JOB_TITLE")
    private String jobTitle;
    
    //@Column(name = "DEPARTMENT")
    private String department;
    
    //@Column(name = "SALARY")
    private BigDecimal salary;
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
        
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + 
               ", lastName=" + lastName + ", jobTitle=" + jobTitle + 
               ", department=" + department + ", salary=" + salary + "]";
    }
    
    
}
