# 0004: Split the game rules from the Swing view

Date: 23 Nov 2026. Status: proposed.

## Context
Rules read state back from button icons (lines 160, 171, 252), so no rule can be tested without a window (risk 6, flow F6, test A6 fail). The shuffle at lines 222 to 229 is biased (risk 5). On 23 Nov 2026 a Python model of that loop, 100,000 shuffles with a fixed seed, gave a chi square of 6,583 on the 10 names by 20 places table, against about 217 for the 1 percent cutoff at 171 degrees of freedom (Wilson Hilferty estimate). The same model with a Fisher Yates shuffle gave 193. This is a model of the Java code, not the Java code itself.

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Keep one class | None | F6 and Q5 stay failing | Yes | Never, given F6 |
| `Game` class with no Swing imports, injected `Random` and clock; view draws from it | 2 to 3 hours | Play could change during the move (risk 7) | Yes, one revert | Recommended |
| Full model, view and controller with events | 4 to 5 hours | More structure than the game needs | Yes | A second view is wanted |

## Decision
Change. Add `Game`, `CardImages`, `MatchCardsView` and a thin `App` in package `matchcards`, with the contracts in docs/design.md section (c). `Game` uses `Collections.shuffle(list, rng)`. The window is built inside `SwingUtilities.invokeLater` (Q6).

## Consequences
The view holds no game state. A headless test can drive every rule. Tests that pin today's rules come before the view is switched over.
