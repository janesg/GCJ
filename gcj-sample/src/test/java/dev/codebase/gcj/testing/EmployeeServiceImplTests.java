package dev.codebase.gcj.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
//import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImplTests.class);
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
    public void sanityCheck() {
        assertNotNull(dao);
        assertNotNull(classUnderTest);
    }
    
    @Test
    public void testFindEmployee() {
        // Using Mockito to define 'Stub' for verifying state
        //when(dao.findEmployeeById(anyInt())).thenReturn(EMPLOYEE);
        //when(dao.findEmployeeById(EMPLOYEE.getId())).thenReturn(EMPLOYEE);
        when(dao.findEmployeeById(anyInt())).thenAnswer(new Answer<Employee>() {
            @Override
            public Employee answer(InvocationOnMock invocation) throws Throwable {
                return EMPLOYEE;
            }            
        });
        
        // Invoke the method under test
        Employee e = classUnderTest.findEmployee(EMPLOYEE.getId());
        
        // Test the state
        assertEquals(e.getFirstName(), "Gary");
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).findEmployeeById(EMPLOYEE.getId());
        
        // Make sure no other interactions with the dao
        // Don't over-use it: http://monkeyisland.pl/2008/07/12/should-i-worry-about-the-unexpected/
        verifyNoMoreInteractions(dao);
        
        // Instead, use 'only' : fails if any other method on any mock was called
        verify(dao, only()).findEmployeeById(EMPLOYEE.getId());

    }

    @Test
    public void testCreateEmployee() {
        // Stubbing a non-void return method
        when(dao.createEmployee(EMPLOYEE)).thenReturn(Boolean.TRUE);
        
        // By default, a void return method on a mock will do nothing
        // Stubbing a void return method using doAnswer just to make it do something on invocation
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Employee e = (Employee) invocation.getArguments()[0];
                LOGGER.info("Hello " + e.getFirstName() + " " + e.getLastName());
                return null;
            }
        }).when(dao).sayHiToEmployee(isA(Employee.class));
        
        // Invoke the method under test
        Employee e = classUnderTest.createEmployee(EMPLOYEE.getId(), 
                                                   EMPLOYEE.getFirstName(), 
                                                   EMPLOYEE.getLastName(), 
                                                   null);
        
        // Test the state
        assertEquals(e.getFirstName(), "Gary");
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(dao).createEmployee(EMPLOYEE);
        verify(dao).sayHiToEmployee(EMPLOYEE);
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
