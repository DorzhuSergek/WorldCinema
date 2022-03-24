package com.example.worldcinema.Fragment;

import android.app.slice.Slice;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldcinema.Adapter.AdapterMovies;
import com.example.worldcinema.Adapter.AdapterPoster;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.MoviesApi;
import com.example.worldcinema.network.PosterApi;
import com.example.worldcinema.network.models.MovieCoverResponse;
import com.example.worldcinema.network.models.MovieResponse;
import com.example.worldcinema.network.service.ApiService;
import com.squareup.picasso.Picasso;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class firstFragment extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<MovieResponse> movieResponses;
    ApiService service = MoviesApi.getInstance().getService();
    private AdapterMovies adapterMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        movieResponses=new ArrayList<>();
        adapterMovies=new AdapterMovies(movieResponses,getContext());
        recyclerView.setAdapter(adapterMovies);
        fetchMovieCover();
        return view;
    }

    private void fetchMovieCover() {
        AsyncTask.execute(() -> {
            service.fetchMovie().enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.isSuccessful()) {
                        movieResponses.add(response.body());
                        adapterMovies.notifyDataSetChanged();
                    } else if (response.code() == 404) {
                        String error = ErrorUtils.parseError(response).message();
                        System.out.println(error);
                    } else {
                        System.out.println("Ошибка");
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage());
                }
            });
        });

    }
}