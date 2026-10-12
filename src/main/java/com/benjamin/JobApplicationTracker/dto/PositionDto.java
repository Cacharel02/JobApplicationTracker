package com.benjamin.JobApplicationTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private String title;
    private String description;
    private String location;
    private String company;
}
