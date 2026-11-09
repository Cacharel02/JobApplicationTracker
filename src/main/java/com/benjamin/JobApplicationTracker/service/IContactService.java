package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.ContactDto;

public interface IContactService {

    void save(ContactDto contactDto, Long companyId, Long userId);

}
