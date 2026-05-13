# 📘 PortOps Platform
<div align="center">
  "El mar, señor, es un enemigo formidable.” — Master and Commander 
</div>

### Plataforma modular de gestión portuaria basada en microservicios

**PortOps Platform** es un ecosistema distribuido diseñado para gestionar de forma integral la información, servicios y operaciones de puertos marítimos.  
La plataforma combina datos estáticos del puerto (infraestructura, servicios disponibles, restricciones) con operaciones dinámicas (atraques, inspecciones, servicios portuarios, operaciones logísticas), permitiendo una visión completa y en tiempo real del entorno portuario.

Está construida sobre una arquitectura moderna basada en **microservicios independientes**; cada uno tiene su propia base de datos y se encarga de un dominio específico; garantizando escalabilidad, resiliencia y facilidad de mantenimiento, comunicación síncrona mediante **REST**, comunicación asíncrona mediante **Kafka**, autenticación centralizada con **JWT**, y despliegue flexible en **local, Docker y Kubernetes**.

---

# 🧩 Arquitectura general

La plataforma está compuesta por los siguientes microservicios, cada uno con su propia base de datos, lógica de dominio y documentación.



---

# 🎯 Objetivos principales

- Centralizar la información de puertos, terminales, atraques y servicios.  
- Gestionar operaciones portuarias en tiempo real.  
- Integrar sistemas externos mediante XML y eventos Kafka.  
- Proveer un sistema seguro de autenticación y roles.  
- Facilitar la escalabilidad y la interoperabilidad entre servicios.  
- Mantener trazabilidad completa mediante bitácora de auditoría.  
- Ofrecer una plataforma moderna y extensible para el sector marítimo.

---

# 🏗️ Tecnologías principales

- **Java 17 / Spring Boot 3**  
- **Spring Security + JWT**
- **Keycloak** (IAM y autenticación centralizada)
- **Spring Data JPA**  
- **Flyway** (migraciones)  
- **Kafka** (event bus)
- **Redis** (caché distribuida)
- **Docker / Docker Compose**  
- **Kubernetes**  
- **OpenAPI / Swagger**  
- **Lombok**
- 

## Cada microservicio contiene su propio README con:

- descripción del dominio  
- entidades  
- endpoints  
---

## 🔄 Comunicación entre microservicios

### ✔ REST  
Para operaciones síncronas (consultas, creación de recursos).

### ✔ Kafka  
Para eventos del dominio.

Esto permite desacoplar microservicios y procesar eventos en tiempo real.

---

## 🔐 Seguridad

- Autenticación mediante JWT  
- Roles definidos.  
- Validación centralizada en el Gateway  
- Autorización por dominio en cada microservicio  
- Refresh tokens gestionados por un provedor externo llamado Keycloak   
---

## 🚀 Puesta en marcha

La forma de ejecutar la plataforma depende del entorno elegido (local, Docker o Kubernetes).  

### Flujo general recomendado

1. Clonar el repositorio  
2. Revisar el README de cada microservicio  
3. Levantar la infraestructura necesaria (bases de datos, mensajería, etc.) según el entorno  
4. Ejecutar los microservicios individualmente o mediante los archivos de orquestación correspondientes  
5. Acceder a la documentación Swagger expuesta por cada servicio

> **Nota:** La plataforma está diseñada para ser modular.  

## 📄 Licencia

Proyecto de arquitectura educativa y demostrativa.  
Puede adaptarse libremente para entornos reales.

---

## 📬 Contacto

Para dudas, mejoras o sugerencias, puedes abrir un issue o contactar con el autor del proyecto.
- Email: [luiszr2002@gmail.com](mailto:luiszr2002@gmail.com)



