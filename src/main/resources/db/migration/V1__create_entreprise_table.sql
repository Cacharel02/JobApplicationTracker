-- V1__create_entreprise_table.sql
CREATE TABLE entreprise (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    site_web VARCHAR(255)
);