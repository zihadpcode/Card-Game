# Assumptions: Card-Game

| # | Assumption | Basis | How to check |
|---|---|---|---|
| 1 | It is a portfolio piece for one desktop player | Public repository, two line README | Ask the author |
| 2 | It runs when started from IntelliJ | Tracked class files under `out/` | Play it once |
| 3 | The clock defect is real | Code reading only: start at line 143, stop at line 259, no start in lines 107 to 125 | Win, press restart, watch the clock |
| 4 | Java 21 is an acceptable minimum | It is the JDK on this machine | Ask the author |
| 5 | The image files are what their names say and `img.zip` holds the same files | Names only. The archive was not opened | Partly checked 20 Nov 2026: `unzip -l` lists the same 11 names plus `.DS_Store` and `__MACOSX` entries. Contents not compared byte for byte |

## Defaults taken for the Phase 0 open questions
Phase 0 was approved on 19 Nov 2026. The user said to use the defaults proposed in the brief for any open question. Each line below is a default, user to confirm.

| # | Open question | Default taken | Source of the default | Status |
|---|---|---|---|---|
| 6 | Where did the card images come from, and under what license? | Source unknown, so treat the images as unlicensed. Replace them with public domain or CC0 card art. Replacing files still needs your yes at that step. See decision 0002 | docs/brief.md, riskiest assumption | default, user to confirm |
| 7 | Is this a tutorial follow along? | No. The README credits no tutorial. The work is from CSC 22100 at City College (stated in the Phase 1 task) | docs/brief.md open questions; no tutorial named | default, user to confirm |
| 8 | May `out/` and both `img.zip` files be removed from the repository? | Yes, remove from tracking and add `.gitignore`. Git history keeps them. The actual removal still needs your yes at that step | docs/revamp-plan.md, proposed restructuring | default, user to confirm |
| 9 | Plain `javac` script, Gradle or Maven? | Plain `javac` and `java` commands in a script, no build tool | docs/risks.md risk 5, mitigation | default, user to confirm |
| 10 | Which license for the code? | The brief proposed no default. Proposed now: MIT for the code only, images excluded until their rights are known. Held until course policy is confirmed (assumption 11) | New in Phase 1 | default, user to confirm |

## New in Phase 1
| # | Assumption | Basis | How to check |
|---|---|---|---|
| 11 | Publishing the game is allowed by the CSC 22100 section it was written for | No CCNY or CUNY rule found on 20 Nov 2026 that forbids publishing your own past coursework. The instructor's own syllabus was not found and may differ | Check your section's syllabus or ask the instructor |
| 12 | The game was individual work, not a team project | The repository has one author. The CCNY CSC 221 syllabus page says the course requires a team based project | Ask the author |
| 13 | The response time and startup numbers in docs/requirements.md fit one player on your Mac | No measurement yet. They are targets | Measure in Phase 5 |
