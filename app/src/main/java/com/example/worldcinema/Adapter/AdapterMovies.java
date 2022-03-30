package com.example.worldcinema.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.ChatScreen;
import com.example.worldcinema.R;
import com.example.worldcinema.network.models.MovieCoverResponse;
import com.example.worldcinema.network.models.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {
    private ArrayList<MovieResponse> movieResponse;
    private LayoutInflater inflater;
    private Context context;
    private CardView cardView;
    private String movieID;
    private OnItemClickListener onItemClickListener;
    private CardView moviePoster;

    public AdapterMovies(ArrayList<MovieResponse> movieResponse, Context context) {
        this.movieResponse = movieResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.list_item, parent, false);
        this.cardView = view.findViewById(R.id.moviePoster);
        moviePoster = view.findViewById(R.id.moviePoster);

        cardView.setOnClickListener(view1 -> {
            context.startActivity(new Intent(context, ChatScreen.class));
        });
        return new AdapterMovies.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMovies.ViewHolder holder, int position) {
        MovieResponse movieResponse1 = movieResponse.get(position);
        holder.setTextCinema(movieResponse1.getName());
        moviePoster.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChatScreen.class);
            String movieID = movieResponse1.getMovieId();
            intent.putExtra("movieId",movieID);
            context.startActivity(intent);
        });


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
            this.coverCinema = (ImageView) view.findViewById(R.id.image_cover_cinema);
            this.txtCinema = (TextView) view.findViewById(R.id.txt_cover_cinema);

        }

        public void setTextCinema(String text) {
            this.txtCinema.setText(text);
        }
    }
}
