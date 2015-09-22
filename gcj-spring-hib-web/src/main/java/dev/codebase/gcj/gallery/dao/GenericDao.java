package dev.codebase.gcj.gallery.dao;

import java.util.List;
import dev.codebase.gcj.gallery.domain.DomainObject;

public interface GenericDao<T extends DomainObject> {

    T get(Long id);

    List<T> getAll();

    void save(T object);

    void delete(T object);
    
}