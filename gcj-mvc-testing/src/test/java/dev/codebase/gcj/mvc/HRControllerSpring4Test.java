package dev.codebase.gcj.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/application-context.xml")
@WebAppConfiguration
public class HRControllerSpring4Test {

    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mvc;
    
    @Before
    public void setup() {
        mvc = webAppContextSetup(wac).build();
    }
    
    @Test
    public void testGetEmployee() throws Exception {
        mvc.perform(get("/employeesSpring4/2").
                    accept(MediaType.parseMediaType("application/json;charset=UTF-8"))).
                    andExpect(status().isOk()).
                    andExpect(content().contentType("application/json;charset=UTF-8")).
                    andExpect(jsonPath("$.name").value("Alex Oxlade-Chamberlain")).
                    andExpect(jsonPath("$.salary").value(65000.00)).
                    andExpect(jsonPath("$.id").value(2));
    }
}
