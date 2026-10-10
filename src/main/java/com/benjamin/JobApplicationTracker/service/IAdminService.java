package com.benjamin.JobApplicationTracker.service;

public interface IAdminService {

    void validate(String username);

    void ban(String username);
}
