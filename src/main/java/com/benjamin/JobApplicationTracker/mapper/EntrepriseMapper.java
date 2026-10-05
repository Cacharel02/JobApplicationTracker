package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.EntrepriseDto;
import com.benjamin.JobApplicationTracker.entity.Entreprise;
import org.springframework.stereotype.Component;

@Component
public class EntrepriseMapper {
    
    public static EntrepriseDto toEntrepriseDto(Entreprise entreprise, EntrepriseDto dto) {
        dto.setName(entreprise.getName());
        dto.setWebsite(entreprise.getWebsite());
        
        return dto;
    }
    
    public static Entreprise toEntreprise(EntrepriseDto dto, Entreprise entreprise) {
        entreprise.setName(dto.getName());
        entreprise.setWebsite(dto.getWebsite());
        
        return entreprise;
    }

}
