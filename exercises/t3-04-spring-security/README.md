# T3-04: Spring Security + JWT (Azure Entra ID Patterns)

## What You'll Learn
- Configuring SecurityFilterChain for a stateless REST API
- OAuth2 resource server with JWT validation
- Extracting claims from JWT tokens (@AuthenticationPrincipal)
- Role-based access control with @PreAuthorize
- How Azure Entra ID maps to Spring Security concepts

## Prerequisites
- T2-01 (REST Controllers)
- T2-02 (Validation & Error Handling)

## The Azure Entra ID Flow

In production with Azure Entra ID:
1. User authenticates via Entra ID (browser redirect)
2. Entra ID issues a JWT token with claims (sub, name, email, roles)
3. Frontend sends JWT in Authorization header: `Bearer <token>`
4. Your Spring Boot API validates the JWT signature
5. Spring Security extracts claims and maps roles to authorities
6. @PreAuthorize checks authorities before method execution

These exercises teach steps 4-6 using mock JWTs. The patterns
are identical whether the JWT comes from Entra ID, Auth0, or Keycloak.

## Exercises

### Exercise 1: Security Filter Chain
**File to edit:** `SecurityConfig.java`
Configure public vs secured endpoints and enable JWT support.

```bash
./mvnw test -Dtest="dev.springforge.t3_04.Ex01_SecurityFilterChainTest"
```

### Exercise 2: JWT Authentication
**File to edit:** `SecureController.java` — profile endpoint
Extract JWT claims and return user information.

```bash
./mvnw test -Dtest="dev.springforge.t3_04.Ex02_JwtAuthenticationTest"
```

### Exercise 3: Role-Based Access Control
**File to edit:** `SecureController.java` — admin endpoint
Add @PreAuthorize to restrict access by scope/role.

```bash
./mvnw test -Dtest="dev.springforge.t3_04.Ex03_RoleBasedAccessTest"
```

## Check Your Progress
```bash
bash scripts/check-progress.sh t3-04
```

## Key Concepts

### JWT Scopes → Spring Authorities
| JWT Claim | Spring Security Authority |
|-----------|--------------------------|
| scope: "read" | SCOPE_read |
| scope: "admin" | SCOPE_admin |
| roles: ["Manager"] | ROLE_Manager (with custom converter) |

### Entra ID Configuration (Production)
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://login.microsoftonline.com/{tenant-id}/v2.0
```

### Testing Without Entra ID
Spring Security Test provides `jwt()` post-processor that creates
mock JWT tokens. This lets you test security logic without needing
an actual identity provider running.
