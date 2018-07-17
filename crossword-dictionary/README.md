# Crossword word set generator

This module generates a list of words usable in a crossword-style puzzle in
which all words must be formable from the same finite set of letters (probably
around six or seven).

It takes an arbitrary dictionary file as input and can generate puzzles with
configurable minimum word length, maximum word length and maximum number of
words.

## Usage
```
>> Main /data/basicdict.txt

PuzzleConfiguration{letter set={ e, e, e, g, i, l, l, n, r, v, }; words=[level, erin, eileen, eine, grille, gen]}
```