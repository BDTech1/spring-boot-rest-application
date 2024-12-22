package com.lsbu.coursemanagement;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // I can also overload the constructor to include other details such as the resource name and id
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(String.format("%s with id %d not found", resourceName, resourceId));
    }
}