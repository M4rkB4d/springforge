# SpringForge

**Test-driven Spring Boot 4.0 learning platform.** Master backend services through failing tests, not tutorials.

## How It Works

1. Read the concept guide on Notion
2. Check out the exercise branch
3. Make the failing tests pass
4. Move to the next module

Every module has tests that start RED. Your job is to make them GREEN.

## Quick Start

```bash
# Clone the repo
git clone https://github.com/M4rkB4d/springforge.git
cd springforge

# No Maven install needed — the wrapper handles it
./mvnw --version

# Check out your first exercise
git checkout exercise/t1-02

# See what you need to do
cat exercises/t1-02-first-app/README.md

# Run the tests (they will FAIL — that's the point)
./mvnw test -Dtest="dev/springforge/t1_02/**"

# Or use the progress checker
./scripts/check-progress.sh t1-02
```

## Prerequisites

- **Java 25** (JDK) — [Download from Oracle](https://www.oracle.com/java/technologies/downloads/)
- **Git**
- **Docker** (for database exercises in Tier 2+)
- An IDE (IntelliJ IDEA recommended, VS Code works too)

## Choose Your Track

| Track | You Are | Start Here | Modules | Hours | Status |
|-------|---------|------------|---------|-------|--------|
| **Junior** | Java developer, new to Spring | T1-01 | 19 | 70-90h | ✅ Available (16 built, 3 planned) |
| **Enterprise** | Team upskilling together | T1-02 | 28 | 90-130h | ✅ Available (18 built, 10 planned) |
| **Migrating** | Spring Boot 2.x/3.x developer | MG-01 | 15 | 25-35h | 🔜 Coming Soon |
| **React Crossover** | Frontend developer learning backend | RX-01 | 18 | 80-110h | 🔜 Coming Soon |

> **Note:** Migrating and React Crossover tracks depend on modules not yet built (MG-01–03, RX-01–02).
> Junior and Enterprise tracks are fully usable today through Tier 3.

See [TRACKS.md](TRACKS.md) for the full module sequence and prerequisites for each track.

## Project Structure

```
springforge/
  exercises/          # Exercise stubs + RED tests (one dir per module)
  solutions/          # Complete implementations (main branch only)
  scripts/            # Helper scripts (check-progress, etc.)
  src/                # Shared application code
```

## Tech Stack

- **Spring Boot 4.0.4** (March 2026)
- **Spring Framework 7.0**
- **Java 25**
- **Maven** (with wrapper — no install needed)
- **JUnit 5 + AssertJ + MockMvc**
- **Testcontainers** (for database exercises)

## License

MIT
