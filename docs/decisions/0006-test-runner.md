# 0006: A small test runner with no library

Date: 23 Nov 2026. Status: proposed.

## Context
There are no tests (A6). The brief asks to ask before adding any dependency, and plain `javac` was accepted (0005). Phase 1 needs at least 10 rule tests plus a shuffle frequency test.

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| `GameTest` with a `check(boolean, String)` helper; `main` runs every test, prints a count and exits 1 on any failure | 30 minutes | A runner bug could hide a failure; one test proves a failing check gives exit 1 | Yes | Recommended |
| JUnit 5 console launcher jar | 1 hour plus a download | Supply chain item, outside action | Yes | Tests pass about 30, or JUnit practice is wanted |
| `assert` with `-ea` | 15 minutes | Silently off without `-ea` | Yes | Never alone |

## Decision
The in repository runner. Tests run headless with `-Djava.awt.headless=true`. Switching to JUnit later moves each test body into an annotated method.
