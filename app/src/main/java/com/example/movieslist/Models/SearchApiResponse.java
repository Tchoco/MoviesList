package com.example.movieslist.Models;

import java.util.List;

public class SearchApiResponse
{
    List<SearchArrayObjectsForSearchMovies> results = null;

    public List<SearchArrayObjectsForSearchMovies> getResults()
    {
        return results;
    }

    public void setResults(List<SearchArrayObjectsForSearchMovies> results)
    {
        this.results = results;
    }
}
