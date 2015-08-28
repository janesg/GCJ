package dev.codebase.gcj.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/SpringTesting.xml")
public class InMemoryEmployeeDaoTests {

    private static Employee FOUNDER = new Employee(21, "Gary", "Janes", new BigDecimal("20750.15"));
    
    @Autowired
    private EmployeeDao dao;
    
    @Before
    public void setup() {
        dao.createEmployee(FOUNDER);
    }
    
    @Test
    public void testCreateEmployee() {
        Employee e = new Employee(21);
        assertFalse(dao.createEmployee(e));
        
        e = new Employee(15);
        assertTrue(dao.createEmployee(e));
    }

    @Test
    public void testUpdateEmployee() {
        Employee e = new Employee(21);
        assertTrue(dao.updateEmployee(e));
        
        e = new Employee(15);
        assertFalse(dao.updateEmployee(e));
    }

    @Test
    public void testDeleteEmployee() {
        assertSame(dao.deleteEmployee(21), FOUNDER);
        assertNull(dao.deleteEmployee(15));
    }

    @Test
    public void testFindEmployeeById() {
        assertSame(dao.deleteEmployee(21), FOUNDER);
        assertNull(dao.findEmployeeById(15));
    }

}
