package com.benjamin.JobApplicationTracker.dto;

import com.benjamin.JobApplicationTracker.entity.ApplicationState;
import lombok.Data;

@Data
public class ApplicationStateRequest {

    private ApplicationState state;
}
