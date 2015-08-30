package dev.codebase.gcj.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class LoggingTestExecutionListener implements TestExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingTestExecutionListener.class);
    
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        LOGGER.info("In beforeTestClass for Class: " + testContext.getTestClass().getName());
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        LOGGER.info("In prepareTestInstance for: " + testContext.getTestInstance());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        LOGGER.info("In beforeTestMethod for method: " + testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        LOGGER.info("In afterTestMethod for method: " + testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        LOGGER.info("In afterTestClass for Class: " + testContext.getTestClass().getName());
    }

}
