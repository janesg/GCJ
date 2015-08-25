package dev.codebase.gcj.generics;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String name;
    private final Set<String> authorities;
    
    public User(String name, Set<String> authorities) {
        this.name = name;
        this.authorities = new HashSet<String>(authorities);
    }

    public String getName() {
        return name;
    }

    public Set<String> getAuthorities() {
        return new HashSet<String>(authorities);
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", authorities=" + authorities + "]";
    }
    
}
