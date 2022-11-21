package com.example.movieslist.Listeners;

import com.example.movieslist.Models.SearchApiResponse;

public interface OnSearchMoviesApiListeners
{
    void onResponse(SearchApiResponse response);
    void onError(String message);
}
