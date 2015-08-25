package dev.codebase.gcj.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GreeterServiceImpl2 implements GreeterService,
                                            ApplicationContextAware, 
                                            BeanNameAware,
                                            BeanFactoryAware,
                                            InitializingBean,
                                            BeanPostProcessor,
                                            DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreeterServiceImpl2.class);
    
    private String greeting;

    @Autowired
    public GreeterServiceImpl2(String greeting) {
        this.greeting = greeting;
    }
    
    @Override
    public String provideGreeting() {
        return greeting;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("destroy() method called");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessBeforeInitialization() method called: " +
                    (bean != null ? bean.toString() : "NULL"));
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("postProcessAfterInitialization() method called: " +
                    (bean != null ? bean.toString() : "NULL"));
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet() method called");        
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOGGER.info("setBeanFactory() method called: " + 
                    (beanFactory != null ? beanFactory.toString() : "NULL"));        
    }

    @Override
    public void setBeanName(String name) {
        LOGGER.info("setBeanName() method called: " + (name != null ? name : "NULL"));        
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("setApplicationContext() method called: " + 
                    (applicationContext != null ? applicationContext.toString() : "NULL"));        
    }
    
}
