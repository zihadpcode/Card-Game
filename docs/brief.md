# Phase 0 brief: Card-Game

Date: 16 Nov 2026. Status: awaiting approval.

## Problem statement
* Who: the author, and anyone who lands on the GitHub repository.
* Today: the README is two lines. To play, a visitor must guess that this is an IntelliJ project, open it and run `App`. The rules live inside button listeners, so none of them can be tested, and at least one visible defect ships (the clock stays at zero after a win and restart).
* What changes: one documented command builds and starts the game, the rules sit in a small class with tests, and the known defects are fixed one at a time.

## Success outcome
By 30 Nov 2026: on a fresh clone with only a JDK, the README command compiles and starts the game, at least 10 unit tests on the rules pass (deal, match, mismatch, win, restart, clock), and the restart after win clock defect has a test that fails before the fix and passes after.

## Out of scope
* A browser or mobile port.
* Multiplayer, saved high scores, sound, animation.
* New card art.
* Installers or app store packaging.

## Prior art
Not rechecked on the web today. Memory match games in Swing are a common tutorial exercise, and this code follows that familiar shape. Confirm sources in Phase 1, including where the card images came from.

| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Build: rewrite from scratch | 4 to 6 hours | Loses working behavior | Yes | Never, the game already works |
| Adopt: point to an existing open source game | Minutes | Shows no work of the author's | Yes | The goal is only to play |
| Adapt: keep the Swing view, pull the rules into a tested class, add a build | 3 to 4 hours | Small refactor risk, covered by tests | Yes | The goal is a credible portfolio piece. Recommended |

## Riskiest assumption
The author has the right to publish the 11 card images. `src/img` holds JPG files with no source or license note, in a public repository.

Cheapest experiment: you say where they came from. If the source is unknown, swap in public domain card art. That needs your yes because it replaces files.

## Rigor tier
Tier A. Laptop only, no data. Skipped: hosting, monitoring, cost, threat model, backups beyond the existing GitHub remote. Kept in full: correctness and tests.

## Open questions
| Question | Resolve by |
|---|---|
| Where did the card images come from, and under what license? | 20 Nov 2026 |
| Is this a tutorial follow along? If yes, the README should credit it | 20 Nov 2026 |
| May `out/` and both `img.zip` files be removed from the repository? | 20 Nov 2026 |
| Plain `javac` script, Gradle or Maven? Options table comes in Phase 2 | 23 Nov 2026 |
| Which license for the code? | 23 Nov 2026 |
