package dev.codebase.gcj.gallery.dao;

import dev.codebase.gcj.gallery.domain.Person;
import dev.codebase.gcj.gallery.exception.AuthenticationException;
import dev.codebase.gcj.gallery.exception.EntityNotFoundException;

public interface PersonDao extends GenericDao<Person> {

    Person getPersonByUsername(String username) throws EntityNotFoundException;
    
    Person authenticatePerson(String username, String password) throws AuthenticationException;
    
}
