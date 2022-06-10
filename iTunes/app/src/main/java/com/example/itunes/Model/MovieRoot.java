package com.example.itunes.Model;

import java.util.List;

public class MovieRoot {

    public int resultCount;
    private List<MovieResult> results = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<MovieResult> getResults() {
        return results;
    }

    public void setResults(List<MovieResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MovieRoot{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
