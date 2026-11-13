package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.dto.PositionResponse;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class PositionMapper {

    public static PositionResponse toPositionResponse(Position position){
            PositionResponse positionResponse = new PositionResponse();
            positionResponse.setId(position.getId());
            positionResponse.setTitle(position.getTitle());
            positionResponse.setDescription(position.getDescription());
            positionResponse.setLocation(position.getLocation());
            positionResponse.setCompany(position.getCompany().getName());
            return positionResponse;
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
