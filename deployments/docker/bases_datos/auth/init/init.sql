-- ============================================
-- 1. Crear roles (solo si no existen)
-- ============================================
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'auth_user') THEN
        CREATE ROLE auth_user WITH LOGIN PASSWORD 'auth_pass';
    END IF;

    IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'flyway_user') THEN
        CREATE ROLE flyway_user WITH LOGIN PASSWORD 'flyway_pass';
    END IF;
END$$;

-- ============================================
-- 2. Permisos base sobre la base de datos
-- ============================================
GRANT CONNECT ON DATABASE db_auth TO auth_user;
GRANT CONNECT ON DATABASE db_auth TO flyway_user;

-- Flyway necesita crear tablas → CREATE en la DB
GRANT CREATE ON DATABASE db_auth TO flyway_user;

-- ============================================
-- 3. Crear esquema (si no existe)
-- ============================================
CREATE SCHEMA IF NOT EXISTS auth AUTHORIZATION flyway_user;

-- Permitir que ambos roles usen el esquema
GRANT USAGE ON SCHEMA auth TO auth_user;
GRANT USAGE ON SCHEMA auth TO flyway_user;

-- ============================================
-- 4. DEFAULT PRIVILEGES (para objetos futuros)
-- ============================================

-- Tablas futuras creadas por flyway_user → auth_user puede usarlas
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA auth
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO auth_user;

-- Secuencias futuras creadas por flyway_user → auth_user puede usarlas
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA auth
GRANT USAGE, SELECT ON SEQUENCES TO auth_user;

-- Flyway debe tener control total sobre sus objetos
ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA auth
GRANT ALL ON TABLES TO flyway_user;

ALTER DEFAULT PRIVILEGES FOR ROLE flyway_user IN SCHEMA auth
GRANT ALL ON SEQUENCES TO flyway_user;

-- ============================================
-- 5. PERMISOS PARA TABLAS YA EXISTENTES
-- ============================================

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA auth TO auth_user;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA auth TO auth_user;
