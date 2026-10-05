package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.EntrepriseDto;
import com.benjamin.JobApplicationTracker.entity.Entreprise;
import com.benjamin.JobApplicationTracker.exception.EntrepriseAlreadyExistsException;
import com.benjamin.JobApplicationTracker.mapper.EntrepriseMapper;
import com.benjamin.JobApplicationTracker.repository.EntrepriseRepository;
import com.benjamin.JobApplicationTracker.service.IEntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntrepriseServiceImpl implements IEntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Override
    public void save(EntrepriseDto entrepriseDto) {
        if(entrepriseRepository.findByName(entrepriseDto.getName()).isPresent()) {
            throw new EntrepriseAlreadyExistsException(entrepriseDto.getName());
        }
        Entreprise entreprise = EntrepriseMapper.toEntreprise(entrepriseDto, new Entreprise());
        entrepriseRepository.save(entreprise);
    }
}
