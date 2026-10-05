package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.EntrepriseDto;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.service.IEntrepriseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/entreprises", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class EntrepriseController {

    private IEntrepriseService entrepriseService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
        entrepriseService.save(entrepriseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Entreprise created successfully"));
    }
}
