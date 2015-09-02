package dev.codebase.gcj.mvc.controller;

import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class LoginController implements Serializable {
    
    private static final String LOGIN_PAGE = "login";    
    private static final String GREETINGS_PAGE = "greetings";
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private LoginDetails loginDetails;
    
    @RequestMapping({"/", "/login"})
    public String onStartup(ModelMap model) {
        return LOGIN_PAGE;
    }
    
    @RequestMapping({"/onLogin"})
    public ModelAndView onLogin(ModelMap model, 
                                HttpSession session,
                                HttpServletRequest request) {
        
        if (!loginService.isValid()) {
            model.addAttribute("error", "Invalid user name and password");
            return new ModelAndView(LOGIN_PAGE, model);
        }
        
        session.setAttribute("userId", request.getParameter("userId"));
        session.setAttribute("loggedInDateTime", LocalDate.now());
        
        model.addAttribute("name", "Welcome reader!");
        
        return new ModelAndView(GREETINGS_PAGE, model);        
    }
    
    @RequestMapping({"/onLoginDetail"})
    public ModelAndView onLoginDetail(ModelMap model) {
        
        model.addAttribute("name", loginDetails.getUser());
        model.addAttribute("dateTime", loginDetails.getLoginDateTime());
        
        return new ModelAndView(GREETINGS_PAGE, model);        
    }
    
}
