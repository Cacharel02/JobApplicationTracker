# Job Application Tracker API

API REST pour suivre ses candidatures : entreprises, postes, contacts et état d'avancement de chaque candidature. Chaque utilisateur ne voit et ne modifie que ses propres données.

> Projet personnel en cours de développement.

## Fonctionnalités

- Inscription et connexion par email et mot de passe, avec authentification par **JWT**
- Rôles `USER` et `ADMIN`, avec validation des comptes par un administrateur (`PENDING`, `APPROVED`, `BANNED`)
- Gestion des **entreprises**, **postes**, **contacts** et **candidatures**
- Suivi de l'état d'une candidature : `TO_SEND`, `SENT`, `INTERVIEW`, `REJECTED`, `ACCEPTED`, `ARCHIVED`
- Isolation des données : toute ressource est filtrée par l'utilisateur connecté

## Stack technique

- Java 21
- Spring Boot, Spring Web, Spring Security, Spring Data JPA (Hibernate)
- PostgreSQL
- Flyway (migrations de base de données)
- JWT (bibliothèque jjwt)
- Maven

## Architecture

Organisation en couches : `controller` → `service` → `repository`, avec des DTO (`record`) pour les échanges et un `@RestControllerAdvice` pour la gestion centralisée des erreurs.

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


Adapter ensuite `src/main/resources/application.yaml` pour l'accès à la base (URL, utilisateur, mot de passe).

**3. Lancer l'application**

```bash
mvn spring-boot:run
```

Les migrations Flyway (`src/main/resources/db/migration`) sont appliquées automatiquement au démarrage.


## Tests

```bash
mvn test
```

## État d'avancement

- [x] Modélisation, cahier des charges, architecture
- [x] Authentification JWT et gestion des rôles
- [ ] Entreprises, postes, contacts, candidatures
- [ ] Administration des comptes
- [ ] Tests d'intégration (isolation entre utilisateurs)

## Auteur

Benjamin Nana