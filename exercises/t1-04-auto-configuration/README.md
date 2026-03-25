# T1-04: Auto-Configuration

**Prereqs:** T1-03 (IoC and Dependency Injection)
**Exercises:** 3 (6 tests total)
**Time:** 30–45 minutes

## What You'll Learn

How Spring Boot configures itself — the magic behind "it just works."
After this module, you'll understand how to customize, override, and
conditionally control any part of Spring Boot's behavior.

## Exercises

### Exercise 1: @ConfigurationProperties ★★☆☆☆

Bind YAML config to a Java record. Type-safe configuration — no more
getString() and parseInt().

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_04.Ex01*"
```

### Exercise 2: @Value Injection ★★☆☆☆

Inject single properties with defaults. Two tests prove it reads the
property AND falls back to the default.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_04.Ex02*"
```

### Exercise 3: @ConditionalOnProperty ★★★☆☆

The heart of auto-configuration: beans that only exist when conditions
are met. Two tests — one proves the bean exists when enabled, one proves
it disappears when disabled.

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_04.Ex03*"
```

## Run All T1-04 Tests

```bash
./mvnw test -pl . -Dtest="dev.springforge.t1_04.*"
```

## Key Concepts

| Concept | What It Does |
|---|---|
| @ConfigurationProperties | Binds YAML prefix to Java object |
| @Value | Injects single property with default |
| @ConditionalOnProperty | "Only create this bean if property X = Y" |
| @ConditionalOnClass | "Only if this class is on classpath" |
| @ConditionalOnMissingBean | "Only if nobody else defined this bean" |
| application.yml | Central config — Spring reads it automatically |
