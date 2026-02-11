package com.kata.onboarding.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String resourceType, String id) {
        super( resourceType + "with id: " + id + " already exists");
    }
}
