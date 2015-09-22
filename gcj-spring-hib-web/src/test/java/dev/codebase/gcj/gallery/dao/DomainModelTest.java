package dev.codebase.gcj.gallery.dao;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.codebase.gcj.gallery.dao.PersonDao;
import dev.codebase.gcj.gallery.domain.Person;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-context.xml" })
@Rollback(value = true)
@Transactional(transactionManager = "txnManager")
public class DomainModelTest {

    protected static Logger LOGGER = LoggerFactory.getLogger(DomainModelTest.class);

    PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Test
    public void testPerson() {
        Person person = new Person();
        person.setFirstName("Paul");
        person.setLastName("Fisher");
        person.setUsername("pfisher");
        person.setPassword("password");
        person.setRoleLevel(Person.RoleLevel.ADMIN.getLevel());
        person.setVersion(1);
        personDao.save(person);

        final List<Person> people = personDao.getAll();
        assertEquals(1, people.size());

        try {
            assertEquals(people.get(0), personDao.authenticatePerson("pfisher", "password"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            personDao.authenticatePerson("pfisher", "badpassword");
            fail("bad password should fail");
        } catch (Exception e) {
             // success, we expect an authentication exception to be propagated
            LOGGER.info("authentication exception thrown as expected, due to passing of invalid username and password");
        }
    }

}
