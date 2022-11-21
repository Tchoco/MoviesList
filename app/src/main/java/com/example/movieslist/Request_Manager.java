package com.example.movieslist;

import android.content.Context;
import android.widget.Toast;

import com.example.movieslist.Listeners.OnSearchMoviesApiListeners;
import com.example.movieslist.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class Request_Manager
{
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Request_Manager(Context context)
    {
        this.context = context;
    }

    public void searchMovies(OnSearchMoviesApiListeners listener, String movie_name)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        getMovies getMovies = retrofit.create(Request_Manager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(key,movie_name,page);
        System.out.print(call);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public interface getMovies
    {
        @Headers({
                "Accept: application/json",
        })
        //@GET("search/movie?api_key=c46870fff6c94f30951b91811ae9238a&language=en-US&query={movie_name}&page=1&include_adult=false")
        @GET("search/movie")
        Call<SearchApiResponse> callMovies(
                //@Path("movie_name") String movie_name
                @Query("api_key") String key,
                @Query("query") String movie_name,
                @Query("page") String page
        );
    }
}
