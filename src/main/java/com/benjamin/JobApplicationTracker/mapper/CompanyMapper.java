package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    
    public static CompanyDto toCompanyDto(Company entreprise, CompanyDto dto) {
        dto.setName(entreprise.getName());
        dto.setWebsite(entreprise.getWebsite());
        
        return dto;
    }
    
    public static Company toCompany(CompanyDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setWebsite(dto.getWebsite());
        
        return company;
    }

}
