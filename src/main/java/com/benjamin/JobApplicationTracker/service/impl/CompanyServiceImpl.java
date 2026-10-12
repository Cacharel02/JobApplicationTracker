package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.exception.CompanyIdNotFoundException;
import com.benjamin.JobApplicationTracker.exception.EntrepriseAlreadyExistsException;
import com.benjamin.JobApplicationTracker.exception.CompanyNameNotFoundException;
import com.benjamin.JobApplicationTracker.mapper.CompanyMapper;
import com.benjamin.JobApplicationTracker.repository.CompanyRepository;
import com.benjamin.JobApplicationTracker.service.ICompanyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public void save(CompanyDto companyDto, User user) {
        if(companyRepository.existsByNameAndUserId(companyDto.getName(), user.getId())) {
            throw new EntrepriseAlreadyExistsException(companyDto.getName());
        }
        Company company = CompanyMapper.toCompany(companyDto);
        company.setUser(user);
        companyRepository.save(company);
    }

    @Override
    public List<CompanyDto> findAll(User user) {
        return companyRepository.findByUserId(user.getId()).stream().map(CompanyMapper::toCompanyDto).toList();
    }

    @Override
    public CompanyDto findByName(String name, User user){
        return companyRepository.findByNameAndUserId(name, user.getId())
                .map(CompanyMapper::toCompanyDto).orElseThrow(() -> new CompanyNameNotFoundException(name));
    }

    @Override
    public CompanyDto findById(Long id, User user) {
        return companyRepository.findByIdAndUserId(id, user.getId())
                .map(CompanyMapper::toCompanyDto).orElseThrow(() -> new CompanyIdNotFoundException(id));
    }

    @Override
    @Transactional
    public CompanyDto modify(Long id, User user, CompanyDto companyDto) {
        Company company = companyRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new CompanyIdNotFoundException(id));
        if(!company.getName().equals(companyDto.getName()) && companyRepository.existsByNameAndUserId(companyDto.getName(), user.getId())) {
            throw new EntrepriseAlreadyExistsException(companyDto.getName());
        }
        company.setName(companyDto.getName());
        company.setWebsite(companyDto.getWebsite());
        companyRepository.save(company);
        return CompanyMapper.toCompanyDto(company);
    }

    @Override
    @Transactional
    public void delete(Long id, User user) {
        companyRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new CompanyIdNotFoundException(id));
        companyRepository.deleteByIdAndUserId(id, user.getId());
    }
}
