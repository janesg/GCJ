package dev.codebase.gcj.async;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/Async.xml")
public class AccountJobTest {

/*
 * Can't directly create the AccountJob if I want it to be a registered Spring Bean
 *     
    private AccountJob accJob;
    
    @Before
    public void setup() {
        accJob = new AccountJob();
    }
*/
    
    @Autowired
    private ApplicationContext ctx;
    
    @Test
    public void testSendSMS() throws Exception {
        AccountJob accJob = (AccountJob) ctx.getBean(AccountJob.class);
        accJob.process();
    }
}
