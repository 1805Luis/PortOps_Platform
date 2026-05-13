-- ============================================
-- 1. Crear roles
-- ============================================
CREATE ROLE auth_user WITH LOGIN PASSWORD 'auth_pass';
CREATE ROLE flyway_user WITH LOGIN PASSWORD 'flyway_pass';

-- ============================================
-- 2. Permisos base sobre DB
-- ============================================
GRANT CONNECT ON DATABASE db_auth TO auth_user;
GRANT CONNECT ON DATABASE db_auth TO flyway_user;

-- ============================================
-- 3. Permisos sobre SCHEMA
-- ============================================
GRANT USAGE ON SCHEMA public TO auth_user;
GRANT USAGE ON SCHEMA public TO flyway_user;

GRANT ALL ON SCHEMA public TO flyway_user;

-- ============================================
-- 4. PERMISOS FUTUROS (Flyway crea → auth_user usa)
-- ============================================

-- Tablas futuras creadas por flyway_user
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO auth_user;

-- Secuencias futuras
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA public
GRANT USAGE, SELECT ON SEQUENCES TO auth_user;

-- Flyway sobre sus propios objetos
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA public
GRANT ALL ON TABLES TO flyway_user;

ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA public
GRANT ALL ON SEQUENCES TO flyway_user;

-- ============================================
-- 5. PERMISOS PARA TABLAS YA EXISTENTES (CLAVE)
-- ============================================

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO auth_user;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO auth_user;