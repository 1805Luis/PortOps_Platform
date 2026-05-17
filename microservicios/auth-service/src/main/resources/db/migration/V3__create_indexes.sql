-- =========================================
-- Auth Service - Indexes Optimization
-- Flyway V3
-- =========================================

-- =========================
-- LOGIN / AUTHORIZATION
-- user -> assignments activos
-- =========================
CREATE INDEX IF NOT EXISTS idx_user_assignment_user_status
ON auth.user_assignment(user_id, status);

-- =========================
-- búsquedas por entidad
-- PORT / SHIP / ORGANIZATION
-- =========================
CREATE INDEX IF NOT EXISTS idx_user_assignment_entity
ON auth.user_assignment(entity_type, entity_id, status);

-- =========================
-- filtro por scope
-- GLOBAL / PORT / SHIP / ORGANIZATION
-- =========================
CREATE INDEX IF NOT EXISTS idx_user_assignment_scope
ON auth.user_assignment(scope);

-- =========================
-- búsqueda rápida por rol
-- (útil para autorización)
-- =========================
CREATE INDEX IF NOT EXISTS idx_user_assignment_role
ON auth.user_assignment(role_in_context);