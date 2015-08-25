package dev.codebase.gcj.generics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/CollectionManagerTest.xml")
public class CollectionManagerTest {

    @Autowired
    ApplicationContext context;
    
    @Autowired
    private CollectionManager cm;
    
    @Before
    public void setup() {        
    }
    
    @Test
    public void testFruits() {
        Collection<String> cBefore = new ArrayList<String>();
        cBefore.add("Banana");
        cBefore.add("Pear");
        
        Collection<User> cBefore2 = new ArrayList<User>();
        
        Collection<Integer> cBefore3 = new ArrayList<Integer>();
        
        Set<String> authorities = new HashSet<String>();
        authorities.add("PERM_GREEN");
        authorities.add("PERM_ODD");
        
        User user = new User("Gary", authorities);
                
        Collection<String> cAfter = cm.manageCollection(cBefore, CollectionId.FRUIT_NAMES, user);
        System.out.println("cAfter: " + cAfter);
        
        Collection<User> cAfter2 = cm.manageCollection(cBefore2, CollectionId.WORK_COLLEAGUES, user);
        System.out.println("cAfter2: " + cAfter2);
        
        Collection<Integer> cAfter3 = cm.manageCollection(cBefore3, CollectionId.SUDUKO_NUMBERS, user);
        System.out.println("cAfter3: " + cAfter3);
        
        System.out.println("String bean: " + context.getBean("fruitNameCollectionType"));
        
        assertEquals(new ArrayList<String>(Arrays.asList("Apple", "Pear", "Lime")), cAfter);
    }

}
