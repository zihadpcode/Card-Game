# 0008: Keyboard play and screen reader names

Date: 23 Nov 2026. Status: proposed.

## Context
Cards and Restart call `setFocusable(false)` (lines 105, 152) and carry no accessible name, so F7 and A7 fail (risk 10).

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Keep `JButton` cards, make them focusable, arrow key bindings on the board panel, Space or Enter flips, accessible name per card | 1 to 2 hours | Focus ring look varies by look and feel | Yes | Recommended |
| Custom painted board with a key listener | 3 to 4 hours | Loses built in button access support | Yes | Never at this size |
| Mouse only, documented | None | F7 fails | Yes | The author drops F7 |

## Decision
Keep `JButton`. Names read "Card 7, face down", "Card 7, Ace of clubs" or "Card 7, matched, Ace of clubs". Arrow keys wrap within the 4 by 5 grid. Checked by hand with VoiceOver, since screen reader output cannot be asserted headless.
