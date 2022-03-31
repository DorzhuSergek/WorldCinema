package com.example.myapplication;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterMovies;
import com.example.myapplication.network.PosterApi;
import com.example.myapplication.network.models.MovieResponse;
import com.example.myapplication.network.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenMovies    extends AppCompatActivity {
    RecyclerView recyclerView ;
    ApiService service = PosterApi.getInstance().getService();
    private ArrayList<MovieResponse> movieResponses;
    AdapterMovies adapterMovies;
    SnapHelper snapHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_movies);
        recyclerView =findViewById(R.id.moviesRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        fetchMovieCover();
    }
    private void fetchMovieCover() {
        AsyncTask.execute(() -> {
            service.fetchMovie().enqueue(new Callback<List<MovieResponse>>() {

                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if (response.isSuccessful()) {
                        movieResponses = new ArrayList<>(response.body());
                        adapterMovies=new AdapterMovies(movieResponses,getApplicationContext());
                        recyclerView.setAdapter(adapterMovies);
                        adapterMovies.notifyDataSetChanged();
                    } else if (response.code() == 404) {

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