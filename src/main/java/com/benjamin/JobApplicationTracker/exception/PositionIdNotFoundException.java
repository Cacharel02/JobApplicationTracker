package com.benjamin.JobApplicationTracker.exception;

public class PositionIdNotFoundException extends RuntimeException{
    public PositionIdNotFoundException(Long id) {
        super("Position not found with id: " + id);
    }
}
