package com.benjamin.JobApplicationTracker.exception;

public class ContactIdNotFoundException extends RuntimeException {

    public ContactIdNotFoundException(Long id) {
        super("Contact not found with id: " + id);
    }
}
