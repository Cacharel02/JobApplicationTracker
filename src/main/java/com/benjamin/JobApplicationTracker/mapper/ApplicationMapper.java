package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.ApplicationDto;
import com.benjamin.JobApplicationTracker.entity.Application;
import com.benjamin.JobApplicationTracker.entity.Position;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public static ApplicationDto toApplicationDto(Application application) {
        Position position = application.getPosition();

        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setState(application.getState());
        applicationDto.setChannel(application.getChannel());
        applicationDto.setDate(application.getDate());
        applicationDto.setTitle(position.getTitle());
        applicationDto.setDescription(position.getDescription());
        applicationDto.setLocation(position.getLocation());
        applicationDto.setCompanyName(position.getCompany().getName());
        return applicationDto;
    }
}
