package com.example.movieslist.Models;

import java.util.List;

public class WatchProvidersApiResponse
{
    List<ArrayWatchProvidersFr> results;

    public List<ArrayWatchProvidersFr> getResults() {
        return results;
    }

    public void setResults(List<ArrayWatchProvidersFr> results) {
        this.results = results;
    }
}
