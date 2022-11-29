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

import com.example.movieslist.Listeners.OnWatchProvidersApiListeners;
import com.example.movieslist.Models.SearchArrayObjectsForSearchMovies;
import com.example.movieslist.Models.WatchProviders.ArrayWatchProvidersFr;
import com.example.movieslist.Models.WatchProviders.StreamingArray;
import com.example.movieslist.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class WatchProvidersRecylclerAdapater extends  RecyclerView.Adapter<WatchProvidersViewHolder>
{
    Context context;
    List<StreamingArray> list;
    OnWatchProvidersApiListeners listener;


    public WatchProvidersRecylclerAdapater(Context context, List<StreamingArray> list, OnWatchProvidersApiListeners listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WatchProvidersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WatchProvidersViewHolder(LayoutInflater.from(context).inflate(R.layout.watch_providers_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WatchProvidersViewHolder holder, int position)
    {
        holder.WatchProvider_name_container.setText(list.get(position).getProvider_name());
        Picasso.get().load(list.get(position).getLogo_path()).into(holder.imageView_WatchProviders);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

}

class WatchProvidersViewHolder extends RecyclerView.ViewHolder
{
    ImageView imageView_WatchProviders;
    TextView WatchProvider_name_container;
    CardView WatchProvider_container;

    public WatchProvidersViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_WatchProviders = itemView.findViewById(R.id.imageView_WatchProviders);
        WatchProvider_name_container = itemView.findViewById(R.id.WatchProvider_name_container);
        WatchProvider_container = itemView.findViewById(R.id.WatchProviders_container);
    }
}