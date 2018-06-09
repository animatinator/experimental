package com.animatinator.index.files;

public class File {
    private final String name;
    private final String contents;

    public File(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public String getContents() {
        return contents;
    }
}
