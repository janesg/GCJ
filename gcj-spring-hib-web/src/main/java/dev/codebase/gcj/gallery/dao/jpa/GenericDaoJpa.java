package dev.codebase.gcj.gallery.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import dev.codebase.gcj.gallery.dao.GenericDao;
import dev.codebase.gcj.gallery.domain.DomainObject;


public class GenericDaoJpa<T extends DomainObject> implements GenericDao<T> {

    private Class<T> type;
    
    protected EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public GenericDaoJpa(Class<T> type) {
        super();
        this.type = type;
    } 
    
    @Transactional(readOnly=true)
    @Override
    public T get(Long id) {
        if (id == null) {
            return null;
        } else {
            return entityManager.find(type, id);
        }
    }

    @Transactional(readOnly=true)
    @Override
    public List<T> getAll() {
        String jpql = "select obj from " + type.getName() + " obj";        
        return entityManager.createQuery(jpql).getResultList(); 
    }

    @Override
    public void save(T object) {
        entityManager.persist(object);        
    }

    @Override
    public void delete(T object) {
        entityManager.remove(object);        
    }

}
