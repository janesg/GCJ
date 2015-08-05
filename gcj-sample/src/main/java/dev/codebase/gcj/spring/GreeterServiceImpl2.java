package dev.codebase.gcj.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class GreeterServiceImpl2 implements GreeterService {

    private String greeting;

    @Autowired
    public GreeterServiceImpl2(String greeting) {
        this.greeting = greeting;
    }
    
    @Override
    public String provideGreeting() {
        return greeting;
    }

}
