package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.UserDto;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "User created successfully"));
    }
}
