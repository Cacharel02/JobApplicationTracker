package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.ApplicationRequest;
import com.benjamin.JobApplicationTracker.entity.Application;
import com.benjamin.JobApplicationTracker.entity.ApplicationState;
import com.benjamin.JobApplicationTracker.entity.Position;
import com.benjamin.JobApplicationTracker.exception.ApplicationAlreadyExistsException;
import com.benjamin.JobApplicationTracker.exception.PositionIdNotFoundException;
import com.benjamin.JobApplicationTracker.repository.ApplicationRepository;
import com.benjamin.JobApplicationTracker.repository.PositionRepository;
import com.benjamin.JobApplicationTracker.service.IApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final ApplicationRepository applicationRepository;
    private final PositionRepository positionRepository;

    @Override
    @Transactional
    public void save(ApplicationRequest applicationRequest, Long positionId, Long userId) {
        Position position = positionRepository.findByIdAndCompanyUserId(positionId, userId)
                .orElseThrow(() -> new PositionIdNotFoundException(positionId));

        if (applicationRepository.existsByPositionId(positionId)) {
            throw new ApplicationAlreadyExistsException(positionId);
        }

        Application application = new Application();
        application.setState(ApplicationState.TO_SEND);
        application.setChannel(applicationRequest.getChannel());
        application.setPosition(position);
        applicationRepository.save(application);
    }
}
