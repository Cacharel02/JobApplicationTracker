package com.benjamin.JobApplicationTracker.mapper;

import com.benjamin.JobApplicationTracker.dto.ContactRequest;
import com.benjamin.JobApplicationTracker.dto.ContactResponse;
import com.benjamin.JobApplicationTracker.entity.Company;
import com.benjamin.JobApplicationTracker.entity.Contact;

public class ContactMapper {

    public static Contact contactRequestToContact(ContactRequest contactRequest, Company company) {
        Contact contact = new Contact();
        contact.setName(contactRequest.getName());
        contact.setRole(contactRequest.getRole());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        contact.setEmail(contactRequest.getEmail());
        contact.setCity(contactRequest.getCity());
        contact.setCompany(company);
        return contact;
    }

    public static ContactResponse toContactResponse(Contact contact) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contact.getId());
        contactResponse.setName(contact.getName());
        contactResponse.setRole(contact.getRole());
        contactResponse.setPhoneNumber(contact.getPhoneNumber());
        contactResponse.setEmail(contact.getEmail());
        contactResponse.setCity(contact.getCity());
        contactResponse.setCompany(contact.getCompany().getName());
        return contactResponse;
    }

}
