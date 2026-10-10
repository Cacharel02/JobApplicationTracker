package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.service.IAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AdminController {

    private IAdminService adminService;

    @PostMapping(path = "/validate/{username}")
    public ResponseEntity<ResponseDto> validate(@PathVariable String username) {
        adminService.validate(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "User validated successfully"));
    }

    @PostMapping(path = "/ban/{username}")
    public ResponseEntity<ResponseDto> ban(@PathVariable String username) {
        adminService.ban(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "User banned successfully"));
    }
}
