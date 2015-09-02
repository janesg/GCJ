package dev.codebase.gcj.mvc.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class LoginController implements Serializable {
    
    static final String LOGIN_PAGE = "login";    
    static final String GREETINGS_PAGE = "greetings";    
    
    private LoginService loginService;

    // Make controller's non-default constructor autowired otherwise 
    // Spring will complain about 'no default constructor'
    @Autowired
    public LoginController(LoginService loginService) {
        super();
        this.loginService = loginService;
    }

    @RequestMapping({"/", "/login"})
    public String onStartup(ModelMap model) {
        return LOGIN_PAGE;
    }
    
    @RequestMapping({"/onLogin"})
    public ModelAndView onLogin(ModelMap model) {
        
        if (!loginService.isValid()) {
            model.addAttribute("error", "Invalid user name and password");
            return new ModelAndView(LOGIN_PAGE, model);
        }

        String userName = loginService.retrieveName();
        model.addAttribute("name", "Welcome " + userName + " !");
        
        return new ModelAndView(GREETINGS_PAGE, model);        
    }
    
}
