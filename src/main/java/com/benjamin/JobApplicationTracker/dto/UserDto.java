package com.benjamin.JobApplicationTracker.dto;

import com.benjamin.JobApplicationTracker.entity.Role;
import com.benjamin.JobApplicationTracker.entity.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    
    private String name;
    
    private String email;
    
    private String password;
    
    private Role role;
    
    private UserStatus userStatus;
}
