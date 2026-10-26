package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.ApplicationDto;
import com.benjamin.JobApplicationTracker.dto.ApplicationStateRequest;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.IApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/applications", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ApplicationController {

    private final IApplicationService applicationService;

    @PatchMapping(path = "/{id}/state")
    public ResponseEntity<ApplicationDto> changeState(@PathVariable Long id, @RequestBody ApplicationStateRequest applicationStateRequest, @AuthenticationPrincipal SecurityUser securityUser) {
        ApplicationDto applicationDto = applicationService.changeState(id, securityUser.getUser().getId(), applicationStateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationDto);
    }
}
