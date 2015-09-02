package dev.codebase.gcj.mvc;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.codebase.gcj.mvc.controller.LoginDetails;
import dev.codebase.gcj.mvc.controller.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/application-context.xml")
@WebAppConfiguration
public class LoginControllerTest {

    @Autowired
    private LoginService loginService;
    
    @Autowired
    private LoginDetails loginDetails;
    
    @Autowired
    private MockHttpServletRequest request;
    
    @Autowired
    private MockHttpSession session;
    
    @Test
    public void requestScope() {
        request.setParameter("userId", "Gary");
        request.setParameter("password", "Gary");
        
        assertTrue(loginService.isValid());
    }
    
    @Test
    public void sessionScope() {
        LocalDate now = LocalDate.now();
        
        session.setAttribute("userId", "Bob");
        session.setAttribute("loggedInDateTime", now);
        
        assertEquals("Bob", loginDetails.getUser());
        assertEquals(now, loginDetails.getLoginDateTime());
    }
    
}
