package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.ContactDto;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Contact;

public class ContactMapper {

    public static Contact contactDtoToContact(ContactDto contactDto, Company company) {
        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setRole(contactDto.getRole());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setEmail(contactDto.getEmail());
        contact.setCity(contactDto.getCity());
        contact.setCompany(company);
        return contact;
    }

}
