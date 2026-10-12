package com.benjamin.JobApplicationTracker.exception;

public class CompanyNameNotFoundException extends RuntimeException{
    public CompanyNameNotFoundException(String name) {
        super("None company with the name " + name + " found");
    }
}
