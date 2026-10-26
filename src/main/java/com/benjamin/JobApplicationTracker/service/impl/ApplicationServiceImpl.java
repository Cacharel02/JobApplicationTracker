package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.ApplicationDto;
import com.benjamin.JobApplicationTracker.dto.ApplicationRequest;
import com.benjamin.JobApplicationTracker.dto.ApplicationStateRequest;
import com.benjamin.JobApplicationTracker.entity.Application;
import com.benjamin.JobApplicationTracker.entity.ApplicationState;
import com.benjamin.JobApplicationTracker.entity.Position;
import com.benjamin.JobApplicationTracker.exception.ApplicationAlreadyExistsException;
import com.benjamin.JobApplicationTracker.exception.ApplicationIdNotFoundException;
import com.benjamin.JobApplicationTracker.exception.ApplicationStateRequiredException;
import com.benjamin.JobApplicationTracker.exception.InvalidApplicationStateTransitionException;
import com.benjamin.JobApplicationTracker.exception.PositionIdNotFoundException;
import com.benjamin.JobApplicationTracker.mapper.ApplicationMapper;
import com.benjamin.JobApplicationTracker.repository.ApplicationRepository;
import com.benjamin.JobApplicationTracker.repository.PositionRepository;
import com.benjamin.JobApplicationTracker.service.IApplicationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Override
    @Transactional
    public ApplicationDto changeState(Long id, Long userId, ApplicationStateRequest applicationStateRequest) {
        if (applicationStateRequest == null || applicationStateRequest.getState() == null) {
            throw new ApplicationStateRequiredException();
        }

        Application application = applicationRepository.findByIdAndOwner(id, userId)
                .orElseThrow(() -> new ApplicationIdNotFoundException(id));

        ApplicationState targetState = applicationStateRequest.getState();
        if (application.getState() == targetState) {
            return ApplicationMapper.toApplicationDto(application);
        }

        if (!application.getState().canTransitionTo(targetState)) {
            throw new InvalidApplicationStateTransitionException(application.getState(), targetState);
        }

        application.setState(targetState);
        if (targetState == ApplicationState.SENT && application.getDate() == null) {
            application.setDate(LocalDate.now());
        }
        applicationRepository.save(application);
        return ApplicationMapper.toApplicationDto(application);
    }
}
