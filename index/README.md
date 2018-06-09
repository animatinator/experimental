# Inverted indices

This module generates inverted indices for text files and allows you to search based on the result.

Usage: pass in a series of filenames to construct the index. Then, enter a search query to search the resulting index,
or enter 'q' to quit.

Basic files for testing are provided in the `data` directory.

## Sample session
```
Loading files...
Indexing...
Ready.

Enter a word to search for, or 'q' to quit:
the
Searching for 'the...
Found 3 matches:
File: cars.txt
	Matches: 3
	Content:
		The swift car speeds past the slow cars in the summer.
File: jump.txt
	Matches: 2
	Content:
		The lazy dog jumps over the car.
File: foxes.txt
	Matches: 2
	Content:
		The quick brown fox jumps over the lazy dog.

Enter a word to search for, or 'q' to quit:
fox
Searching for 'fox...
Found 1 matches:
File: foxes.txt
	Matches: 1
	Content:
		The quick brown fox jumps over the lazy dog.

Enter a word to search for, or 'q' to quit:
car
Searching for 'car...
Found 2 matches:
File: cars.txt
	Matches: 1
	Content:
		The swift car speeds past the slow cars in the summer.
File: jump.txt
	Matches: 1
	Content:
		The lazy dog jumps over the car.

Enter a word to search for, or 'q' to quit:
q
Goodbye!
```

## Future work
* Highlight matches in the content of matching files
* Handle plurals, verb conjugations etc.