package dev.codebase.gcj.spring;

public class GreeterServiceImpl implements GreeterService {

    private String greeting;
    
    public GreeterServiceImpl(String greeting) {
        super();
        this.greeting = greeting;
    }

    @Override
    public String provideGreeting() {
        return greeting;
    }

}
