package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.network.models.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {
    private ArrayList<MovieResponse> movieResponse;
    private LayoutInflater inflater;
    private Context context;

    public AdapterMovies(ArrayList<MovieResponse> movieResponse, Context context) {
        this.movieResponse = movieResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.movie_item, parent, false);
//        moviePoster = view.findViewById(R.id.nameMovie);

        return new AdapterMovies.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMovies.ViewHolder holder, int position) {
        MovieResponse movieResponse1 = movieResponse.get(position);
        holder.setTextCinema(movieResponse1.getName());

        Picasso.with(context).
                load("http://cinema.areas.su/up/images/" + movieResponse1.getPoster()).
                into(holder.coverCinema);

    }

    @Override
    public int getItemCount() {
        return movieResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView coverCinema;
        final private TextView txtCinema;

        private ViewHolder(View view) {
            super(view);
            this.coverCinema = (ImageView) view.findViewById(R.id.posterMovie);
            this.txtCinema = (TextView) view.findViewById(R.id.nameMovie);

        }

        public void setTextCinema(String text) {
            this.txtCinema.setText(text);
        }
    }
}
