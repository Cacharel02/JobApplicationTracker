package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public/company", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CompanyController {

    private ICompanyService companyService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCompany(@RequestBody CompanyDto companyDto, @AuthenticationPrincipal SecurityUser securityUser) {
        companyService.save(companyDto, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Company created successfully"));
    }
}
