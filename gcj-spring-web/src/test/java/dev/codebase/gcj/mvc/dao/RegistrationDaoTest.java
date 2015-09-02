package dev.codebase.gcj.mvc.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationDaoTest {

    private RegistrationDaoImpl classUnderTest;
    
    @Mock
    private JdbcTemplate jdbcTemplate;
    
    @Before
    public void setup() {
        classUnderTest = new RegistrationDaoImpl(jdbcTemplate);
    }
    
    @Test
    public void testWhenCreateUserSuccessful() {
        
        // Can't use anyVarargs() otherwise Mockito unsure which method to stub
        when(jdbcTemplate.update(anyString(), 
                                 anyObject(), 
                                 anyObject(), 
                                 anyObject(), 
                                 anyObject())).thenReturn(1);
        
        String userId = "bob27";
        String password = "flob@bob@job";
        String firstName = "Bob";
        String lastName = "Smurthwaite";
        
        classUnderTest.create(userId, password, firstName, lastName);
        
        // Verify the method call and arguments
        ArgumentCaptor<Object> varArgs = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<String> strArgs = ArgumentCaptor.forClass(String.class);
        
        verify(jdbcTemplate).update(strArgs.capture(), 
                                    varArgs.capture(), 
                                    varArgs.capture(), 
                                    varArgs.capture(), 
                                    varArgs.capture());
        
        assertEquals(userId, varArgs.getAllValues().get(0));
        assertEquals(password, varArgs.getAllValues().get(1));
        assertEquals(firstName, varArgs.getAllValues().get(2));
        assertEquals(lastName, varArgs.getAllValues().get(3));
                
    }
    
    @Test(expected = RuntimeException.class)
    public void testWhenCreateUserFails() {
        
        // Can't use anyVarargs() otherwise Mockito unsure which method to stub
        when(jdbcTemplate.update(anyString(), 
                                 anyObject(), 
                                 anyObject(), 
                                 anyObject(), 
                                 anyObject())).thenReturn(0);
        
        String userId = "bob27";
        String password = "flob@bob@job";
        String firstName = "Bob";
        String lastName = "Smurthwaite";
        
        classUnderTest.create(userId, password, firstName, lastName);
        
    }
    
}
