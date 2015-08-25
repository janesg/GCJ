package dev.codebase.gcj.mvc.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.codebase.gcj.mvc.model.LoginInfo;

@Controller
@Scope("session")
public class LoginController implements Serializable {
    
    private static final String LOGIN_PAGE = "login";    
    private static final String GREETINGS_PAGE = "greetings";
    
    @RequestMapping({"/", "/login"})
    public String onStartup(ModelMap model) {
        model.addAttribute("loginInfo", new LoginInfo());
        model.addAttribute("error", "");
        return LOGIN_PAGE;
    }
    
    @RequestMapping({"/onLogin"})
    public String onLogin(@ModelAttribute("loginInfo") LoginInfo loginInfo,
                          ModelMap model) {
        
        if (!"junit".equals(loginInfo.getUserId())) {
            model.addAttribute("error", "invalid login name");
            return LOGIN_PAGE;
        }
        
        if (!"password".equals(loginInfo.getPassword())) {
            model.addAttribute("error", "invalid password");
            return LOGIN_PAGE;            
        }
        
        model.addAttribute("name", "junit reader");
        return GREETINGS_PAGE;        
    }
    
}
