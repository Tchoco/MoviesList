package com.example.movieslist.Listeners;

import com.example.movieslist.Models.DetailsApiResponse;

public interface OnDetailsApiListeners {
    void onResponse(DetailsApiResponse response);
    void onError(String message);
}
