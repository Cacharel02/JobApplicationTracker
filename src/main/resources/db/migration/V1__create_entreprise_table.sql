-- V1__create_entreprise_table.sql
CREATE TABLE entreprise (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    website VARCHAR(255)
);