package com.example.movieslist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieslist.Models.ArrayForCastMembers;
import com.example.movieslist.Models.CastMembers;
import com.example.movieslist.R;

import java.util.List;

public class CastRecylerAdapter extends RecyclerView.Adapter<CastViewHolder>
{
    Context context;
    List<ArrayForCastMembers> list;

    public CastRecylerAdapter(Context context, List<ArrayForCastMembers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.textView_actor.setText(list.get(position).getName());
        holder.textView_character.setText(list.get(position).getCharacter());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CastViewHolder extends RecyclerView.ViewHolder
{
    TextView textView_actor, textView_character;
    public CastViewHolder(@NonNull View itemView)
    {
        super(itemView);
        textView_actor = itemView.findViewById(R.id.textView_actor);
        textView_character = itemView.findViewById(R.id.textView_character);
    }
}
