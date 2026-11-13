# Job Application Tracker API

API REST pour suivre ses candidatures : entreprises, postes, contacts et état d'avancement de chaque candidature. Chaque utilisateur ne voit et ne modifie que ses propres données.

> Projet personnel en cours de développement.

## Fonctionnalités

- Inscription et connexion par email et mot de passe, avec authentification par **JWT**
- Rôles `SIMPLE_USER` et `ADMIN` (`ROLE_SIMPLE_USER` / `ROLE_ADMIN`), avec validation des comptes par un administrateur (`PENDING`, `APPROVED`, `BANNED`)
- Gestion des **entreprises**, **postes**, **contacts** et **candidatures**
- Suivi de l'état d'une candidature : `TO_SEND`, `SENT`, `INTERVIEW`, `REJECTED`, `ACCEPTED`, `ARCHIVED`
- Isolation des données : toute ressource est filtrée par l'utilisateur connecté

## Stack technique

- Java 21
- Spring Boot 4.1 (Spring Web MVC, Spring Security, Spring Data JPA / Hibernate)
- PostgreSQL
- Flyway (migrations de base de données)
- JWT (bibliothèque jjwt)
- Maven

## Architecture

Organisation en couches : `controller` → `service` → `repository`, avec des DTO, des mappers statiques et un `GlobalExceptionHandler` (`@ControllerAdvice`) pour la gestion centralisée des erreurs.

## Endpoints

Toutes les routes sauf `/auth/**` exigent l'en-tête `Authorization: Bearer <token>` ; chaque utilisateur ne voit que ses propres données.

| Groupe | Routes |
|---|---|
| Authentification (public) | `POST /auth/register`, `POST /auth/login` (retourne le JWT) |
| Administration (`ROLE_ADMIN`) | `POST /admin/validate/{username}`, `POST /admin/ban/{username}` (`username` = email) |
| Entreprises | `POST /companies`, `GET /companies`, `GET /companies?name={name}`, `GET /companies/{id}`, `PUT /companies/{id}`, `DELETE /companies/{id}` |
| Postes | `POST /companies/{companyId}/positions`, `GET /companies/{companyId}/positions`, `GET /positions`, `PUT /positions/{id}`, `DELETE /positions/{id}` |
| Contacts | `POST /companies/{companyId}/contacts`, `GET /companies/{companyId}/contacts`, `GET /contacts`, `PUT /contacts/{id}`, `DELETE /contacts/{id}` |
| Candidatures | `POST /positions/{positionId}/applications`, `PATCH /applications/{id}/state` |

## Prérequis

- JDK 21
- Maven (ou le wrapper `mvnw` fourni)
- PostgreSQL 16

## Installation et lancement

**1. Créer la base de données**

```sql
CREATE USER job_tracker_user WITH PASSWORD 'votre_mot_de_passe';
CREATE DATABASE job_tracker OWNER job_tracker_user;
```

**2. Configurer les variables d'environnement**

Créer un fichier `.env` à la racine du projet (il est ignoré par Git) :

```
JWT_SECRET=<clé secrète encodée en Base64, 32 octets minimum>
```

Ce fichier est chargé relativement au répertoire d'exécution : lancez Maven depuis la racine du projet. Adaptez ensuite `src/main/resources/application.yaml` pour l'accès à la base (URL, utilisateur, mot de passe).

**3. Lancer l'application**

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Unix / macOS
./mvnw spring-boot:run
```

Les migrations Flyway (`src/main/resources/db/migration`) sont appliquées automatiquement au démarrage.

## Tests

```bash
# Windows
.\mvnw.cmd test

# Unix / macOS
./mvnw test
```

PostgreSQL doit être démarré : les tests utilisent la base de développement.

## État d'avancement

- [x] Modélisation, cahier des charges, architecture
- [x] Authentification JWT et gestion des rôles
- [x] Administration des comptes (validation, bannissement)
- [x] Entreprises (CRUD)
- [x] Postes (CRUD)
- [x] Contacts : création, consultation, modification et suppression
- [x] Candidatures : création et changement d'état
- [ ] Liaison des contacts à une candidature (US13)
- [ ] Filtre des candidatures par état (US14)
- [ ] Documentation Swagger (US16)
- [ ] Statistiques simples (US15)
- [ ] Dockerisation (US17)
- [ ] Tests d'intégration (isolation entre utilisateurs)

## Auteur

Benjamin Nana
