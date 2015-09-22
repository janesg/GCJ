package dev.codebase.gcj.gallery.util;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import dev.codebase.gcj.gallery.domain.Person;
import dev.codebase.gcj.gallery.service.ArtworkFacade;
import dev.codebase.gcj.gallery.exception.EntityNotFoundException;

public class AccountCreator implements InitializingBean {

    private List<String> users;
    private ArtworkFacade artworkFacade;

    @Override
    public void afterPropertiesSet() {
        for (String user : users) {
            String[] userInfo = user.split(",");
            
            if (userInfo.length < 2)
                continue;
            
            String username = userInfo[0];
            
            try {
                artworkFacade.getPersonByUsername(username);
            } catch (EntityNotFoundException e) {
                createNewPerson(userInfo);
            }
        }
    }

    private boolean createNewPerson(String[] personInfo) {
        String username = personInfo[0];
        String password = personInfo[1];
        String firstname = personInfo[2];
        String lastname = personInfo[3];
        Person.RoleLevel level = Person.RoleLevel.getLevel(personInfo[4]);
        Person newPerson = new Person(firstname, lastname, username, password, level.getLevel());
        
        artworkFacade.savePerson(newPerson);
        
        return true;
    }

    public List<String> getUsers() {
        return users;
    }

    @Autowired
    public void setUsers(@Qualifier("users") List<String> users) {
        this.users = users;
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    @Autowired
    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }

}
