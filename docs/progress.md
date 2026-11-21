# Progress: Card-Game

| Phase | State | Date | Note |
|---|---|---|---|
| 0 Frame | Approved | 19 Nov 2026 | Brief, audit and plan on branch `revamp/phase-0`. Open questions taken as defaults, see docs/assumptions.md 6 to 10 |
| 1 Requirements and risk | In progress, awaiting approval | 20 Nov 2026 | Documents only. No code changed |
| 2 to 8 | Not started | | |

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
| 6. Decision on card art | Proposed | docs/decisions/0002-card-art.md |

Exit gate: requirements, risk register and acceptance tests approved. Awaiting approval.

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

## Next step
Approve or correct docs/requirements.md, docs/risks.md and docs/acceptance-tests.md, and answer the questions above.
