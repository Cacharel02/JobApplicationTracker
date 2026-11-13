# Project notes

_Last updated: 2026-11-13_

Progress tracker for the Job Application Tracker API. Source of truth for the
user stories (US) is the product backlog (priorities: High, Medium, Low).
Stable rules and conventions are in `AGENTS.md`, not here.

## Done

| US | Story | Priority |
|---|---|---|
| US1 | Register with email and password | High |
| US2 | Admin validates a new account | High |
| US3 | Login with email and password (JWT) | High |
| US4 | Admin bans a user | Medium |
| US5 | Create a company | High |
| US6 | View, update, delete my companies | High |
| US7 | Create a position attached to a company | High |
| US8 | View, update, delete my positions | High |
| US9 | Create a contact attached to a company | Medium |
| US10 | View, update, delete my contacts | Medium |
| US11 | Create an application linked to an existing position | High |
| US12 | Change the state of an application | High |

## In progress

_Maintained by the agent. Keep only the task(s) currently being worked on._

**Rules for the agent:**

- When starting a user story, add it here (US id, title, branch name) before
  writing code.
- Update the checklist as steps are completed, so the next session can resume
  from the exact step.
- When the story is finished and its tests pass, move it to the **Done** table
  and remove it from this section. Do not mark it done without the user's
  confirmation.
- Note any blocker or open question here instead of guessing.

**Current task:** none

<!-- Template to copy when starting a story:
**Current task:** US11 - Create an application linked to an existing position
**Branch:** feature/us11-create-application
**Checklist:**
- [ ] Flyway migration (`V{n}__create_application_table.sql`)
- [ ] Entity `Application`
- [ ] Repository (queries filtered by user id)
- [ ] DTOs and mapper
- [ ] Service (ownership check through position -> company -> user)
- [ ] Controller and route
- [ ] Tests, including isolation between users
- [ ] Manual check with Postman
**Blockers / questions:** none
-->

## Remaining, in the order to follow

Work through priorities in order: High, then Medium, then Low.

### High

_None remaining._

### Medium

| US | Story | Notes |
|---|---|---|
| US13 | Link one or more contacts to an application | Needs US9 and US11. Link table between application and contact |
| US14 | Filter my applications by state | Use `@RequestParam` on `GET /applications` |
| US16 | Swagger documentation | springdoc dependency; routes under `/swagger-ui/**` and `/v3/api-docs/**` are already public in `SecurityConfig` |

### Low

| US | Story | Notes |
|---|---|---|
| US15 | Simple statistics (count by state, response rate) | After the application features are stable |
| US17 | Dockerize the application | Last; Docker for the database was abandoned earlier, native PostgreSQL is used |

## Decisions

- No hard delete of users for now: bans go through the user status.
- Positions inherit ownership through their company.
- Every query is filtered by the authenticated user; another user's resource
  returns 404.
- Read DTOs expose the id of the resource they represent so the client can target
  update/delete routes; owner and foreign-key ids are never exposed nor accepted
  from request data.
- The nested company in read DTOs is a plain string (company name), not a
  `CompanyDto`.
- `PUT /contacts/{id}` replaces all mutable contact fields; the company of a
  contact cannot be changed through this route.

## Known issues

- Tests run against the development database (no isolated test setup yet).
  Do not write tests that delete existing data.
- No integration tests yet for isolation between users.

## Last session

- Done: US10 complete (view, update, delete contacts), confirmed by the user
  after manual Postman tests. Branch `contacts` ready for a pull request.
- Also fixed: `CompanyRepository.findByUserId` returned `Optional<Company>`, so
  `GET /companies` failed with more than one company (now `findAllByUserId`).
- Naming: read DTOs are named `*Response` (`ContactResponse`,
  `PositionResponse`); `PositionResponse` and `ContactResponse` expose the
  resource `id`, and the nested company is a plain string.
- Stopped at: ready to commit and open the pull request `contacts` -> `main`.
- Next: merge, then US14 (add `GET /applications`, then the state filter) or
  US13 (link contacts to an application).
- Check first on resume: `git status`, `git diff`, database running, `.env` present