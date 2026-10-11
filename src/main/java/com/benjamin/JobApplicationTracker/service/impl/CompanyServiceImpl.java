package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.exception.EntrepriseAlreadyExistsException;
import com.benjamin.JobApplicationTracker.mapper.CompanyMapper;
import com.benjamin.JobApplicationTracker.repository.CompanyRepository;
import com.benjamin.JobApplicationTracker.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private CompanyRepository companyRepository;

    @Override
    public void save(CompanyDto companyDto, User user) {
        if(companyRepository.existsByNameAndUserId(companyDto.getName(), user.getId())) {
            throw new EntrepriseAlreadyExistsException(companyDto.getName());
        }
        Company company = CompanyMapper.toCompany(companyDto);
        company.setUser(user);
        companyRepository.save(company);
    }
}
