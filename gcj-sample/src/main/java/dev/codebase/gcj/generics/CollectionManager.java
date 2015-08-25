package dev.codebase.gcj.generics;

import java.util.Collection;

public interface CollectionManager {

    <E> Collection<E> manageCollection(Collection<E> c, CollectionId id, User user);
    
}
