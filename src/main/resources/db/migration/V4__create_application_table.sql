-- V4__create_application_table.sql
CREATE TABLE application (
    id BIGSERIAL PRIMARY KEY,
    state VARCHAR(50) NOT NULL,
    channel VARCHAR(255),
    date DATE,
    position_id BIGINT NOT NULL UNIQUE REFERENCES position(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
