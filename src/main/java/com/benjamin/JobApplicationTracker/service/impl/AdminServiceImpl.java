package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.entity.UserStatus;
import com.benjamin.JobApplicationTracker.repository.UserRepository;
import com.benjamin.JobApplicationTracker.service.IAdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private UserRepository userRepository;

    @Override
    @Transactional
    public void validate(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        user.setUserStatus(UserStatus.APPROVED);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void ban(String username) {
        User user = userRepository.findByEmail(username).orElseThrow();
        user.setUserStatus(UserStatus.BANNED);
        userRepository.save(user);
    }
}
