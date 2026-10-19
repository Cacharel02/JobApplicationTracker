package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.entity.User;

import java.util.List;

public interface ICompanyService {

    void save(CompanyDto companyDto, User user);

    List<CompanyDto> findAll(Long userId);

    CompanyDto findByName(String name, Long userId);

    CompanyDto findById(Long id, Long userId);

    CompanyDto modify(Long id, Long userId, CompanyDto companyDto);

    void delete(Long id, Long userId);

    List<PositionDto> findAllPositionsByCompany(Long companyId, Long userId);
}
