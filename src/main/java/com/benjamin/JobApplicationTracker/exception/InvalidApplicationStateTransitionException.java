package com.benjamin.JobApplicationTracker.exception;

import com.benjamin.JobApplicationTracker.entity.ApplicationState;

public class InvalidApplicationStateTransitionException extends RuntimeException {

    public InvalidApplicationStateTransitionException(ApplicationState currentState, ApplicationState targetState) {
        super("Cannot transition application from " + currentState + " to " + targetState);
    }
}
