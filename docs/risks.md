# Risks: Card-Game

Rebuilt 20 Nov 2026 from master prompt sections 6 to 11. Updated 23 Nov 2026 for Phase 2: mitigations now point to decisions and slices; risks 17 to 21 are new. Phase 0 risks kept, merged and numbered again; the old number is in brackets. Likelihood and damage are Low, Medium or High.

| # | Risk | Section | Likelihood | Damage | Mitigation | Phase |
|---|---|---|---|---|---|---|
| 1 | Card images are someone else's work with no license, in a public repository (P0 1) | 6, 11 | Medium | High | Replace with the Kenney pack, decision 0002 accepted 22 Nov 2026 | 4 (S1) |
| 2 | Publishing breaks the CSC 22100 section policy, or the work was a team project with shared authorship | 11 | Low | High | Check the section syllabus and ask the author before adding a LICENSE or promoting the repository | 1 |
| 3 | Visitors cannot run it: no commands, IDE only run path (P0 2) | 11 | High | Medium | README commands and a plain `javac` script, fresh clone test | 3 |
| 4 | Clock stays at 0s after a win and restart (P0 4) | 9 | High | Low | Test for win then restart before the fix | 4 to 5 |
| 5 | Biased shuffle: some layouts come up more often | 9 | High | Low | `Collections.shuffle` with an injected `Random`, plus a frequency test. Decision 0004. A model of the current loop scored 6,583 against a cutoff near 217 (23 Nov 2026) | 4 (S2) |
| 6 | Rules cannot be tested because state lives in button icons | 11 | High | Medium | `Game` class with no Swing imports. Decision 0004 | 4 (S2) |
| 7 | Refactor changes how the game plays (P0 3) | 11 | Medium | Medium | Tests pin the rules first, a play check after each slice | 4 |
| 8 | Window built off the Swing event thread, an occasional startup glitch | 9 | Low | Low | `SwingUtilities.invokeLater` in `App`. Decision 0004 | 3 (skeleton) |
| 9 | Restart while a wrong pair is showing shortens the next preview | 9 | Medium | Low | Restart stops the hide timer before `newGame`. Contract in docs/design.md (c) | 4 (S3) |
| 10 | No keyboard play and no accessible names | 13 | High | Medium | Focusable cards, key bindings, accessible names. Decision 0008 | 4 (S4) |
| 11 | No code license, so nobody may reuse it | 6, 11 | High | Low | MIT once risk 2 is settled. Decision 0010 | 4 (S5) |
| 12 | A build tool adds weight a 270 line game does not need (P0 5) | 11 | Medium | Low | Closed by decision 0005: plain `javac` scripts, 0.42 s compile measured | 2, closed |
| 13 | Removing tracked files loses something wanted (P0 6) | 11 | Low | Low | Ask first; history keeps them | 3 |
| 14 | Scope creep into a browser port (P0 7) | 11 | Medium | Medium | Out of scope list | All |
| 15 | Clock drift: Swing timers can fire late under load, so shown seconds lag wall time | 7, 9 | Low | Low | Start and stop readings from an injected monotonic clock. Decision 0007 | 4 (S3) |
| 16 | Stale README once the layout moves | 11 | Medium | Low | Fresh clone test at every phase close | 3 to 8 |
| 17 | The Kenney pack lacks one of the ten faces, or its style or size looks poor at 120 by 164 | 11 | Medium | Low | Check the download before replacing (assumption 14). Fallback: Java2D drawn cards | 4 (S1) |
| 18 | A third party CI action runs changed code | 6 | Low | Low | Pin `actions/setup-java` to a full commit hash; no secrets in the workflow | 3 |
| 19 | The home made test runner reports success when a check failed | 11 | Low | Medium | One test proves a failing check makes `test.sh` exit 1 | 3 |
| 20 | Starting the clock after the preview changes times the author expects | 11 | Low | Low | Ask first (assumption 15) | 4 (S3) |
| 21 | Timing budgets in docs/design.md (d) are estimates, not measurements | 7 | Medium | Low | Timing log in Phase 4; compare with budget after each slice | 4 to 5 |

Not applicable at Tier A, with reason (decision 0009): hosting (8), cost (10, ceiling 0 dollars, nothing hosted), network latency and availability (7, 9, no network), injection, authentication and secrets (6, no inputs beyond clicks, no accounts, none found). Supply chain (6) reduces to the JDK.
