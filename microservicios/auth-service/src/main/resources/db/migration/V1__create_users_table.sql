-- =========================================
-- Auth Service - Users Table (PostgreSQL)
-- Flyway V2
-- =========================================

CREATE SCHEMA IF NOT EXISTS auth;

-- =========================
-- USERS TABLE
-- =========================
-- Identity base

CREATE TABLE auth.user_context (
    keycloak_id VARCHAR(100) PRIMARY KEY
);

-- Companies (multi-tenant)
CREATE TABLE auth.user_organization (
    keycloak_id VARCHAR(100) REFERENCES auth.user_context(keycloak_id) ON DELETE CASCADE,
    organization_id UUID NOT NULL,
    PRIMARY KEY (keycloak_id, organization_id)
);

-- Ports (multi-access)
CREATE TABLE auth.user_ports (
    keycloak_id VARCHAR(100) REFERENCES auth.user_context(keycloak_id) ON DELETE CASCADE,
    port_id UUID NOT NULL,
    PRIMARY KEY (keycloak_id, port_id)
);

-- Ships (multi-assignment)
CREATE TABLE auth.user_ships (
    keycloak_id VARCHAR(100) REFERENCES auth.user_context(keycloak_id) ON DELETE CASCADE,
    ship_id UUID NOT NULL,
    PRIMARY KEY (keycloak_id, ship_id)
);