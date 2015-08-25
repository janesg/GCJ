package dev.codebase.gcj.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/SpringTesting.xml")
public class InMemoryEmployeeDaoTests {

    private static Employee FOUNDER = new Employee(21, "Gary", "Janes", new BigDecimal("20750.15"));
    private InMemoryEmployeeDaoImpl impl = null;
    
    @Before
    public void setup() {
        impl = new InMemoryEmployeeDaoImpl();
        impl.createEmployee(FOUNDER);
    }
    
    @Test
    public void testCreateEmployee() {
        Employee e = new Employee(21);
        assertFalse(impl.createEmployee(e));
        
        e = new Employee(15);
        assertTrue(impl.createEmployee(e));
    }

    @Test
    public void testUpdateEmployee() {
        Employee e = new Employee(21);
        assertTrue(impl.updateEmployee(e));
        
        e = new Employee(15);
        assertFalse(impl.updateEmployee(e));
    }

    @Test
    public void testDeleteEmployee() {
        assertSame(impl.deleteEmployee(21), FOUNDER);
        assertNull(impl.deleteEmployee(15));
    }

    @Test
    public void testFindEmployeeById() {
        assertSame(impl.deleteEmployee(21), FOUNDER);
        assertNull(impl.findEmployeeById(15));
    }

}
