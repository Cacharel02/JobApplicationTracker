package com.benjamin.JobApplicationTracker.entity;

public enum ApplicationState {
    TO_SEND,
    SENT,
    INTERVIEW,
    REJECTED,
    ACCEPTED,
    ARCHIVED;

    public boolean canTransitionTo(ApplicationState targetState) {
        return switch (this) {
            case TO_SEND -> targetState == SENT || targetState == ARCHIVED;
            case SENT -> targetState == INTERVIEW || targetState == REJECTED || targetState == ARCHIVED;
            case INTERVIEW -> targetState == ACCEPTED || targetState == REJECTED || targetState == ARCHIVED;
            case ACCEPTED, REJECTED -> targetState == ARCHIVED;
            case ARCHIVED -> false;
        };
    }
}
