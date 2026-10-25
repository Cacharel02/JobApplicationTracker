# AGENTS.md

## Project

Single-module Spring Boot 4.1 REST API (Java 21, Maven) for tracking job applications.
PostgreSQL + Flyway, stateless JWT auth. Code lives under
`src/main/java/com/benjamin/JobApplicationTracker`; entrypoint is
`JobApplicationTrackerApplication`.

## Commands

Run from the repository root (Windows):

```powershell
.\mvnw.cmd compile
.\mvnw.cmd test
.\mvnw.cmd test -Dtest=JobApplicationTrackerApplicationTests
.\mvnw.cmd spring-boot:run
```

Use `./mvnw` on Unix/macOS. There is no lint, formatter, typecheck, or CI
configuration; `test` is the full verification step.

## Prerequisites and environment

- `.env` (or the environment) must define `JWT_SECRET` as Base64 of at least
  32 bytes. It is loaded by
  `spring.config.import: optional:file:.env[.properties]`, so run Maven from
  the repo root; otherwise startup fails on the unresolved `jwt.secret`.
- `application.yaml` hardcodes the local PostgreSQL connection:
  `localhost:5432/job_tracker`, user `job_tracker_user`. `mvn test` starts the
  full `@SpringBootTest` context against this database and runs Flyway; there
  is no test profile or isolated H2 setup even though H2 is on the classpath.
  PostgreSQL must be running before tests.
- `spring.jpa.hibernate.ddl-auto=validate`: migrations in
  `src/main/resources/db/migration` are the only schema source. Add a new
  `V{n}__*.sql`; never edit an applied migration. Tables are `users` (plural)
  but `company` and `position` (singular).

## Conventions

- Layering: `controller` -> `I*Service` + `service/impl/*ServiceImpl` ->
  `repository`. DTOs are mostly Lombok `@Data` classes in `dto` (`AuthResponse`
  is the one record); mappers are static methods in `mapper`; errors are mapped
  centrally in `GlobalExceptionHandler`.
- The POM targets Spring Boot 4.1 and uses Boot 4 starter names
  (`spring-boot-starter-webmvc`, `spring-boot-starter-flyway`, etc.); match
  those when adding dependencies.
- Security: `/auth/**` is public, `/admin/**` requires `ROLE_ADMIN`, and
  every other route requires a valid JWT.
- Per-user isolation: controllers read the owner from
  `@AuthenticationPrincipal SecurityUser` and pass `userId` to services;
  repository methods combine the resource id/name with `userId`. Keep this
  pattern and never accept an owner/user id from request data. Positions
  inherit ownership through `company`.
- Roles are `ADMIN` and `SIMPLE_USER`, producing `ROLE_ADMIN` /
  `ROLE_SIMPLE_USER` (not `ROLE_USER`). `SecurityUser.isEnabled()` is true only
  for `APPROVED`; registration creates `PENDING` users who cannot log in until
  an admin calls `/admin/validate/{email}`.
- Company lookups: `GET /companies` lists all, `GET /companies?name={name}`
  finds by name, and `GET /companies/{id}` finds by id.

## Rules for the agent

- Never read, print, or modify `.env`, and never write secrets (JWT key,
  passwords, API keys) in code, config, logs, or this file.
- Do not run `git commit`, `git push`, or any destructive command (file or
  database deletion, `DROP`, `DELETE` without `WHERE`) unless explicitly asked.
- Tests run against the local development database: do not write tests that
  delete or truncate existing data.
- Do not create or modify test files for now; tests will be handled separately.
- Work on the current branch only; keep changes small and focused so the diff
  is easy to review. List the files you changed at the end.
- Reply to the user in French. Code, identifiers, comments, and commit
  messages are in English.

## API and error conventions

- REST routes: plural nouns, HTTP method gives the action (`GET /companies`,
  `GET /companies/{id}`); filters via `@RequestParam`, identification via
  `@PathVariable`.
- Business exceptions extend `RuntimeException` and are translated in
  `GlobalExceptionHandler`: 404 not found (also for another user's resource,
  never 403), 409 duplicate, 401 bad credentials, 403 pending/banned account.
- Never expose JPA entities in responses; return DTOs.
- Never trust an id sent by the client for ownership (see per-user isolation).

## Domain

- Entities: `User`, `Company`, `Position`, `Contact`, `Application`; they extend
  `BaseEntity` (`id`, `created_at`, `updated_at`).
- Ownership chain: `Position` -> `Company` -> `User`.
- User status: `PENDING`, `APPROVED`, `BANNED`.
- Application state: `TO_SEND`, `SENT`, `INTERVIEW`, `REJECTED`, `ACCEPTED`,
  `ARCHIVED`.
