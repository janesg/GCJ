package dev.codebase.gcj.generics;

public enum CollectionId {
    
    FRUIT_NAMES("Fruits", String.class),
    SUDUKO_NUMBERS("Suduko", Integer.class),
    WORK_COLLEAGUES("Colleagues", User.class);
    
    private String name;
    private Class<?> type;
    
    private CollectionId(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public Class<?> getType() {
        return type;
    }
}
