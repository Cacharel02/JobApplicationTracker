package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.exception.EntrepriseAlreadyExistsException;
import com.benjamin.JobApplicationTracker.mapper.CompanyMapper;
import com.benjamin.JobApplicationTracker.repository.CompanyRepository;
import com.benjamin.JobApplicationTracker.service.IEntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements IEntrepriseService {

    private CompanyRepository entrepriseRepository;

    @Override
    public void save(CompanyDto entrepriseDto) {
        if(entrepriseRepository.findByName(entrepriseDto.getName()).isPresent()) {
            throw new EntrepriseAlreadyExistsException(entrepriseDto.getName());
        }
        Company entreprise = CompanyMapper.toCompany(entrepriseDto, new Company());
        entrepriseRepository.save(entreprise);
    }
}
