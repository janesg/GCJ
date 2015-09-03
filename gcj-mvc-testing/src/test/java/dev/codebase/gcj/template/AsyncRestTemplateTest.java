package dev.codebase.gcj.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

/**
 * Will only run without exceptions if Tomcat is running the gcj-mvn-testing webapp
 *  
 * @author Gary
 *
 */
public class AsyncRestTemplateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRestTemplateTest.class);
    
    @Test
    public void testExchange() {
        
        AsyncRestTemplate aTemp = new AsyncRestTemplate();
        
        String url = "http://localhost:9080/gcj-mvc-testing/employeesSpring4";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
        HttpEntity<String> requestEntity = new HttpEntity<>("params", headers);
        
        ListenableFuture<ResponseEntity<String>> future =
                aTemp.exchange(url, HttpMethod.GET, requestEntity, String.class);

        LOGGER.info("Async request made");
        
        try {
            LOGGER.info("Waiting for async response...");
            
            ResponseEntity<String> response = future.get();
            
            LOGGER.info("Async response body: " + response.getBody());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecute() {
        
        AsyncRestTemplate aTemp = new AsyncRestTemplate();
        
        String url = "http://localhost:9080/gcj-mvc-testing/employeesSpring4";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        
        AsyncRequestCallback requestCallback = new AsyncRequestCallback() {
            @Override
            public void doWithRequest(AsyncClientHttpRequest request) throws IOException {
                LOGGER.info(request.getURI().toString());                
            }};
        
        ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
            @Override
            public String extractData(ClientHttpResponse response) throws IOException {
                return response.getStatusText();
            }};

        Map<String, String> urlVariables = new HashMap<>();    
            
        ListenableFuture<String> future =
                aTemp.execute(url, HttpMethod.GET, requestCallback, responseExtractor, urlVariables);

        LOGGER.info("Async request made");
        
        try {
            LOGGER.info("Waiting for async response...");
            
            String response = future.get();
            
            LOGGER.info("Status: " + response);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
