package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.ApplicationDto;
import com.benjamin.JobApplicationTracker.dto.ApplicationRequest;
import com.benjamin.JobApplicationTracker.dto.ApplicationStateRequest;

public interface IApplicationService {

    void save(ApplicationRequest applicationRequest, Long positionId, Long userId);

    ApplicationDto changeState(Long id, Long userId, ApplicationStateRequest applicationStateRequest);
}
