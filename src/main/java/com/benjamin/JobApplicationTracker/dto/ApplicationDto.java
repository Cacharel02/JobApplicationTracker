package com.benjamin.JobApplicationTracker.dto;

import com.benjamin.JobApplicationTracker.entity.ApplicationState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private ApplicationState state;
    private String channel;
    private LocalDate date;
    private String title;
    private String description;
    private String location;
    private String companyName;
}
