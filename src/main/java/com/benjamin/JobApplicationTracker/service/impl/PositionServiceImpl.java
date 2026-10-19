package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Position;
import com.benjamin.JobApplicationTracker.exception.CompanyIdNotFoundException;
import com.benjamin.JobApplicationTracker.exception.PositionIdNotFoundException;
import com.benjamin.JobApplicationTracker.mapper.PositionMapper;
import com.benjamin.JobApplicationTracker.repository.CompanyRepository;
import com.benjamin.JobApplicationTracker.repository.PositionRepository;
import com.benjamin.JobApplicationTracker.service.IPositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements IPositionService {

    private final PositionRepository positionRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void save(PositionRequest positionRequest, Long companyId, Long userId) {
        Company company = companyRepository.findByIdAndUserId(companyId, userId)
                .orElseThrow(() -> new CompanyIdNotFoundException(companyId));
        Position position = PositionMapper.positionRequestToPosition(positionRequest, company);
        positionRepository.save(position);
    }

    @Override
    public List<PositionDto> findAllByUserId(Long userId) {
        List<Position> positions = positionRepository.findAllByOwner(userId);
        return positions.stream().map(PositionMapper::toPositionDto).toList();
    }

    @Override
    public PositionDto update(Long id, Long userId, PositionRequest positionRequest) {
        Position position = positionRepository.findByIdAndCompanyUserId(id, userId)
                .orElseThrow(() -> new PositionIdNotFoundException(id));
        position.setTitle(positionRequest.getTitle());
        position.setDescription(positionRequest.getDescription());
        position.setLocation(positionRequest.getLocation());
        positionRepository.save(position);
        return PositionMapper.toPositionDto(position);
    }
}
