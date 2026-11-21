# Requirements: Card-Game

Date: 20 Nov 2026. Phase 1. Status: awaiting approval.
Tier A: one player, one laptop, no stored data, no network. Line numbers refer to `match card game demo/src/MatchCards.java` at b516f15 unless stated. Behavior below comes from reading the code. The game was not played in this phase.

## 1. User flows, ordered by value

| # | Flow | Actor | Trigger | Steps | Result |
|---|---|---|---|---|---|
| F1 | Play one game to a win | Player | Starts the program | Window opens with 20 cards face up (10 pairs). After 1.5 s they turn face down. Player clicks one card, then a second. A pair stays face up | All 10 pairs face up. A dialog shows time in seconds and the error count. The clock stops (line 259) |
| F2 | Build and start from the README | Visitor with only a JDK | Clones the repository | Reads the README, runs the documented commands | The game window opens. Today there are no commands, so this flow does not exist |
| F3 | New game after a win | Player | Presses Restart after the win dialog | Cards reshuffle, show for 1.5 s, turn face down. Errors and time reset to 0 | A fresh game whose clock counts up. Today the clock stays at 0s (lines 107 to 125 never restart it) |
| F4 | A wrong guess | Player | Flips two cards that differ | Error count goes up by one. Both stay visible for 1.5 s, then turn face down. Clicks are ignored meanwhile | Player can pick again |
| F5 | Restart in the middle of a game | Player | Presses Restart before winning | Same as F3, but the clock was never stopped | A fresh game, clock from 0 |
| F6 | Check the rules without a window | Developer | Runs the test command | Tests deal, shuffle, pick, match, mismatch, win, restart and clock on a rules class | All pass. Today not possible: rules read state from button icons (lines 160, 171, 252) |
| F7 | Play with the keyboard only | Player who does not use a mouse | Starts the program | Moves focus between cards, flips with Space or Enter, hears or sees a card name | Same result as F1. Today not possible: nothing is focusable (lines 105, 152) and cards have no accessible name |

## 2. Quality requirements
"Target" means no measurement exists yet and the user must confirm the number. "Evidence" names what it rests on.

| Quality | Number | Basis |
|---|---|---|
| Response time, card click to face shown | p95 at most 100 ms on the author's Mac, over 50 clicks | Target. 100 ms is a common rule of thumb for a reaction feeling instant. Not measured. The click handler does a list lookup and one `setIcon` (lines 163 to 168), with images scaled once at start (lines 198 to 201), so the budget looks loose |
| Response time, launch to playable window | p95 at most 3 s over 5 cold starts on the author's Mac | Target. Not measured |
| Preview and wrong guess display time | 1500 ms, within 100 ms | Evidence: one timer of 1500 ms at line 134 serves both |
| Clock accuracy | Shown seconds within 1 s of wall time after 300 s of play, in every game including after a restart | Target. Fails today after a win and restart (F3) |
| Shuffle fairness | Every arrangement equally likely. Test: with a fixed seed, over 100,000 shuffles, how often each card name lands in each of the 20 places passes a chi square test at the 1 percent level | Fails today, shown below |
| Availability | Not applicable. A local program has no uptime. It is available when the player starts it | Tier A |
| Concurrency | One player, one window. Not applicable beyond UI responsiveness: all game state must be touched only on the Swing event thread | Evidence: all timers are Swing timers (lines 134, 139). `App.java` line 3 builds the window off that thread, a defect |
| Network latency | Not applicable. No network calls found (search for file, socket, URL and process calls on 20 Nov 2026 found only the two image lookups at lines 193 and 210) | Code search |
| Data volume | 20 cards, 11 images, 156 KB on disk. Nothing is saved between runs | Evidence: `du` on `src/img`, 20 Nov 2026 |
| Monthly cost ceiling | 0 dollars | Target. No hosted service exists or is planned |
| Recovery time | A fresh clone builds and starts in at most 5 minutes on a machine with a JDK | Target. The GitHub remote is the only backup, and that is enough at Tier A |
| Acceptable data loss | A game in progress is lost if the window closes. Accepted, since no state is meant to persist | Tier A |

### Why the shuffle fails today
Lines 222 to 229 swap each of the 20 places with a place drawn from all 20. That gives 20 to the power 20 equally likely sequences of draws. There are 20 factorial arrangements. 20 factorial is divisible by 19, and 20 to the power 20 is not, so the sequences cannot split evenly across the arrangements. Some orders must come up more often than others. `Collections.shuffle` with an injected `Random` does not have this flaw. This is a proof from the code, not a measurement.

## 3. Data classification

| Data | Class | Where stored | Who can read it |
|---|---|---|---|
| Source code (`App.java`, `MatchCards.java`) | Public | Git repository on GitHub and local clones | Anyone |
| Card images in `src/img`, `out/`, both `img.zip` | Public, rights unknown | Same | Anyone can view. Right to reuse is unknown, see section 4 |
| `img.zip` extras: `.DS_Store`, `__MACOSX` entries | Internal | Inside both archives in the repository | Anyone. Contents not opened. They are machine metadata with no use to the game |
| Compiled classes in `out/` | Public | Repository | Anyone |
| IntelliJ `.iml` file | Public | Repository | Anyone |
| Commit author name and email | Personal | Git history on GitHub | Anyone. Not printed here. Rewriting history is out of scope |
| Game state: board order, picks, errors, seconds | Internal | Process memory only, gone on exit | The player at the screen |
| Secrets | None found or needed | Phase 0 scan of tracked text files found none | Not applicable |

## 4. Licenses, rights and terms
Checked on 20 Nov 2026.

| Item | Finding | Link |
|---|---|---|
| JDK (Eclipse Temurin 21.0.6 on this machine) and Swing | GPL version 2 with the Classpath Exception. The FAQ says you may freely use, modify and share it under the included licenses | https://adoptium.net/docs/faq/ |
| Other libraries | None. The code imports only `java.awt`, `java.util` and `javax.swing` (lines 1, 3, 4) | Code |
| Outside APIs and datasets | None | Code search above |
| Card images (11 JPG files) | Source and license unknown. No author, copyright or source in the files. Sizes vary from 180 by 252 to 197 by 276 pixels, the styles differ, and every file carries the comment `Compressed by jpeg-recompress`. The back looks like a photo of a physical card. This suggests the files were gathered from different web pages, which is not verified. No macOS download source is recorded (`mdls` WhereFroms empty) | Local check with `file`, `strings`, `mdls` |
| Replacement art candidate: Kenney Playing Cards Pack | Listed under CC0 1.0. The page text does not list which cards are included | https://kenney.nl/assets/playing-cards-pack |
| Replacement art candidate: Byron Knoll vector playing cards | Listed as CC0 on OpenGameArt. Commenters there question the source of the claim and say the Ace of Spades is by another artist with no public domain statement | https://opengameart.org/content/playing-cards-vector-png |
| This repository | No LICENSE file. With no license, others generally have no permission to use, modify or share the code | https://choosealicense.com/no-permission/ |
| Course: CCNY CSc 22100 Software Design Laboratory | The department syllabus page (last updated 19 Jun 2026) requires a small, team based application project (paraphrased). It says nothing about publishing student work | https://www.ccny.cuny.edu/compsci/csc-221-syllabus |
| CCNY academic integrity | Lists cheating, plagiarism, unauthorized collaboration and submitting work done by someone else as violations. Nothing found on publishing your own past work | https://www.ccny.cuny.edu/about/integrity |
| CUNY Policy on Academic Integrity | Amended and replaced 27 Jun 2022. The web page has no clause on publishing your own coursework. The full PDF could not be read here, so the full text is unchecked | https://www.law.cuny.edu/university-policies-legal-notices/cuny-policy-on-academic-integrity/ |

What this means: no university rule found forbids publishing your own past work, but a section syllabus can, and a future student copying it would be the violation the policy names. Your instructor's syllabus was not found and is the source that decides. If the game was a team project, teammates share the authorship (assumption 12).
