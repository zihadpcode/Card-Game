# 0009: Hosting, data store, authentication and background work do not apply

Date: 23 Nov 2026. Status: proposed.

## Context
Phase 2 asks for a decision on each. The game runs on the player's own machine, stores nothing, has no accounts and does no background work beyond two Swing timers.

## Decision
* Hosting: none. The repository on GitHub is the distribution. Exit path: any Git host; nothing else to move.
* Data store: none. State lives in memory (docs/design.md section (b)).
* Authentication: none. One player, no accounts.
* Background work: none beyond Swing timers on the event thread.
* Continuous integration: GitHub Actions, since the repository is already there. The GitHub billing page, read 23 Nov 2026, says standard GitHub hosted runners are free in public repositories (https://docs.github.com/en/billing/managing-billing-for-your-products/managing-billing-for-github-actions/about-billing-for-github-actions). Any third party action, such as `actions/setup-java`, is pinned to a full commit hash.

## Options for CI
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| GitHub Actions | 0 dollars for a public repository | Third party action code; pin by hash | Yes, delete one file | Recommended |
| No CI, run `test.sh` before each commit | None | Forgotten runs | Yes | The author prefers no workflow file |

## Consequences
If saved scores or online play enter scope, this note is replaced with real options tables.
