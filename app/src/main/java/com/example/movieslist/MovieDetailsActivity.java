package com.example.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieslist.Adapters.CastRecylerAdapter;
import com.example.movieslist.Listeners.OnCastMembersApiListeners;
import com.example.movieslist.Listeners.OnDetailsApiListeners;
import com.example.movieslist.Listeners.OnWatchProvidersApiListeners;
import com.example.movieslist.Models.CastMembers;
import com.example.movieslist.Models.DetailsApiResponse;
import com.example.movieslist.Models.WatchProvidersApiResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView textView_movie_name, textView_movie_released, textView_movie_runtime,textView_movie_rating, textView_movie_votes, textView_synopsis;
    ImageView imageView_movie_poster, Imageview_WatchProviders;
    RecyclerView recycler_movie_cast;
    CastRecylerAdapter adapter;
    Request_Manager manager ;
    Request_Manager manager2,manager3 ;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        textView_synopsis       = findViewById(R.id.textView_movie_plot);
        textView_movie_name     = findViewById(R.id.textView_movie_name);
        textView_movie_rating   = findViewById(R.id.textView_movie_rating);
        textView_movie_released = findViewById(R.id.textView_movie_released);
        textView_movie_runtime  = findViewById(R.id.textView_movie_runtime);
        textView_movie_votes    = findViewById(R.id.textView_movie_votes);
        imageView_movie_poster  = findViewById(R.id.Image_View_movie_poster);
        Imageview_WatchProviders = findViewById(R.id.Imageview_WatchProviders);
        recycler_movie_cast     = findViewById(R.id.recyler_movie_cast);

        manager = new Request_Manager(this);
        manager2 = new Request_Manager(this);
        manager3 = new Request_Manager(this);
        String movie_id = getIntent().getStringExtra("data");
        dialog = new ProgressDialog(this);
        dialog.setTitle("Patientez...");
        dialog.show();
        manager.searchMovieDetails(listener,movie_id);
        manager2.searchCastMembers(listener2,movie_id);
        manager3.searchWatchProviders(listener3,movie_id);
    }
    private OnDetailsApiListeners listener = new OnDetailsApiListeners() {
        @Override
        public void onResponse(DetailsApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(MovieDetailsActivity.this,"Une erreur est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MovieDetailsActivity.this,"Une erreur est survenue",Toast.LENGTH_SHORT).show();
        }
    };

    private OnCastMembersApiListeners listener2 = new OnCastMembersApiListeners() {
        @Override
        public void onResponse(CastMembers response)
        {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(MovieDetailsActivity.this,"Une erreur02 est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults2(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MovieDetailsActivity.this,"Une erreur02 est survenue",Toast.LENGTH_SHORT).show();
        }
    };

    private OnWatchProvidersApiListeners listener3 = new OnWatchProvidersApiListeners() {
        @Override
        public void onResponse(WatchProvidersApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(MovieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults3(response);

        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MovieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
        }
    };


    private void showResults(DetailsApiResponse response)
    {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released.setText("Date de sortie : " + response.getRelease_date());
        textView_movie_runtime.setText("Durée du film : " + response.getRuntime() + "min");
        textView_synopsis.setText("Synopsis : " + response.getOverview());
        textView_movie_votes.setText("Nombre de votes : " + response.getVote_count());
        textView_movie_rating.setText("Notes : " + response.getVote_average() +"/10");
        try
        {
            Picasso.get().load(response.getPoster_path()).into(imageView_movie_poster);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
    private void showResults2(CastMembers response)
    {
        recycler_movie_cast.setHasFixedSize(true);
        recycler_movie_cast.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CastRecylerAdapter(this,response.getCast());
        recycler_movie_cast.setAdapter(adapter);
    }

    private void showResults3(WatchProvidersApiResponse response)
    {
    
    }
}