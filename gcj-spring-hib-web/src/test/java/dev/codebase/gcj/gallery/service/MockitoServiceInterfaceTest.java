package dev.codebase.gcj.gallery.service;

import dev.codebase.gcj.gallery.domain.Person;
import dev.codebase.gcj.gallery.exception.AuthenticationException;
import dev.codebase.gcj.gallery.service.ArtworkFacade;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoServiceInterfaceTest {

    private Person person;
    
    @Mock
    private ArtworkFacade artworkService;

    @Before
    public void initializeTest() {
        person = new Person();
        person.setUsername("username");
        person.setPassword("goodpassword");
    }

    @Test
    public void testAuthenticationSuccess() throws AuthenticationException {
        
        when(artworkService.authenticatePerson("username", "goodpassword")).thenReturn(person);
        
        artworkService.authenticatePerson("username", "goodpassword");
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticationFailure() throws AuthenticationException {
        
        when(artworkService.authenticatePerson("username", "badpassword")).thenThrow(AuthenticationException.class);
        
        artworkService.authenticatePerson("username", "badpassword");
    }

}
