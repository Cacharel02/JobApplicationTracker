-- V3__create_position_table.sql
CREATE TABLE position (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    company_id BIGINT NOT NULL REFERENCES company(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
