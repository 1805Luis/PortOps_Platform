-- =========================================
-- Auth Service - Indexes Optimization
-- Flyway V3
-- =========================================

-- =========================
-- USER ASSIGNMENT INDEXES
-- =========================

-- búsquedas por usuario (contexto en login / gateway)
CREATE INDEX idx_user_assignment_user_status
ON auth.user_assignment(user_id, status);

-- búsquedas por entidad (PORT / SHIP / ORG)
CREATE INDEX idx_user_assignment_entity
ON auth.user_assignment(entity_type, entity_id);
