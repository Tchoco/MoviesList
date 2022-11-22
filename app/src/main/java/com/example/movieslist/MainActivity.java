package com.example.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movieslist.Adapters.HomeRecyclerAdapter;
import com.example.movieslist.Listeners.OnMovieClickListeners;
import com.example.movieslist.Listeners.OnSearchMoviesApiListeners;
import com.example.movieslist.Models.SearchApiResponse;

public class MainActivity extends AppCompatActivity implements  OnMovieClickListeners {

    SearchView search_view;
    RecyclerView home_recycler_view;
    Request_Manager manager;
    HomeRecyclerAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        home_recycler_view = findViewById(R.id.home_recycler_view);

        dialog = new ProgressDialog(this);
        manager = new Request_Manager(this);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Patientez...");
                dialog.show();
                manager.searchMovies(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnSearchMoviesApiListeners listener = new OnSearchMoviesApiListeners() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(MainActivity.this, "les données ne sont pas disponibles", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            return;
        }
    };

    private void showResult(SearchApiResponse response) {
        home_recycler_view.setHasFixedSize(true);
        home_recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        adapter = new HomeRecyclerAdapter(this, response.getResults(), this);
        home_recycler_view.setAdapter(adapter);
    }

    @Override
    public void onMovieClicked(String id)
    {
        startActivity(new Intent(MainActivity.this,MovieDetailsActivity.class)
                .putExtra("data", id));
    }
}