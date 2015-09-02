package dev.codebase.gcj.mvc.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    private RegistrationController classUnderTest;
    
    @Mock
    private RegistrationService registrationService;
    
    @Before
    public void setup() {
        classUnderTest = new RegistrationController(registrationService);
    }
    
    @Test
    public void testInvalidLogin() {
        
        when(registrationService.getError()).thenReturn("Non-null String");
        
        ModelMap map = new ModelMap();
        ModelAndView mav = classUnderTest.onRegistration(map);
        
        String msg = (String) mav.getModel().get("message");
        assertNotNull(msg);
        assertTrue(msg.contains(RegistrationController.ERROR_MSG));
    }
    
    @Test
    public void testValidLogin() {
        
        when(registrationService.getError()).thenReturn(null);
        
        ModelMap map = new ModelMap();
        ModelAndView mav = classUnderTest.onRegistration(map);
        
        String msg = (String) mav.getModel().get("message");
        assertNotNull(msg);
        assertTrue(msg.contains(RegistrationController.SUCCESS_MSG));
    }
            
}
