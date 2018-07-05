package com.animatinator.crossword.generate;

import java.util.HashMap;
import java.util.Map;

public class BoardGenerationFlags {
    private final Map<Integer, Boolean> flagValues = new HashMap<>();

    public BoardGenerationFlags() {
        initialiseFlagsToFalse();
        setInitialDefaults();
    }

    private void initialiseFlagsToFalse() {
        for (BoardGenerationFlagConstant value : BoardGenerationFlagConstant.values()) {
            setFlag(value, false);
        }
    }

    private void setInitialDefaults() {
        setFlag(BoardGenerationFlagConstant.RANDOM_INITIAL_ORIENTATION, true);
    }

    void setFlag(BoardGenerationFlagConstant flag, boolean value) {
        flagValues.put(flag.ordinal(), value);
    }

    boolean getFlag(BoardGenerationFlagConstant flag) {
        return flagValues.get(flag.ordinal());
    }
}
