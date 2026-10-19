package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.exception.CompanyIdNotFoundException;
import com.benjamin.JobApplicationTracker.exception.EntrepriseAlreadyExistsException;
import com.benjamin.JobApplicationTracker.exception.CompanyNameNotFoundException;
import com.benjamin.JobApplicationTracker.mapper.CompanyMapper;
import com.benjamin.JobApplicationTracker.mapper.PositionMapper;
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
    public List<CompanyDto> findAll(Long userId) {
        return companyRepository.findByUserId(userId).stream().map(CompanyMapper::toCompanyDto).toList();
    }

    @Override
    public CompanyDto findByName(String name, Long userId){
        return companyRepository.findByNameAndUserId(name, userId)
                .map(CompanyMapper::toCompanyDto).orElseThrow(() -> new CompanyNameNotFoundException(name));
    }

    @Override
    public CompanyDto findById(Long id, Long userId) {
        return CompanyMapper.toCompanyDto(findCompanyByIdAndUserId(id, userId));
    }

    @Override
    @Transactional
    public CompanyDto modify(Long id, Long userId, CompanyDto companyDto) {
        Company company = findCompanyByIdAndUserId(id, userId);
        if(!company.getName().equals(companyDto.getName()) && companyRepository.existsByNameAndUserId(companyDto.getName(), userId)) {
            throw new EntrepriseAlreadyExistsException(companyDto.getName());
        }
        company.setName(companyDto.getName());
        company.setWebsite(companyDto.getWebsite());
        companyRepository.save(company);
        return CompanyMapper.toCompanyDto(company);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        Company company = findCompanyByIdAndUserId(id, userId);
        companyRepository.delete(company);
    }

    @Override
    public List<PositionDto> findAllPositionsByCompany(Long companyId, Long userId) {
        return findCompanyByIdAndUserId(companyId, userId).getPositions()
                .stream()
                .map(PositionMapper::toPositionDto)
                .toList();
    }

    private Company findCompanyByIdAndUserId(Long companyId, Long userId) {
        return companyRepository.findByIdAndUserId(companyId, userId)
                .orElseThrow(() -> new CompanyIdNotFoundException(companyId));
    }


}
