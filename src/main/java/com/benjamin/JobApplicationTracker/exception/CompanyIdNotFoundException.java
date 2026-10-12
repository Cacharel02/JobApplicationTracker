package com.benjamin.JobApplicationTracker.exception;

public class CompanyIdNotFoundException extends RuntimeException{
    public CompanyIdNotFoundException(Long id) {
        super("None company with the id " + id + " found");
    }
}
