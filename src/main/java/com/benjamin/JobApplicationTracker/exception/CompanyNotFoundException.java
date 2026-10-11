package com.benjamin.JobApplicationTracker.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String name) {
        super("None company with the name " + name + " found");
    }
}
