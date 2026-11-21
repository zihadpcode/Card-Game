# Threat model: Card-Game (Tier A, short version)

Date: 20 Nov 2026. Line numbers refer to `match card game demo/src/MatchCards.java` at b516f15.

* Assets: the source code and the author's reputation as a portfolio piece; the author's standing with the course; the right to publish the card images.
* Actors: the author; visitors who clone or fork the repository; a future CSC 22100 student who might copy it; the owners of the card images.
* Entry points: mouse clicks on 20 cards and one button; image files read from the class path (lines 193 and 210); the repository itself (anyone can read it).
* Trust boundaries: the class path. Whoever controls the files next to the program controls the images it shows. There is no network, file write or account boundary.
* Latency, availability and concurrency: not applicable beyond UI responsiveness. All state lives on the Swing event thread, except the window build in `App.java` line 3.

## Top abuse cases

| # | Abuse case | Control today | Gap |
|---|---|---|---|
| 1 | An image owner asks for removal, or a host takes the repository down, because the images are used without permission | None. No credits or license file | Credit or replace the art (decision 0002) |
| 2 | A student copies the repository and submits it in a later term | None | Confirm section policy. A README note that the work is public and dated |
| 3 | Someone reuses the code with no license and the author cannot say on what terms | None. No LICENSE | Pick a license after assumption 11 is checked |
| 4 | A replaced or corrupt image on the class path stops the game | Missing images fail at start with the path in the message (lines 194 to 196, 211 to 213). A corrupt file is not checked | Low damage at Tier A. Accepted |
| 5 | Rapid clicks during the 1.5 s delay change the score | Clicks ignored while cards hide or a pair is showing (lines 155, 160, 165) | Restart pressed while a wrong pair shows reuses the running timer (line 124), so that preview can be shorter than 1.5 s. Not tested |
