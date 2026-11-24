# 0005: Plain javac scripts and a standard layout

Date: 23 Nov 2026. Status: proposed.

## Context
No build file exists; the run path is an IntelliJ `.iml` (risk 3). The folder name has spaces. `out/` and two copies of `img.zip` are tracked. Assumption 9 (plain `javac`, no build tool) and assumption 8 (remove `out/` and both zips from tracking) were accepted on 22 Nov 2026. Compiling both files took 0.42 s on 23 Nov 2026.

## Options
| Option | Cost | Risk | Reversible | Pick when |
|---|---|---|---|---|
| `build.sh`, `run.sh`, `test.sh` calling `javac` and `java`; same commands in the README | 1 hour | Windows users follow the README commands | Yes | Recommended |
| Gradle with the wrapper | 1 to 2 hours plus first run download | Wrapper jar, network on fresh clone | Yes | Libraries arrive |
| Maven | 1 to 2 hours plus downloads | Heavy for two files | Yes | Maven already known and libraries arrive |

## Decision
Scripts. Layout: `src/main/java/matchcards/`, `src/main/resources/img/` with `CREDITS.md`, `src/test/java/matchcards/`. Move with `git mv` so history follows. Add `.gitignore` for `out/`, `*.class`, `.DS_Store`, `.idea/` and `*.iml`. Remove `out/` and both `img.zip` files from tracking; history keeps them. A runnable jar from the JDK `jar` tool is optional and only if asked.

## Consequences
A fresh clone needs only a JDK. The removal is a step that needs the user's yes when it happens.
