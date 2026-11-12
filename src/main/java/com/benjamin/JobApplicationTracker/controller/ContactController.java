package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.ContactResponse;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import com.benjamin.JobApplicationTracker.service.IContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/contacts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAll(@AuthenticationPrincipal SecurityUser securityUser) {
        List<ContactResponse> contacts = contactService.findAll(securityUser.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contacts);
    }

}
