package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.dto.PositionResponse;
import com.benjamin.JobApplicationTracker.entity.Position;

import java.util.List;

public interface IPositionService {

    void save(PositionRequest positionRequest, Long companyId, Long userId);

    List<PositionResponse> findAllByUserId(Long userId);

    PositionResponse update(Long id, Long userId, PositionRequest positionRequest);

    void delete(Long id, Long userId);
}
