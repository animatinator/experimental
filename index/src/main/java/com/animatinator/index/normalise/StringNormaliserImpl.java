package com.animatinator.index.normalise;

public class StringNormaliserImpl implements StringNormaliser {
    public StringNormaliserImpl() {}

    @Override
    public String normaliseString(String input) {
        input = input.trim();
        input = input.replaceAll("[^a-zA-Z]", "");
        return input.toLowerCase();
    }
}
