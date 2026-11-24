# 0003: Keep Java 21 and Swing

Date: 23 Nov 2026. Status: proposed.

## Context
The game is 272 lines of Java using only `java.awt`, `java.util` and `javax.swing`. It compiles on Java 21.0.6 with no warnings in 0.42 s (measured 23 Nov 2026). Phase 1 found failing numbers for the clock, the shuffle, the fresh clone path and keyboard play. None of them is caused by Swing.

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Keep Java 21 and Swing | None | Older toolkit, but inside every JDK, no download | Yes | Recommended |
| JavaFX | 4 to 6 hours, separate download | New dependency and build steps | Costly | Animation or styling enters scope |
| Browser port | 8 hours or more | Rewrite with no measurement behind it; on the out of scope list | Costly | The goal becomes play in a browser |

## Decision
Keep. Java 21 is the stated minimum (assumption 4, accepted).

## Consequences
No new dependency. If a browser port is ever approved, this note and 0001 are replaced together.
