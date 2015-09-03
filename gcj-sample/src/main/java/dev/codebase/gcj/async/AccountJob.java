package dev.codebase.gcj.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountJob.class);
    
    @Autowired
    private SMSTask smsTask;
    
    public void process() throws InterruptedException, ExecutionException {
        
        LOGGER.info("Begin finding defaulters...");
        
        Future<Boolean> asyncResult = smsTask.send("1", "2", "3");
        
        LOGGER.info("Defaulter job complete. SMS will be sent to all defaulters");
        
        Boolean result = asyncResult.get();
        
        LOGGER.info("Was SMS sent ? : " + result);
    }
    
}
