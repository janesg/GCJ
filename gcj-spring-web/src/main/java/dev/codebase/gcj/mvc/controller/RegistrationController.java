package dev.codebase.gcj.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    
    static final String REGISTER_PAGE = "register";    
    static final String ERROR_MSG = "Unable to create user due to following error: ";    
    static final String SUCCESS_MSG = "User created successfully";    
    
    private RegistrationService registrationService;
    
    // Make controller's non-default constructor autowired otherwise 
    // Spring will complain about 'no default constructor'
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        super();
        this.registrationService = registrationService;
    }

    @RequestMapping({"register"})
    public String showRegistrationView(ModelMap model) {
        return REGISTER_PAGE;
    }

    @RequestMapping({"/onRegistration"})
    public ModelAndView onRegistration(ModelMap model) {
        
        String error = registrationService.getError();
        
        if (error != null) {
            model.addAttribute("message", ERROR_MSG + error);
        } else {
            model.addAttribute("message", SUCCESS_MSG);            
        }
        
        return new ModelAndView(REGISTER_PAGE, model);            
    }
        
}
