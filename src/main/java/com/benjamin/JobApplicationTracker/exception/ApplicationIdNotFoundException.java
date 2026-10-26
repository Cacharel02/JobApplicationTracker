package com.benjamin.JobApplicationTracker.exception;

public class ApplicationIdNotFoundException extends RuntimeException {

    public ApplicationIdNotFoundException(Long id) {
        super("Application not found with id: " + id);
    }
}
