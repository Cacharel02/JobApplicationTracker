package com.benjamin.JobApplicationTracker.exception;

public class EntrepriseAlreadyExistsException extends RuntimeException {
    
    public EntrepriseAlreadyExistsException(String name) {
        super("An Entreprise with the name " + name + " already exists");
    }
}
