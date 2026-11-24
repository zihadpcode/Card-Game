# 0007: Measure play time from a start reading

Date: 23 Nov 2026. Status: proposed.

## Context
The clock counts one second per Swing timer tick (lines 139 to 143). Restart after a win never starts it again, so it stays at 0s (Q4, A3 fail). The preview counts as play (defect 4). Late ticks would be lost time (risk 15).

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Add `gameTimer.start()` to Restart | 5 minutes | Fixes F3 only; untestable; drift remains | Yes | A one line hotfix only |
| `Game` keeps start and stop readings from an injected `LongSupplier` of nanoseconds (`System::nanoTime` in play); a 250 ms Swing timer only repaints the label | 1 hour | Reported times shrink by the 1.5 s preview | Yes | Recommended |
| `java.time.Clock` and `Instant` | 1 hour | Wall time jumps with system clock changes | Yes | Dates are ever shown |

## Decision
Injected monotonic clock. The clock starts in `endPreview` and stops at the win. Starting after the preview needs the user's agreement (assumption 15).

## Consequences
Shown seconds lag real time by at most the 250 ms repaint period. Tests advance a fake clock by 300 s and check the shown value.
