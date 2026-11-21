# Acceptance tests: Card-Game

Date: 20 Nov 2026. "Code reading" means the status comes from reading `match card game demo/src/MatchCards.java` at b516f15. The game was not played in Phase 1, since it opens a window on your desktop.

## Per user flow
| # | Flow | Test in plain language | How it will be checked | Status today |
|---|---|---|---|---|
| A1 | F1 | Matching all 10 pairs shows a win message with the time and error count, and the clock stops | Unit test on the rules class; one manual play | Not yet testable as a unit test. Code reading says it works (lines 248 to 261) |
| A2 | F2 | On a fresh clone with only JDK 21, the README commands compile and open the window | Fresh clone into a temporary folder, run the commands | Fails today: the README has no commands |
| A3 | F3 | After a win and Restart, the clock counts up from 0 | Unit test with an injected clock; one manual play | Fails today, by code reading: no `gameTimer.start()` in lines 107 to 125 |
| A4 | F4 | Two different cards add one error and turn face down after 1.5 s; clicks in that time change nothing | Unit test on the rules class | Not yet testable. Code reading says it works (lines 171 to 174, 155) |
| A5 | F5 | Restart mid game resets errors and time to 0 and deals a new order | Unit test; manual play | Not yet testable. Code reading says it works, except the preview can be shorter if a wrong pair is showing (risk 9) |
| A6 | F6 | One documented command runs at least 10 rule tests and all pass | Run the command | Fails today: no tests exist |
| A7 | F7 | The game can be won with the keyboard alone, and VoiceOver reads a name for each card | Manual play with keyboard and VoiceOver | Fails today: `setFocusable(false)` at lines 105 and 152, no accessible names |

## Per quality number
| # | Quality | Test | How it will be checked | Status today |
|---|---|---|---|---|
| Q1 | Click to face shown, p95 at most 100 ms | Time 50 clicks from event to repaint | Timing log in a test build | Not yet testable |
| Q2 | Launch to playable, p95 at most 3 s | 5 cold starts, timed | Stopwatch or timing log | Not yet testable (not run) |
| Q3 | Preview and wrong pair display 1500 ms, within 100 ms | Assert the configured delay; time it once by hand | Unit test plus manual | Passes on configuration: 1500 at line 134. Real timing not measured |
| Q4 | Clock within 1 s of wall time after 300 s, every game | Injected clock in unit tests; one 5 minute manual game | Unit and manual | Fails today after a win and restart (see A3) |
| Q5 | Fair shuffle | Fixed seed, 100,000 shuffles, chi square at 1 percent on place by card counts | Unit test | Fails today, by proof in docs/requirements.md section 2 |
| Q6 | All state touched on the Swing event thread | Code review; startup through `invokeLater` | Review | Fails today: `App.java` line 3 |
| Q7 | Data volume: nothing written to disk | Search the code for file writes | Code search | Passes today: search on 20 Nov 2026 found no file writes |
| Q8 | Cost 0 dollars a month | No paid service in the design | Review at Phase 2 | Passes today: none exists |
| Q9 | Fresh clone builds and starts in at most 5 minutes | Same as A2, timed | Timed run | Fails today (see A2) |
| Q10 | Image and course rights recorded | `CREDITS.md` names a source and license for every image; course policy noted | Review | Fails today: no credits; section policy unconfirmed |

Availability, network latency and concurrency beyond the event thread rule have no test, since they do not apply at Tier A.
