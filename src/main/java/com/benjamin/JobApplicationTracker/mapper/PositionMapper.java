package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

    public static PositionDto toPositionDto(Position position){
            PositionDto positionDto = new PositionDto();
            positionDto.setTitle(position.getTitle());
            positionDto.setDescription(position.getDescription());
            positionDto.setLocation(position.getLocation());
            positionDto.setCompany(position.getCompany().getName());
            return positionDto;
    }

    public static Position positionRequestToPosition(PositionRequest positionRequest, Company company){
        Position position = new Position();
        position.setTitle(positionRequest.getTitle());
        position.setDescription(positionRequest.getDescription());
        position.setLocation(positionRequest.getLocation());
        position.setCompany(company);
        return position;
    }

}
