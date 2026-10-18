package com.benjamin.JobApplicationTracker.controller;


import com.benjamin.JobApplicationTracker.dto.PositionDto;
import com.benjamin.JobApplicationTracker.dto.PositionRequest;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.IPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/public/positions", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class PositionController {

    private final IPositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionDto>> getAllPositions(@AuthenticationPrincipal SecurityUser securityUser) {
        List<PositionDto> positionDtos = positionService.findAllByUserId(securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(positionDtos);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PositionDto> updatePosition(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser, @RequestBody PositionRequest positionRequest) {
        PositionDto updatedPosition = positionService.update(id, securityUser.getUser().getId(), positionRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPosition);
    }
}
