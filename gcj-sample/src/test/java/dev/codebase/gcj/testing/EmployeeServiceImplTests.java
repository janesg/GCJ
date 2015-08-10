package dev.codebase.gcj.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTests {

    private static final Employee EMPLOYEE = new Employee(25, "Gary", "Janes", null);
    
    // Creates a Mock and injects any dependencies that are also defined as Mock
    // @InjectMocks - injects mock or spy fields into tested object automatically.
    @InjectMocks
    private EmployeeServiceImpl classUnderTest;

    @Mock
    private EmployeeDao dao;

    @Before
    public void init() {
        // Use of MockitoAnnotations.initMocks not necessary when using the MockitoJUnitRunner
        //MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindEmployee() {
        // Using Mockito to define 'Stub' for verifying state
        when(dao.findEmployeeById(EMPLOYEE.getId())).thenReturn(EMPLOYEE);
        
        // Invoke the method under test
        Employee e = classUnderTest.findEmployee(EMPLOYEE.getId());
        
        // Test the state
        assertEquals(e.getFirstName(), "Gary");
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).findEmployeeById(EMPLOYEE.getId());
        
        // Make sure no other interactions with the dao
        // Don't over-use it: http://monkeyisland.pl/2008/07/12/should-i-worry-about-the-unexpected/
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void testCreateEmployee() {
        when(dao.createEmployee(EMPLOYEE)).thenReturn(Boolean.TRUE);
        
        // Invoke the method under test
        Employee e = classUnderTest.createEmployee(EMPLOYEE.getId(), 
                                                   EMPLOYEE.getFirstName(), 
                                                   EMPLOYEE.getLastName(), 
                                                   null);
        
        // Test the state
        assertEquals(e.getFirstName(), "Gary");
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).createEmployee(EMPLOYEE);
    }

    @Test
    public void testUpdateEmployee() {
        when(dao.updateEmployee(EMPLOYEE)).thenReturn(Boolean.TRUE);
        
        // Invoke the method under test
        classUnderTest.updateEmployee(EMPLOYEE);
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).updateEmployee(EMPLOYEE);
    }

    @Test
    public void testDeleteEmployee() {
        when(dao.deleteEmployee(EMPLOYEE.getId())).thenReturn(EMPLOYEE);
        
        // Invoke the method under test
        classUnderTest.deleteEmployee(EMPLOYEE.getId());
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).deleteEmployee(EMPLOYEE.getId());
    }

}
