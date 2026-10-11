package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    
    public static CompanyDto toCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(company.getName());
        companyDto.setWebsite(company.getWebsite());
        
        return companyDto;
    }
    
    public static Company toCompany(CompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setWebsite(dto.getWebsite());
        
        return company;
    }

}
