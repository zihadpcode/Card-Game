# 0002: Card art with unknown rights

Date: 20 Nov 2026. Status: accepted 22 Nov 2026 (Phase 1 default approved). Updated 23 Nov 2026.

## Context
The 11 card images have no source, author or license recorded (docs/requirements.md section 4). The repository is public. The brief's default is to replace them with public domain art if the source stays unknown. Replacing files needs your yes.

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| Keep the images and add a credit | Minutes, if you know the source | Only as good as the source's license. Unknown today | Yes | You can name the source and it allows reuse |
| Replace with the Kenney Playing Cards Pack (CC0 1.0, checked 20 Nov 2026) | About 1 hour: pick 11 files, rename, update the name list at lines 24 to 35 | The look changes. Sizes may differ, so scaling must be checked | Yes, history keeps the old files | Source unknown. Recommended |
| Replace with Byron Knoll's vector cards (listed CC0) | About 1 hour | The public domain claim is questioned on its own page, and the Ace of Spades has a different artist | Yes | Only if Kenney lacks a card you need |
| Draw the cards in code with Java2D (rank and suit text) | 2 to 3 hours | More code to test. Plainer look | Yes | You want zero image assets |

## Recommendation
Replace with the Kenney pack, unless you can name a source that allows reuse. Add `CREDITS.md` beside the images either way. What would change this: a verified source for the current images.

## Update, 23 Nov 2026 (Phase 2)
The user approved the Phase 1 defaults on 22 Nov 2026, so the Kenney pack is the chosen option. The pack page, read again on 23 Nov 2026, still says CC0 but lists no file types or card names. Before any file is replaced, slice S1 opens the download and checks that faces for the ten names and a back exist (assumption 14). If they do not, the fallback is Java2D drawn cards, not the Byron Knoll set. Downloading the pack is an outside action and replacing the files needs the user's yes at that step.
