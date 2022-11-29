package com.example.movieslist.Listeners;

import com.example.movieslist.Models.WatchProviders.WatchProvidersApiResponse;

public interface OnWatchProvidersApiListeners
{
    void onResponse(WatchProvidersApiResponse response);
    void onError(String message);
}
