package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.network.models.MovieResponse;

import java.util.ArrayList;

public class AdapterDiscussion extends RecyclerView.Adapter<AdapterDiscussion.ViewHolder> {
    private ArrayList<MovieResponse> movieResponse;
    private LayoutInflater inflater;
    private Context context;

    public AdapterDiscussion(ArrayList<MovieResponse> movieResponse, Context context) {
        this.movieResponse = movieResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDiscussion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.discussion_item,parent,false);
        return new AdapterDiscussion.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiscussion.ViewHolder holder, int position) {
        MovieResponse movieResponse1 = movieResponse.get(position);
        holder.setTextDiscussion(movieResponse1.getName());
    }

    @Override
    public int getItemCount() {
        return movieResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final private TextView nameDiscussion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameDiscussion=itemView.findViewById(R.id.nameDiscussion);
        }
        public  void setTextDiscussion(String text){
            this.nameDiscussion.setText(text);
        }
    }
}
