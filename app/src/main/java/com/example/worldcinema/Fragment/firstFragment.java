package com.example.worldcinema.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldcinema.Adapter.AdapterMovies;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.MoviesApi;
import com.example.worldcinema.network.models.MovieResponse;
import com.example.worldcinema.network.service.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;

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
        SnapHelper snapHelper = new PagerSnapHelper();
        LinearLayoutManager layoutManager =new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(layoutManager);
        snapHelper.attachToRecyclerView(recyclerView);
        fetchMovieCover();
        return view;
    }

    private void fetchMovieCover() {
        AsyncTask.execute(() -> {
            service.fetchMovie().enqueue(new Callback<List<MovieResponse>>() {

                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if (response.isSuccessful()) {
                        movieResponses=new ArrayList<>(response.body());
                        adapterMovies = new AdapterMovies(movieResponses, getContext());
                        recyclerView.setAdapter(adapterMovies);
                        adapterMovies.notifyDataSetChanged();
                    } else if (response.code() == 404) {
                        String error = ErrorUtils.parseError(response).message();
                        System.out.println(error);
                    } else {
                        System.out.println("Ошибка");
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    System.out.println(t.getLocalizedMessage()+"Локальная проблема");

                }
            });

        });
    }
}