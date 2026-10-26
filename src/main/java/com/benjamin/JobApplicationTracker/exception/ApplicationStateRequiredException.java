package com.benjamin.JobApplicationTracker.exception;

public class ApplicationStateRequiredException extends RuntimeException {

    public ApplicationStateRequiredException() {
        super("Application state is required");
    }
}
