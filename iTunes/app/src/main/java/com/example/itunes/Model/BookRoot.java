package com.example.itunes.Model;

import java.util.List;

public class BookRoot {

    public int resultCount;
    private List<BookResult> results = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<BookResult> getResults() {
        return results;
    }

    public void setResults(List<BookResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BookRoot{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
