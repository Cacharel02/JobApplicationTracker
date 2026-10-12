package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.User;

import java.util.List;

public interface ICompanyService {

    void save(CompanyDto companyDto, User user);

    List<CompanyDto> findAll(User user);

    CompanyDto findByName(String name, User user);

    CompanyDto findById(Long id, User user);

    CompanyDto modify(Long id, User user, CompanyDto companyDto);

    void delete(Long id, User user);
}
