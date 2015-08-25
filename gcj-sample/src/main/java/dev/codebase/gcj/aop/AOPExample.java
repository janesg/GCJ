package dev.codebase.gcj.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPExample {

    private static Logger LOGGER = LoggerFactory.getLogger(AOPExample.class);
    
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(args[0]);
        MessageWriter mw = ctx.getBean(MessageWriter.class);
        
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new MessageDecorator());
        pf.setTarget(mw);
        
        MessageWriter proxy = (MessageWriter) pf.getProxy();
                
        LOGGER.info("Original:");
        mw.writeMessage();
        
        LOGGER.info("Proxy:");
        proxy.writeMessage();
        
        ctx.close();        
    }

}
