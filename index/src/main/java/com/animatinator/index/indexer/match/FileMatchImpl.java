package com.animatinator.index.indexer.match;

public class FileMatchImpl implements FileMatch {
    private final String fileName;
    private final String fileContents;
    private final int numMatches;

    public FileMatchImpl(String fileName, String fileContents, int numMatches) {
        this.fileName = fileName;
        this.fileContents = fileContents;
        this.numMatches = numMatches;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getFileContents() {
        return fileContents;
    }

    @Override
    public int getNumMatches() {
        return numMatches;
    }
}
