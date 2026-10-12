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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/public/companies", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CompanyController {

    private ICompanyService companyService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCompany(@RequestBody CompanyDto companyDto, @AuthenticationPrincipal SecurityUser securityUser) {
        companyService.save(companyDto, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Company created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll(@AuthenticationPrincipal SecurityUser securityUser) {
        List<CompanyDto> companies = companyService.findAll(securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companies);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<CompanyDto> getByName(@PathVariable String name, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.findByName(name, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.findById(id, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto companyDto, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.modify(id, securityUser.getUser(), companyDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        companyService.delete(id, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Company deleted successfully"));
    }


}
