# 0001: Phase 0 base, worktree, tier and addenda

Date: 16 Nov 2026. Status: proposed.

## Context
The checkout at `~/Downloads/Card-Game` is clean, on `main`, level with `origin/main`. There are no other branches. The game is a Java Swing desktop program.

## Decision
* Work in a worktree at `~/projects/revamp/card-game` on branch `revamp/phase-0`, based on `origin/main` at b516f15.
* Tier A.
* No section 14 addendum is kept. The web app addendum was considered and dropped because nothing runs in a browser. Its accessibility points are still audited by analogy.
* Phase 0 writes documents only. No commits.

## Options considered
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Treat as desktop app, no addendum | None | Accessibility could be forgotten, so it is listed in the audit | Yes | Chosen |
| Apply the web app addendum in full | Wasted checks on sessions and content policy | Misleading audit | Yes | The game is ported to a browser |
| Apply the command line or library addendum | Poor fit | Misleading audit | Yes | The rules become a reusable library |

## Consequences
If a browser port is ever approved, this note is replaced and the web app addendum returns.
