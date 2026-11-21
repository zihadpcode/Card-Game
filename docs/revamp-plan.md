# Revamp plan: Card-Game

Date: 16 Nov 2026. Nothing here is started. Each slice waits for a go ahead.

## Phase map
| Phase | State | Evidence or gap |
|---|---|---|
| 0 Frame | In progress | This folder. Awaiting approval |
| 1 Requirements and risk | Not met | Rules not written down. Image rights unknown |
| 2 Design | Light | Two decisions: build tool, and rules class shape |
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
src/main/resources/img/*.jpg
src/main/resources/img/CREDITS.md
src/test/java/matchcards/GameTest.java
docs/
```
* Rename `match card game demo/` away, since the spaces hurt every command. This is a move, so history follows with `git mv`.
* Remove `out/` and both `img.zip` files from tracking, and add `.gitignore`. Deleting needs your explicit yes.
* Drop the `.iml` or keep it ignored. Your call.
* Fix the file names `Ace-of-spade.jpg` and the mixed capital letters only together with the name list at MatchCards.java:24 to 35.

## First slices, in order
1. Run path: `.gitignore`, a README with the exact `javac` and `java` commands for the current layout, Java version stated. No code change. Test: the commands work on a fresh clone.
2. Extract `Game` (deck, shuffle with `Collections.shuffle` and an injected `Random`, pick, match, error count, win) with unit tests, and make the view call it. Test: at least 8 tests pass, and the game plays as before. Needs your yes on the test library, or plain `assert` based tests with none.
3. Fix the clock: a failing test for win then restart, then restart the timer in the restart handler and start it after the preview. Test: the new test passes.
4. Event thread start in `App`, and keyboard play: focusable cards, arrow keys, visible focus, accessible names.
5. Image credits or replacement art, LICENSE, layout move to `src/main`.
6. Continuous integration that compiles and runs the tests.
