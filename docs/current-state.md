# Current state audit: Card-Game

Date: 16 Nov 2026. Base: `origin/main` at b516f15 (3 commits, all 14 Sep 2025).

## What exists
* One folder, `match card game demo/`, an IntelliJ module. There is no separate "demo" folder.
* `src/App.java` (6 lines) and `src/MatchCards.java` (266 lines).
* `src/img/`: 10 card faces and one card back, plus `src/img.zip`.
* `out/production/`: compiled classes and a second copy of every image and of `img.zip`, all tracked in git.
* `README.md`: two lines.

## What I ran
* Command: `javac -Xlint:all -d <scratch>/cg_out "match card game demo"/src/*.java` with Java 21.0.6. Exit code 0, no warnings, three class files written outside the worktree.
* I did not start the game, since it opens a window on your desktop. Every behavior claim below comes from reading the code, not from playing.

## What works, bounded by the checks above
* The source compiles cleanly on Java 21.
* Missing images fail loudly with the path in the message (MatchCards.java:194 and 211).
* Clicks are ignored while cards are hidden or a mismatch is showing (MatchCards.java:155, 161, 165).

## Defects, with location
All line numbers refer to `match card game demo/src/MatchCards.java` unless stated.
1. Lines 107 to 125 and 259: the clock is stopped on a win and the restart handler never starts it again. After a win and restart the time stays at 0s for the whole next game.
2. Lines 222 to 229: the shuffle swaps each position with a random position from the whole list. That method does not give every order the same chance. `Collections.shuffle` does.
3. `App.java` line 3: the window is built on the main thread, not on the Swing event thread through `SwingUtilities.invokeLater`.
4. Lines 143 and 134: the clock starts at construction, so the 1.5 second preview counts as play time.
5. Lines 152 and 105: `setFocusable(false)` on every card and on the restart button. The game cannot be played with a keyboard.
6. Lines 148 to 151: card buttons have no text and no accessible name, so a screen reader announces nothing useful.
7. Lines 160, 171 and 252: game state is read back from the button icons (`getIcon() == cardBackImageIcon`). Rules and display are one thing, so no rule can be tested without a window.
8. Lines 71 and 130: `setSize` is overridden by `pack`. Line 73: the fixed 600 by 656 board plus two bars cannot shrink on a small screen.
9. Lines 179 and 260: emoji in a comment and in the win dialog. Rendering depends on the system font.
10. Line 260: the win dialog blocks, and offers no restart choice.
11. Fields at lines 24 to 64 are all package visible and mutable.

## Repository gaps
* `out/` build output is tracked. No `.gitignore`.
* `img.zip` duplicates `img/`, and both are duplicated again under `out/`.
* The folder name has spaces, which makes every command need quotes.
* No build file. Only the IntelliJ `.iml`.
* No tests, no continuous integration, no LICENSE, no image credits.
* README does not say how to run, what Java version, or what the rules are.

## Sections 6 to 11 and 13
* Security (6): no network, no file writes, no input beyond clicks, no secrets found in tracked text files. No third party libraries, so the supply chain item is the JDK alone. `img.zip` was not opened.
* Performance (7): images are scaled once at start (lines 198 to 201). Not measured.
* Hosting (8), cost (10): not applicable at Tier A.
* Reliability (9): the restart and timer paths are the race prone area. All timers are Swing timers on one thread, which limits that. Defect 1 lives there.
* Process (11): "only runs on my machine" applies in full, since the run path is one IDE's project file.
* Definition of done (13): fails on documented commands, tests, continuous integration, LICENSE, PROJECT_BRIEF and docs.
* Web app addendum: does not apply. By analogy, keyboard access and labels are defects 5 and 6. Contrast was not checked.

## Largest gaps, ranked by damage times likelihood
1. Image rights unknown in a public repository. Low to medium likelihood, highest damage here.
2. No way to run from the README. Certain, and it blocks every visitor.
3. Clock defect after win and restart. Certain on the second game.
4. Rules untestable because they live in the view.
5. Tracked build output and duplicate archives.
