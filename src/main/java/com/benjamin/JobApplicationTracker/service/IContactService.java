package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.ContactRequest;
import com.benjamin.JobApplicationTracker.dto.ContactResponse;

import java.util.List;

public interface IContactService {

    void save(ContactRequest contactRequest, Long companyId, Long userId);

    List<ContactResponse> findAll(Long userId);

}
