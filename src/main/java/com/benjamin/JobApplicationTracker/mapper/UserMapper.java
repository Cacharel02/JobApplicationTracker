package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.UserDto;
import com.benjamin.JobApplicationTracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public static UserDto toUserDto(User user, UserDto dto) {
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setUserStatus(user.getUserStatus());
        
        return dto;
    }
    
    public static User toUser(UserDto dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setUserStatus(dto.getUserStatus());
        
        return user;
    }

}
