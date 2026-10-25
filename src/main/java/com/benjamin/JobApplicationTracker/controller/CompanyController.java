package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.CompanyDto;
import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.ICompanyService;
import com.benjamin.JobApplicationTracker.service.IPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/companies", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CompanyController {

    private ICompanyService companyService;
    private IPositionService positionService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCompany(@RequestBody CompanyDto companyDto, @AuthenticationPrincipal SecurityUser securityUser) {
        companyService.save(companyDto, securityUser.getUser());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Company created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll(@AuthenticationPrincipal SecurityUser securityUser) {
        List<CompanyDto> companies = companyService.findAll(securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companies);
    }

    @GetMapping(params = "name")
    public ResponseEntity<CompanyDto> getByName(@RequestParam String name, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.findByName(name, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.findById(id, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyDto companyDto, @AuthenticationPrincipal SecurityUser securityUser) {
        CompanyDto company = companyService.modify(id, securityUser.getUser().getId(), companyDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(company);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        companyService.delete(id, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Company deleted successfully"));
    }

    @PostMapping(path = "/{companyId}/positions")
    public ResponseEntity<ResponseDto> createPosition(@PathVariable Long companyId, @RequestBody PositionRequest positionRequest, @AuthenticationPrincipal SecurityUser securityUser){
        positionService.save(positionRequest, companyId, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Position created successfully"));
    }

    @GetMapping(path = "/{companyId}/positions")
    public ResponseEntity<List<PositionDto>> getPositions(@PathVariable Long companyId, @AuthenticationPrincipal SecurityUser securityUser){
        List<PositionDto> positions = companyService.findAllPositionsByCompany(companyId, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(positions);
    }

}
