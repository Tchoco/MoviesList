package com.example.movieslist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieslist.Listeners.OnMovieClickListeners;
import com.example.movieslist.Listeners.OnWatchProvidersApiListeners;
import com.example.movieslist.Models.ArrayWatchProvidersResults;
import com.example.movieslist.Models.SearchArrayObjectsForSearchMovies;
import com.example.movieslist.Models.ArrayWatchProvidersFr;

import com.example.movieslist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProviderRecyclerAdapter extends RecyclerView.Adapter<ProviderViewHolder>
{
    Context context;
    List<ArrayWatchProvidersResults> list;
    OnWatchProvidersApiListeners listener;

    public ProviderRecyclerAdapter(Context context, List<ArrayWatchProvidersResults> list, OnWatchProvidersApiListeners listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateProviderHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ProviderViewHolder(LayoutInflater.from(context).inflate(R.layout.cardView_watch_providers,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        holder.home_container.get(list.get(position).getProvider_name());
        holder.textView_movie.setSelected(true);
        Picasso.get().load(list.get(position).getLogo_path()).into(holder.imageView_poster);

        holder.home_container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onMovieClicked(String.valueOf(list.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ProviderViewHolder extends RecyclerView.ViewHolder
{
    ImageView imageView_poster;
    TextView textView_movie;
    CardView home_container;
    public ProviderViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.Image_View_poster);
        textView_movie = itemView.findViewById(R.id.Text_View_movie_container);
        home_container = itemView.findViewById(R.id.home_container);
    }
}

