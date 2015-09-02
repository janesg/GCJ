package dev.codebase.gcj.mvc.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dev.codebase.gcj.mvc.dao.RegistrationDao;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    private RegistrationServiceImpl classUnderTest;
    
    @Mock
    private RegistrationDao registrationDao;
    
    @Before
    public void setup() {
        classUnderTest = new RegistrationServiceImpl();
        classUnderTest.setDao(registrationDao);
    }
    
    @Test
    public void testEmptyInputs() {
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.ENTER_USER, error);
                
        classUnderTest.setUserId("Bob27");
        error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.ENTER_PASSWORD, error);
                
        classUnderTest.setPassword("bobs&pwd123");
        error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.ENTER_FIRST_NAME, error);
                
        classUnderTest.setFirstName("Bob");
        error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.ENTER_LAST_NAME, error);
                
        classUnderTest.setLastName("Ajob");
        error = classUnderTest.getError();
        assertNull(error);
                        
    }
    
    @Test
    public void testNoSpecialInName() {
        
        classUnderTest.setUserId("Bob27");
        classUnderTest.setPassword("bobs&pwd123");
        classUnderTest.setFirstName("B@b");
        classUnderTest.setLastName("Ajob");
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.NO_SPECIAL_IN_NAME, error);
                                        
    }
                
    @Test
    public void testNoNumericInName() {
        
        classUnderTest.setUserId("Bob27");
        classUnderTest.setPassword("bobs&pwd123");
        classUnderTest.setFirstName("Bob");
        classUnderTest.setLastName("Ajob3");
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.NO_NUMERIC_IN_NAME, error);
                                        
    }
                
    @Test
    public void testPasswordContainsSpecial() {
        
        classUnderTest.setUserId("Bob27");
        classUnderTest.setPassword("bobspwd123");
        classUnderTest.setFirstName("Bob");
        classUnderTest.setLastName("Ajob");
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.SPECIAL_IN_PASSWORD, error);
                                        
    }
                
    @Test
    public void testUserExists() {
        
        when(registrationDao.isExistingUser(anyString())).thenReturn(true);
        
        classUnderTest.setUserId("Bob27");
        classUnderTest.setPassword("bobs&pwd123");
        classUnderTest.setFirstName("Bob");
        classUnderTest.setLastName("Ajob");
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertEquals(RegistrationServiceImpl.USER_EXISTS, error);
                                        
    }
                
    @Test
    public void testUserCreationFailed() {
        
        when(registrationDao.isExistingUser(anyString())).thenReturn(false);

        // As create returns void we must use the alternative form of stubbing
        doThrow(new RuntimeException("User persistence failed")).
                                when(registrationDao).
                                create(anyString(), anyString(), anyString(), anyString());
        
        classUnderTest.setUserId("Bob27");
        classUnderTest.setPassword("bobs&pwd123");
        classUnderTest.setFirstName("Bob");
        classUnderTest.setLastName("Ajob");
        
        String error = classUnderTest.getError();
        assertNotNull(error);
        assertTrue(error.contains(RegistrationServiceImpl.CREATE_USER_FAILED));
                                        
    }
                
}
