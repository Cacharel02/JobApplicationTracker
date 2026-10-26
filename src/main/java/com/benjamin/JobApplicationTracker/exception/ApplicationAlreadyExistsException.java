package com.benjamin.JobApplicationTracker.exception;

public class ApplicationAlreadyExistsException extends RuntimeException {

    public ApplicationAlreadyExistsException(Long positionId) {
        super("An application already exists for position id: " + positionId);
    }
}
