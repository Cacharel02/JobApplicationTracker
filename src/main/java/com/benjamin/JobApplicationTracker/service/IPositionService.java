package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.entity.Position;

public interface IPositionService {

    void save(PositionRequest positionRequest, Long companyId, Long userId);
}
