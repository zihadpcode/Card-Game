# Revamp plan: Card-Game

Date: 16 Nov 2026. Updated 23 Nov 2026 for Phase 2: phase map refreshed, Phase 3 and 4 slices now match docs/design.md. Nothing here is started. Each slice waits for a go ahead.

## Phase map
| Phase | State | Evidence or gap |
|---|---|---|
| 0 Frame | Approved 19 Nov 2026 | docs/brief.md |
| 1 Requirements and risk | Approved 22 Nov 2026 | docs/requirements.md, risks, acceptance tests |
| 2 Design | Awaiting approval | docs/design.md, decisions 0003 to 0010 |
| 3 Scaffold | Not met | No build file, no tests, no ignore rules |
| 4 Build | Met for play | Deal, flip, match, error count, clock, restart all exist in code. Compiles on Java 21 |
| 5 Harden | Not met | Defects 1 to 6 in docs/current-state.md |
| 6 Deploy and operate | Skipped at Tier A | A runnable jar is the most this needs |
| 7 Validate | Not met | No fresh clone run, no outside player |
| 8 Maintain | Not met | |

## Proposed restructuring
```
README.md  LICENSE  PROJECT_BRIEF.md  .gitignore
src/main/java/matchcards/App.java
src/main/java/matchcards/Game.java        (rules only, no Swing imports)
src/main/java/matchcards/MatchCardsView.java
src/main/resources/img/*   (Kenney CC0 art after slice S1)
src/main/resources/img/CREDITS.md
src/main/java/matchcards/CardImages.java
src/test/java/matchcards/GameTest.java
build.sh  run.sh  test.sh  .github/workflows/ci.yml
docs/
```
* Rename `match card game demo/` away, since the spaces hurt every command. This is a move, so history follows with `git mv`.
* Remove `out/` and both `img.zip` files from tracking, and add `.gitignore`. Deleting needs your explicit yes.
* Drop the `.iml` or keep it ignored. Your call.
* Fix the file names `Ace-of-spade.jpg` and the mixed capital letters only together with the name list at MatchCards.java:24 to 35.

## Phase 3: scaffold and walking skeleton
"Outside action" means a network download, an account, a push to the remote, or a deploy. Each needs the user's yes at that step.

| # | Step | Test | Outside action |
|---|---|---|---|
| K1 | `git mv` into `src/main/java/matchcards` and `src/main/resources/img`; add `package matchcards`; `.gitignore` | `build.sh` compiles; the game opens as before | None. The move is local |
| K2 | Remove `out/` and both `img.zip` files from tracking (decision 0005) | `git ls-files` shows none of them | None, but it deletes tracked files, so explicit yes |
| K3 | `build.sh`, `run.sh`, `test.sh`; README with the same commands and Java 21 | Fresh clone into a temporary folder builds and starts in under 5 minutes (Q9) | None |
| K4 | `App` builds the window inside `invokeLater` (Q6) | Code review; game opens | None |
| K5 | First `Game` with constructor, `newGame` and accessors; `GameTest` with the deal test and the runner self test (risk 19) | `test.sh` prints 2 passed and exits 0; a forced failure exits 1 | None |
| K6 | CI workflow running `build.sh` and `test.sh` on JDK 21, action pinned by hash | Green run on GitHub | Yes: push to the remote, and GitHub runs third party action code |
| K7 | Rollback proof: run the README command from the commit before the skeleton, then return | Old version opens | None |

Logging, health check, environment config and a deploy target do not apply at Tier A (decision 0009, docs/design.md (e)).

## Phase 4: vertical slices, in order
| # | Slice | Tests | Outside action |
|---|---|---|---|
| S1 | Card art: download the Kenney pack, check assumption 14, replace the 11 images, update the name list and file type constant, add `CREDITS.md` (decision 0002). Riskiest assumption first | Every name loads; a missing file fails with its path; one manual play | Yes: network download from kenney.nl, and replacing tracked files needs explicit yes |
| S2 | Rules move into `Game`: flip, match, mismatch, win, fair shuffle; the view draws from `Game` (decision 0004) | At least 8 rule tests and the chi square frequency test (Q5); manual play matches F1, F4 | None |
| S3 | Clock from an injected monotonic clock, starts after preview, stops at win; Restart stops the hide timer first (decision 0007, risk 9) | Win then restart then 300 s on a fake clock shows 300 (A3, Q4); restart during a mismatch gives a full 1.5 s preview | None |
| S4 | Keyboard play and accessible names (decision 0008) | Win with keyboard only; VoiceOver reads names (A7) | None |
| S5 | MIT LICENSE, README rules and coursework note (decision 0010); timing log for Q1 and Q2 | Fresh clone test; p95 within budgets in docs/design.md (d) | None, once the author confirms assumptions 11 and 12 |

Pushing finished slices to the remote is an outside action each time.
