CREATE SCHEMA IF NOT EXISTS auth;

-- =========================
-- USER CONTEXT (identidad base)
-- =========================
CREATE TABLE  IF NOT EXISTS auth.user_context (
    keycloak_id VARCHAR(100) PRIMARY KEY
);

-- =========================
-- USER ASSIGNMENTS (MODELO FINAL)
-- =========================
CREATE TABLE IF NOT EXISTS auth.user_assignment(

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id VARCHAR(100),

    -- NUEVO: define el alcance del rol
    scope VARCHAR(20) NOT NULL,  
    -- GLOBAL / ORGANIZATION / PORT / SHIP

    entity_type VARCHAR(20),     
    -- SOLO si scope != GLOBAL

    entity_id UUID,              
    -- SOLO si scope != GLOBAL

    role_in_context VARCHAR(50) NOT NULL,

    start_date TIMESTAMP NOT NULL DEFAULT now(),
    end_date TIMESTAMP NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES auth.user_context(keycloak_id)
        ON DELETE CASCADE,

    -- Evita duplicados
    CONSTRAINT uq_user_assignment
        UNIQUE (user_id, scope, entity_type, entity_id, role_in_context),

    -- 🔥 Regla de integridad (muy importante)
    CONSTRAINT chk_scope_logic CHECK (
        (scope = 'GLOBAL' AND entity_id IS NULL AND entity_type IS NULL)
        OR
        (scope <> 'GLOBAL' AND entity_id IS NOT NULL AND entity_type IS NOT NULL)
    )
);