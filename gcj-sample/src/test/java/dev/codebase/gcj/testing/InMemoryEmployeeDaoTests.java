package dev.codebase.gcj.testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/SpringTesting.xml")
@TestExecutionListeners(
        listeners = LoggingTestExecutionListener.class,
        mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
public class InMemoryEmployeeDaoTests {

    private static Employee FOUNDER = new Employee(21, "Gary", "Janes", new BigDecimal("20750.15"));

    // We wouldn't normally use @Autowire to instantiate the class under test
    // - instead we'd just instantiate an object of the correct class in the @Before method
    @Autowired
    private EmployeeDao dao;
    
    // We can always get hold of the Spring test context if we need it
    @Autowired
    private ApplicationContext ctx;
    
    @Before
    public void setup() {
        dao.createEmployee(FOUNDER);
    }
    
    @Test
    public void testContextAvailable() {
        assertNotNull(ctx);
        assertNotNull(ctx.getBean(EmployeeDao.class));
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
