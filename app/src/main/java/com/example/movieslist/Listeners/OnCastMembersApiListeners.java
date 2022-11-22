package com.example.movieslist.Listeners;

import com.example.movieslist.Models.CastMembers;
import com.example.movieslist.Models.DetailsApiResponse;

public interface OnCastMembersApiListeners
{
    void onResponse(CastMembers response);
    void onError(String message);
}
