package com.animatinator.crossword.generate;

import com.animatinator.crossword.board.Board;

import java.util.List;

class BoardGenerator {
    Board generateBoard(List<String> possibleWords) {
        sortByLengthDescending(possibleWords);

        return new Board();
    }

    private void sortByLengthDescending(List<String> possibleWords) {
        possibleWords.sort((String s1, String s2) -> s2.length() - s1.length());
    }
}
