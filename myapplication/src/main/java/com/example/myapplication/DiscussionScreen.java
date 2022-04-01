package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterDiscussion;
import com.example.myapplication.Adapter.AdapterMovies;
import com.example.myapplication.network.PosterApi;
import com.example.myapplication.network.models.MovieResponse;
import com.example.myapplication.network.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionScreen extends AppCompatActivity {
    RecyclerView recyclerView ;
    ApiService service = PosterApi.getInstance().getService();
    private ArrayList<MovieResponse> movieResponses;
    AdapterDiscussion adapterDiscussion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_screen);
        recyclerView = findViewById(R.id.discussionRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        fetchDiscussion();
    }

    private void fetchDiscussion() {
        AsyncTask.execute(() -> {
            service.fetchMovie().enqueue(new Callback<List<MovieResponse>>() {
                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
                        movieResponses = new ArrayList<>(response.body());
                        adapterDiscussion=new AdapterDiscussion(movieResponses,getApplicationContext());
                        recyclerView.setAdapter(adapterDiscussion);
                        adapterDiscussion.notifyDataSetChanged();
                        Toast.makeText(DiscussionScreen.this, "Красава", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DiscussionScreen.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    Toast.makeText(DiscussionScreen.this, "Ошибка "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}