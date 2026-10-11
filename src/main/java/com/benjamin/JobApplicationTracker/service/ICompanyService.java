package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.User;

public interface ICompanyService {

    void save(CompanyDto companyDto, User user);
}
