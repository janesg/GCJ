package dev.codebase.gcj.async;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class SMSTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMSTask.class);
    
    @Async
    public Future<Boolean> send(String... numbers) {
        
        LOGGER.info("Selecting SMS format...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.info("Sleep interrupted: " + e.getMessage());
            return new AsyncResult<>(false);
        }
        
        LOGGER.info("SMS send task is complete");
        return new AsyncResult<>(true);
        
    }
}
