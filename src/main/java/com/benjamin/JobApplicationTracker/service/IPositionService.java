package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.entity.Position;

import java.util.List;

public interface IPositionService {

    void save(PositionRequest positionRequest, Long companyId, Long userId);

    List<PositionDto> findAllByUserId(Long userId);

    PositionDto update(Long id, Long userId, PositionRequest positionRequest);
}
