package com.benjamin.JobApplicationTracker.controller;


import com.benjamin.JobApplicationTracker.dto.ApplicationRequest;
import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.IApplicationService;
import com.benjamin.JobApplicationTracker.service.IPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/positions", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PositionController {

    private final IPositionService positionService;
    private final IApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions(@AuthenticationPrincipal SecurityUser securityUser) {
        List<PositionDto> positionDtos = positionService.findAllByUserId(securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(positionDtos);
    }

    @PostMapping(path = "/{positionId}/applications")
    public ResponseEntity<ResponseDto> createApplication(@PathVariable Long positionId, @RequestBody ApplicationRequest applicationRequest, @AuthenticationPrincipal SecurityUser securityUser) {
        applicationService.save(applicationRequest, positionId, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Application created successfully"));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable Long id, @RequestBody PositionRequest positionRequest, @AuthenticationPrincipal SecurityUser securityUser) {
        PositionDto updatedPosition = positionService.update(id, securityUser.getUser().getId(), positionRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPosition);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> deletePosition(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        positionService.delete(id, securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Position deleted successfully"));
    }
}
