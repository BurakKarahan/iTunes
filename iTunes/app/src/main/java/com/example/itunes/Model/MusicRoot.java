package com.example.itunes.Model;

import java.util.List;

public class MusicRoot {

    public int resultCount;
    private List<MusicResult> results = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<MusicResult> getResults() {
        return results;
    }

    public void setResults(List<MusicResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MusicRoot{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
