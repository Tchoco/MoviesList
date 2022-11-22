package com.example.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieslist.Listeners.OnDetailsApiListeners;
import com.example.movieslist.Models.DetailsApiResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView textView_movie_name, textView_movie_released, textView_movie_runtime,textView_movie_rating, textView_movie_votes, textView_synopsis;
    ImageView imageView_movie_poster;
    RecyclerView recycler_movie_cast;
    //CastRecyclerAdapter adapter;
    Request_Manager manager ;
    Request_Manager manager2 ;
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
        recycler_movie_cast     = findViewById(R.id.recyler_movie_cast);

        manager = new Request_Manager(this);
        String movie_id = getIntent().getStringExtra("data");
        dialog = new ProgressDialog(this);
        dialog.setTitle("Patientez...");
        dialog.show();
        manager.searchMovieDetails(listener,movie_id);
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

    private void showResults(DetailsApiResponse response)
    {
        textView_movie_name.setText(response.getTitle());
        textView_movie_released.setText("Date de sortie : " + response.getRelease_date());
        textView_movie_runtime.setText("Durée du film : " + response.getRuntime());
        textView_synopsis.setText("Synopsys : " + response.getOverview());
        textView_movie_votes.setText("Nombres de votes : " + response.getVote_count());
        textView_movie_rating.setText("Notes : " + response.getVote_average());

        try
        {
            Picasso.get().load(response.getPoster_path()).into(imageView_movie_poster);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}