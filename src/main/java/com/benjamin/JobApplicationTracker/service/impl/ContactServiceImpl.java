package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.ContactDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Contact;
import com.benjamin.JobApplicationTracker.exception.CompanyIdNotFoundException;
import com.benjamin.JobApplicationTracker.mapper.ContactMapper;
import com.benjamin.JobApplicationTracker.repository.CompanyRepository;
import com.benjamin.JobApplicationTracker.repository.ContactRepository;
import com.benjamin.JobApplicationTracker.service.IContactService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void save(ContactDto contactDto, Long companyId, Long userId) {
        Company company = companyRepository.findByIdAndUserId(companyId, userId)
                .orElseThrow(() -> new CompanyIdNotFoundException(companyId));
        Contact contact = ContactMapper.contactDtoToContact(contactDto, company);
        contactRepository.save(contact);
    }
}
