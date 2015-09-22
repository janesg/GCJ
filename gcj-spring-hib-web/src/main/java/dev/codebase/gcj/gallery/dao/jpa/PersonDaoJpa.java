package dev.codebase.gcj.gallery.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dev.codebase.gcj.gallery.dao.PersonDao;
import dev.codebase.gcj.gallery.domain.Person;
import dev.codebase.gcj.gallery.exception.AuthenticationException;
import dev.codebase.gcj.gallery.exception.EntityNotFoundException;

@Repository("personDao")
public class PersonDaoJpa extends GenericDaoJpa<Person>implements PersonDao {

    public PersonDaoJpa() {
        super(Person.class);
    }

    @Override
    public Person authenticatePerson(String username, String password)
            throws DataAccessException, AuthenticationException {
        
        List<Person> results = null;
        
        String jpql = "from Person as p where p.username = :username and p.password = :password";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        results = query.getResultList();
        
        if (results == null || results.size() <= 0) {
            throw new AuthenticationException("No users found");
        } else {
            return results.get(0);
        }
    }

    @Override
    public Person getPersonByUsername(String username) 
            throws DataAccessException, EntityNotFoundException {
        
        List<Person> results = null;
        
        String jpql = "from Person as p where p.username = :username";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);
        
        results = query.getResultList();
        
        if (results == null || results.size() <= 0) {
            throw new EntityNotFoundException(username + " not found");
        } else {
            return results.get(0);
        }
    }
}
