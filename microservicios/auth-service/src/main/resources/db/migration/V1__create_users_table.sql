CREATE SCHEMA IF NOT EXISTS auth;

-- =========================
-- USER CONTEXT (identidad base)
-- =========================
CREATE TABLE auth.user_context (
    keycloak_id VARCHAR(100) PRIMARY KEY
);

-- =========================
-- USER ASSIGNMENTS (NUEVO MODELO)
-- =========================
CREATE TABLE auth.user_assignment (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id VARCHAR(100),
    entity_type VARCHAR(20) NOT NULL,   -- SHIP / PORT / ORGANIZATION
    entity_id UUID NOT NULL,

    role_in_context VARCHAR(50) NOT NULL,

    start_date TIMESTAMP NOT NULL DEFAULT now(),
    end_date TIMESTAMP NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES auth.user_context(keycloak_id)
        ON DELETE CASCADE
);