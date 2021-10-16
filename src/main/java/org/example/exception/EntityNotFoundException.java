package org.example.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class type, Long id) {
        super("Could not find " + type.getName() + " with id " + id);
    }

    public EntityNotFoundException(Class type, String name) {
        super("Could not find " + type.getName() + " with name " + name);
    }

}
