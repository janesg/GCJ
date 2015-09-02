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

import dev.codebase.gcj.mvc.controller.LoginController;
import dev.codebase.gcj.mvc.controller.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private LoginController classUnderTest;
    
    @Mock
    private LoginService loginService;
    
    @Before
    public void setup() {
        classUnderTest = new LoginController(loginService);
    }
    
    @Test
    public void testInvalidLogin() {
        
        when(loginService.isValid()).thenReturn(false);
        
        ModelMap map = new ModelMap();
        ModelAndView mav = classUnderTest.onLogin(map);
        
        assertNotNull(mav.getModel().get("error"));
        assertEquals(LoginController.LOGIN_PAGE, mav.getViewName());
    }
    
    @Test
    public void testValidLogin() {
        
        when(loginService.isValid()).thenReturn(true);
        
        ModelMap map = new ModelMap();
        ModelAndView mav = classUnderTest.onLogin(map);
        
        assertNull(mav.getModel().get("error"));
        assertNotNull(mav.getModel().get("name"));
        assertEquals(LoginController.GREETINGS_PAGE, mav.getViewName());
    }
        
}
