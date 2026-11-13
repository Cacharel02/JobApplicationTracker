-- V5__create_contact_table.sql
CREATE TABLE contact (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(255),
    phone_number VARCHAR(50),
    email VARCHAR(255),
    company_id BIGINT NOT NULL REFERENCES company(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
