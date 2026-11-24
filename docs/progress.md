# Progress: Card-Game

| Phase | State | Date | Note |
|---|---|---|---|
| 0 Frame | Approved | 19 Nov 2026 | Brief, audit and plan on branch `revamp/phase-0`. Open questions taken as defaults, see docs/assumptions.md 6 to 10 |
| 1 Requirements and risk | Approved | 22 Nov 2026 | Approved with every default assumption as written. See docs/assumptions.md |
| 2 Design | In progress, awaiting approval | 23 Nov 2026 | Documents only. No code changed |
| 3 to 8 | Not started | | |

## Done in Phase 0
* Worktree at `~/projects/revamp/card-game` from `origin/main` at b516f15.
* Compile check with `javac -Xlint:all` on Java 21: exit 0, no warnings.
* PROJECT_BRIEF.md, docs/brief.md, docs/current-state.md, docs/revamp-plan.md, assumptions, risks, decision 0001.

## Phase 1 steps
| Step | Status | File |
|---|---|---|
| 0. Mark Phase 0 approved, log defaults for open questions | Done | docs/progress.md, docs/assumptions.md |
| 1a. User flows (7) | Done | docs/requirements.md |
| 1b. Quality numbers | Done, mostly targets to confirm | docs/requirements.md |
| 1c. Data classification | Done | docs/requirements.md |
| 1d. Licenses, image rights, course policy | Done, image source still unknown | docs/requirements.md |
| 2. Threat model, short Tier A version | Done | docs/threat-model.md |
| 3. Risk register rebuilt | Done | docs/risks.md |
| 4. Acceptance tests | Done | docs/acceptance-tests.md |
| 5. Phase report | Done | below |
| 6. Decision on card art | Accepted 22 Nov 2026 | docs/decisions/0002-card-art.md |

Exit gate: requirements, risk register and acceptance tests approved. Approved 22 Nov 2026.

## Phase 1 report
What was done: seven user flows, twelve quality lines with numbers or a reason they do not apply, a data table, a license and policy table with links, a short threat model, a register of 16 risks, and 17 acceptance tests with status.

How it was checked, on 20 Nov 2026:
* `javac -Xlint:all` again, output written to a scratch folder outside the worktree: exit 0.
* `du` on `src/img`: 156 KB. `unzip -l` on `src/img.zip`: same 11 names plus `.DS_Store` and `__MACOSX` entries.
* `file`, `strings` and `mdls` on each image: no author, license or download source.
* Code search for file, socket, URL and process calls: only the two image lookups at lines 193 and 210.
* Web pages for the JDK license, CC0 card art, the CCNY syllabus and integrity pages and the CUNY policy, links in docs/requirements.md.
* The shuffle bias is shown by a counting proof, not by running a test.

What remains uncertain:
* Where the images came from. Nothing in the files says.
* Your section's own syllabus rules on publishing, and whether this was team work.
* Every timing number is a target. The game was not started or played, so the clock defect is still from code reading.
* The full CUNY policy PDF could not be read here, only its web page.

## Questions for you
1. Where did you get the 11 card images, and may I replace them with the CC0 Kenney pack (decision 0002)?
2. Did your CSC 22100 section say anything about posting your work publicly, and was this game built alone or with a team?
3. In flow F3, why does the clock stay at 0s after a win and Restart, and which single line would fix it?
4. The shuffle swaps each place with any of the 20 places. Why does that make some layouts more likely than others?
5. Are 100 ms per click, 3 s to start and a 0 dollar monthly cost the right numbers for you, or would you change any?

## Phase 2 steps
| Step | Status | File |
|---|---|---|
| 0. Mark Phase 1 approved; Phase 1 defaults accepted 2026-11-22 | Done | docs/progress.md, docs/assumptions.md |
| 1a. Decisions with options tables, keep or change | Done | docs/design.md (a) |
| 1b. Data model | Done | docs/design.md (b) |
| 1c. Component contracts | Done | docs/design.md (c) |
| 1d. p95 budget split | Done, estimates only | docs/design.md (d) |
| 1e. Environments and config | Done | docs/design.md (e) |
| 1f. Cost now and at ten times | Done | docs/design.md (f) |
| 1g. Architecture diagram | Done | docs/design.md (g) |
| 1h. Walking skeleton plan | Done | docs/design.md (h) |
| 2. Decision notes 0003 to 0010; 0002 marked accepted | Done | docs/decisions/ |
| 3. Risks and revamp plan updated | Done | docs/risks.md, docs/revamp-plan.md |
| 4. Phase report | Done | below |

Exit gate: design approved, with every major decision recorded. Awaiting approval.

## Phase 2 report
What was decided:
* Keep Java 21 and Swing (0003). Keep class path image loading and images scaled once at start.
* Change: split rules into a `Game` class with no Swing imports, injected `Random` and clock, window built through `invokeLater` (0004).
* Change: plain `javac` scripts and a `src/main/java/matchcards` layout; `out/` and both zips leave tracking (0005).
* Change: a small test runner with no library (0006).
* Change: play time from a monotonic clock reading, starting after the preview (0007).
* Change: focusable `JButton` cards, arrow keys, accessible names (0008).
* Change: Kenney CC0 art (0002, accepted). MIT for code once course policy is confirmed (0010).
* Does not apply: hosting, data store, authentication, background work. CI on GitHub Actions (0009).
* Cost 0 dollars now and at ten times. First limit is screen size, not money.

How it was checked, on 23 Nov 2026:
* `javac -Xlint:all` on both files into the scratch folder: exit 0, 0.42 s real time, Java 21.0.6.
* A Python model of the shuffle loop at lines 222 to 229, 100,000 runs with a fixed seed: chi square 6,583 on the 10 by 20 table, against a 1 percent cutoff near 217. Fisher Yates in the same model: 193. The Java code itself was not run.
* GitHub Actions billing page: standard hosted runners free in public repositories.
* Kenney pack page: CC0 stated; contents not listed.

What remains uncertain:
* Whether the Kenney pack has all ten faces (assumption 14).
* Every timing budget in design (d) is an estimate. The game was still not played.
* The 217 cutoff is an approximation; the test in slice S2 should compute or hard code the exact value.
* Course policy and sole authorship (assumptions 11 and 12) still gate the LICENSE.

## Questions for you
1. Why does `Game` take a `Random` and a clock as constructor arguments instead of calling `Math.random()` and counting timer ticks?
2. Should the clock start when the cards turn face down, so the 1.5 s preview no longer counts as play time?
3. `flip` returns `IGNORED` for a click on a face up card but throws for place 25. Why treat those two cases differently?
4. What does the test runner self test prove, and what would go wrong without it?
5. May slice S1 download the Kenney pack and replace the 11 images, and may step K2 remove `out/` and both zips from tracking?

## Next step
Approve or correct docs/design.md and decisions 0003 to 0010, and answer the Phase 2 questions above. Then Phase 3 starts at step K1.
