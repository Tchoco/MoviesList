package com.example.movieslist.Listeners;

import com.example.movieslist.Models.CastMembers;
import com.example.movieslist.Models.WatchProvidersApiResponse;

public interface OnWatchProvidersApiListeners
{
    void onResponse(WatchProvidersApiResponse response);
    void onError(String message);
}
