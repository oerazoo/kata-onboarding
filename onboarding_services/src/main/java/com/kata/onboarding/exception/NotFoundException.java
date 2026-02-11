package com.kata.onboarding.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceType, String id) {
        super( resourceType + "with id: " + id + "was not found");
    }
}
