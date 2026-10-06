package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.UserDto;
import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.exception.UserAlreadyExistsException;
import com.benjamin.JobApplicationTracker.mapper.UserMapper;
import com.benjamin.JobApplicationTracker.repository.UserRepository;
import com.benjamin.JobApplicationTracker.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Override
    public void save(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(userDto.getEmail());
        }
        User user = UserMapper.toUser(userDto, new User());
        userRepository.save(user);
    }
}
