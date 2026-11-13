package com.benjamin.JobApplicationTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {

    private Long id;
    private String name;
    private String role;
    private String phoneNumber;
    private String email;
    private String city;
    private String company;

}
