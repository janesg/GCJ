package dev.codebase.gcj.gallery.service;

import dev.codebase.gcj.gallery.dao.PersonDao;
import dev.codebase.gcj.gallery.domain.Person;
import dev.codebase.gcj.gallery.exception.AuthenticationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoServiceInterfaceTest {

    private Person person;
    
    @InjectMocks
    private ArtworkFacadeImpl classUnderTest;

    @Mock
    private PersonDao personDao;

    @Before
    public void initializeTest() {
        person = new Person();
        person.setFirstName("Bob");
        person.setLastName("Ajob");
        person.setUsername("username");
        person.setPassword("goodpassword");
    }

    @Test
    public void testAuthenticationSuccess() throws AuthenticationException {
        
        when(personDao.authenticatePerson("username", "goodpassword")).thenReturn(person);
        
        Person p = classUnderTest.authenticatePerson("username", "goodpassword");
        
        assertTrue(p.getFirstName().equals(person.getFirstName()));
        assertTrue(p.getLastName().equals(person.getLastName()));
        assertTrue(p.getUsername().equals(person.getUsername()));
        
        // Using Mockito to test the behaviour that occurred calls to the Mock
        verify(personDao).authenticatePerson("username", "goodpassword");

    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticationFailure() throws AuthenticationException {
        
        when(personDao.authenticatePerson("username", "badpassword")).thenThrow(AuthenticationException.class);
        
        classUnderTest.authenticatePerson("username", "badpassword");
    }

}
