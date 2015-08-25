package dev.codebase.gcj.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CollectionManagerImpl implements CollectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionManagerImpl.class);
            
    @Resource(name="collectionAuthorityMap")
    private Map<CollectionId, Map<String, Collection<?>>> collectionAuthorityMap;
    
    @Override
    public <E> Collection<E> manageCollection(Collection<E> c, CollectionId id, User user) {
        
        LOGGER.info("Collection Type: " + CollectionId.FRUIT_NAMES.getType().getName());
        LOGGER.info("Collection Type: " + CollectionId.SUDUKO_NUMBERS.getType().getName());
        LOGGER.info("Input Collection: " + c);
        LOGGER.info("Collection Map: " + collectionAuthorityMap);

        Map<String, Collection<?>> authorityValueMap = collectionAuthorityMap.get(id);
        
        LOGGER.info("Authority Map for " + id.getName() + ": " + authorityValueMap);
        
        Collection<E> retCol = new ArrayList<E>();
        
        Class<?> clazz = id.getType();
        LOGGER.info("Clazz: " + clazz.getName());
        
        for (String userAuthority : user.getAuthorities()) {
            Collection<?> permittedValues = authorityValueMap.get(userAuthority);
            
            if (permittedValues != null) {
                for (Object obj : permittedValues) {
                    LOGGER.info("Object is of class: " + obj.getClass().getName());
                    
                    if (obj.getClass() == clazz) {
/*                        
                    }
                    if ((obj.getClass() == clazz) &&
                        (obj.getClass() == E.class)) {
*/
                        // Due to Type Erasure we can't check whether obj is of same type
                        // as the returned collection. Is it possible ???
                        retCol.add((E) obj);
                    } else {
                        LOGGER.warn("Incompatible classes: Collection [" + id.getName() + 
                                    "] is a collection of <" + clazz.getName() + 
                                    "> but object is of class <" + obj.getClass().getName() + ">");                        
                    }
                }
            }            
        }
   
        return retCol;
    }

}
