package com.benjamin.JobApplicationTracker.service.impl;

import com.benjamin.JobApplicationTracker.dto.ContactRequest;
import com.benjamin.JobApplicationTracker.dto.ContactResponse;
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

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void save(ContactRequest contactRequest, Long companyId, Long userId) {
        Company company = companyRepository.findByIdAndUserId(companyId, userId)
                .orElseThrow(() -> new CompanyIdNotFoundException(companyId));
        Contact contact = ContactMapper.contactRequestToContact(contactRequest, company);
        contactRepository.save(contact);
    }

    @Override
    public List<ContactResponse> findAll(Long userId) {
        return contactRepository.findAllByOwner(userId).stream()
                .map(ContactMapper::toContactResponse)
                .toList();
    }

}
