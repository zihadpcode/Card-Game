# Risks: Card-Game

Rebuilt 20 Nov 2026 from master prompt sections 6 to 11. Phase 0 risks kept, merged and numbered again; the old number is in brackets. Likelihood and damage are Low, Medium or High.

| # | Risk | Section | Likelihood | Damage | Mitigation | Phase |
|---|---|---|---|---|---|---|
| 1 | Card images are someone else's work with no license, in a public repository (P0 1) | 6, 11 | Medium | High | Replace with CC0 art or credit a verified source. Decision 0002 | 3 to 5 |
| 2 | Publishing breaks the CSC 22100 section policy, or the work was a team project with shared authorship | 11 | Low | High | Check the section syllabus and ask the author before adding a LICENSE or promoting the repository | 1 |
| 3 | Visitors cannot run it: no commands, IDE only run path (P0 2) | 11 | High | Medium | README commands and a plain `javac` script, fresh clone test | 3 |
| 4 | Clock stays at 0s after a win and restart (P0 4) | 9 | High | Low | Test for win then restart before the fix | 4 to 5 |
| 5 | Biased shuffle: some layouts come up more often | 9 | High | Low | `Collections.shuffle` with an injected `Random`, plus a frequency test | 4 |
| 6 | Rules cannot be tested because state lives in button icons | 11 | High | Medium | Extract a rules class with no Swing imports | 4 |
| 7 | Refactor changes how the game plays (P0 3) | 11 | Medium | Medium | Tests pin the rules first, a play check after each slice | 4 |
| 8 | Window built off the Swing event thread, an occasional startup glitch | 9 | Low | Low | `SwingUtilities.invokeLater` in `App` | 5 |
| 9 | Restart while a wrong pair is showing shortens the next preview | 9 | Medium | Low | Stop the hide timer in the restart handler; add a test | 5 |
| 10 | No keyboard play and no accessible names | 13 | High | Medium | Focusable cards, key bindings, accessible names | 5 |
| 11 | No code license, so nobody may reuse it | 6, 11 | High | Low | Add a LICENSE once risk 2 is settled | 3 |
| 12 | A build tool adds weight a 270 line game does not need (P0 5) | 11 | Medium | Low | Plain `javac` as the default, options table in Phase 2 | 2 |
| 13 | Removing tracked files loses something wanted (P0 6) | 11 | Low | Low | Ask first; history keeps them | 3 |
| 14 | Scope creep into a browser port (P0 7) | 11 | Medium | Medium | Out of scope list | All |
| 15 | Clock drift: Swing timers can fire late under load, so shown seconds lag wall time | 7, 9 | Low | Low | Compute seconds from a start time, not by counting ticks; pass the clock in for tests | 4 |
| 16 | Stale README once the layout moves | 11 | Medium | Low | Fresh clone test at every phase close | 3 to 8 |

Not applicable at Tier A, with reason: hosting (8), cost (10, ceiling 0 dollars, nothing hosted), network latency and availability (7, 9, no network), injection, authentication and secrets (6, no inputs beyond clicks, no accounts, none found). Supply chain (6) reduces to the JDK.
