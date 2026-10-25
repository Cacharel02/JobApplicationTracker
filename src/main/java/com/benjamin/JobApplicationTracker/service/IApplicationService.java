package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.ApplicationRequest;

public interface IApplicationService {

    void save(ApplicationRequest applicationRequest, Long positionId, Long userId);
}
