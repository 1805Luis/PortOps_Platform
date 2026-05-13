# Keycloak en PortOps

## ¿Qué es Keycloak?
Keycloak es un servidor de identidad y acceso (IAM) de código abierto. Proporciona:

- Autenticación centralizada (login, logout, contraseña olvidada)
- Autorización basada en roles y permisos
- Gestión de usuarios, clientes y federación de identidades
- Soporte para OpenID Connect, OAuth 2.0 y SAML

En este proyecto, Keycloak se usa para proteger la API y la aplicación del ecosistema PortOps.

## ¿Qué es `realm-portops.json`?
`realm-portops.json` es un archivo de configuración de Keycloak que define un "realm" completo.
Un realm en Keycloak es un espacio aislado que contiene:

- Configuración del realm
- Roles globales
- Clientes (aplicaciones)
- Usuarios predefinidos

Este archivo se puede importar directamente en Keycloak para crear la configuración inicial del realm `portops`.

## Cómo se importa este realm

En este directorio de despliegue Docker, `realm-portops.json` está pensado para ser importado cuando se inicia Keycloak.
El contenedor Keycloak está configurado para cargar el realm al arrancar, este JSON se aplica automáticamente.
En caso contrario, se puede reimportar manualmente desde la consola de administración de Keycloak.

## Qué contiene este realm

### 1. Configuración del Realm
- `realm`: `portops`
- `displayName`: `PortOps Maritime`
- `enabled`: `true`
- `sslRequired`: `external` (solo requiere SSL para accesos externos)
- `loginWithEmailAllowed`: `true`
- `registrationAllowed`: `false` (registro público deshabilitado)
- `resetPasswordAllowed`: `true`
- `duplicateEmailsAllowed`: `false`
- `editUsernameAllowed`: `false` (no permite cambiar el nombre de usuario una vez creado)
- `accessTokenLifespan`: `3600` segundos (1 hora)

### 2. Roles globales del realm
El archivo define los siguientes roles globales que se asignan a usuarios:

- `ADMIN`: Acceso total al sistema sin restricciones de puerto ni dominio
- `VIEWER`: Acceso de solo lectura a datos permitidos por su puerto o ámbito
- `PORT_ADMIN`: Gestión operativa y administrativa del puerto (infraestructura, recursos, concesiones)
- `PORT_OPERATOR`: Ejecución de operaciones portuarias (coordinación de atraques, recursos y flujo operativo)
- `MARITIME_AUTHORITY`: Autoriza entrada/salida de buques y regula la navegación en el puerto
- `PILOT`: Asistencia técnica en maniobras de entrada/salida de buques dentro del puerto
- `VESSEL_CAPTAIN`: Control del propio buque y solicitud de operaciones portuarias
- `SHIP_AGENT`: Representación de la naviera en trámites operativos y documentales del puerto
- `CARRIER`: Transporte terrestre de mercancías desde y hacia el puerto
- `STEVEDORE`: Ejecución de carga y descarga de mercancías en operaciones portuarias
- `MOORING_TEAM`: Operaciones de amarre y desamarre de buques
- `CUSTOMS_OFFICER`: Autoridad aduanera para inspección y control de mercancías
- `BORDER_CONTROL`: Control de acceso de personas y documentación en el puerto
- `EMERGENCY_COORDINATOR`: Gestión y coordinación de emergencias en el entorno portuario
- `RESCUE_TEAM`: Ejecución de operaciones de rescate marítimo y portuario
- `ENVIRONMENTAL_RESPONSE`: Gestión de incidentes ambientales (derrames, contaminación)

### 3. Clientes
El realm incluye dos clientes configurados:

- `portops-api`: Cliente para la API de PortOps (bearer-only, sin flujo de autenticación directa)
- `portops-app`: Cliente para la aplicación web de PortOps (cliente público, con flujos estándar y de acceso directo, redirecciones a localhost:3000 y localhost:8090)

### 4. Usuarios predefinidos
El archivo incluye 15 usuarios predefinidos con credenciales temporales (contraseñas que terminan en @2026!). Cada usuario tiene asignado uno o más roles del realm. Los usuarios incluyen administradores, operadores portuarios, capitanes de buques, agentes, transportistas, estibadores, equipos de amarre, oficiales de aduanas, control de fronteras, coordinadores de emergencias, equipos de rescate, respuesta ambiental y visores.

Para una lista completa, consulta el archivo `realm-portops.json`.
- `CAPTAIN`: Capitán de barco con acceso solo lectura a su propio barco y sus operaciones.

### 3. Clientes (applications)
Se definen dos clientes principales:

- `portops-api`
  - Tipo: `bearer-only`
  - Usado como API protegida que solo acepta tokens.
- `portops-app`
  - Cliente público para la aplicación web.
  - Habilita el flujo estándar de OpenID Connect y acceso directo.
  - Redirect URIs permitidos:
    - `http://localhost:3000/*`
    - `http://localhost:8081/*`
  - Web origins permitidos:
    - `http://localhost:3000`
    - `http://localhost:8081`

### 4. Protocol Mappers importantes
Para `portops-app` se definen mapeos que añaden información útil al token:

- `roles-mapper`: expone los roles del realm como claim `role` multivalorado.

Estos mapeos permiten que la app y la API reciban datos como el identificador del usuario, puerto y roles dentro del token JWT.


## Datos interesantes

- El realm está diseñado para un dominio marítimo/portuario.
- El `clientId` `portops-api` es `bearer-only`, por lo que no inicia sesiones de usuario directamente.
- `portops-app` permite tanto el login con navegador como el uso de tokens OIDC.
- El archivo ya incluye usuarios con contraseñas claras para desarrollo local.

## Uso en Docker

Este JSON se importa automáticamente cuando se inicia Keycloak dentro de `deployments/docker/keycloak`.
Eso permite reproducir la configuración del realm `portops` con roles, clientes y usuarios predefinidos.

Si la importación automática no está habilitada, puedes reimportar `realm-portops.json` desde la consola de administración de Keycloak.

## Recomendaciones

- Cambiar las contraseñas de ejemplo antes de usar en producción.
- No dejar usuarios ni credenciales de desarrollo en entornos reales.
- Adaptar `redirectUris` y `webOrigins` según los dominios reales de despliegue.
- Confirmar que `registrationAllowed` siga deshabilitado si no se desea registro público.

