# T1-03: IoC and Dependency Injection

**Prereqs:** T1-02 (Spring Boot First App)
**Exercises:** 3 (9 tests total)
**Time:** 30–45 minutes

## What You'll Learn

The core of Spring: how the framework creates and wires your objects together.
After this module, `@Service`, `@Autowired`, and `@Profile` won't be magic anymore.

## Exercises

### Exercise 1: Component Scanning ★☆☆☆☆

Make Spring discover your service. Add `@Service`, implement the method,
and watch Spring wire it automatically.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_03.Ex01*"
```

### Exercise 2: Constructor Injection ★★☆☆☆

The right way to inject dependencies. Build a service that depends on another
service — Spring handles the wiring.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_03.Ex02*"
```

### Exercise 3: Profiles ★★★☆☆

Swap implementations based on environment. Production vs test, formal vs casual —
profiles let you configure without changing code.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_03.Ex03*"
```

## Run All T1-03 Tests

```bash
./mvnw test -pl . -Dtest="dev/springforge/t1_03/**"
```

## Key Concepts

| Concept | What It Does |
|---|---|
| @Component / @Service | "Hey Spring, manage this class for me" |
| Constructor Injection | Spring passes dependencies via constructor |
| @Autowired | Explicit injection marker (optional with single constructor) |
| @Profile | "Only create this bean for this environment" |
| IoC Container | Spring creates objects so YOU don't have to |
