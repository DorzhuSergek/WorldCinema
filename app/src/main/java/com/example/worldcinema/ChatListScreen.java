package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.worldcinema.Adapter.AdapterChats;
import com.example.worldcinema.network.ChatsListAPI;
import com.example.worldcinema.network.models.ChatListResponse;
import com.example.worldcinema.network.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatListScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<ChatListResponse> chatResponses;
    AdapterChats adapterChats;

    ImageView arrowBack;
    ApiService service = ChatsListAPI.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list_screen);
        arrowBack=findViewById(R.id.back_arrow);
        arrowBack.setOnClickListener(view -> {
            startActivity(new Intent(this,MainActivity.class));
        });
        recyclerView =findViewById(R.id.chat_recycler);
    }

    private void fetchChat(String movieId) {
        AsyncTask.execute(() -> {
            service.getChats(movieId).enqueue(new Callback<List<ChatListResponse>>() {

                @Override
                public void onResponse(Call<List<ChatListResponse>> call, Response<List<ChatListResponse>> response) {
                    if (response.isSuccessful()) {
                        chatResponses = new ArrayList<>(response.body());
                        adapterChats = new AdapterChats(chatResponses, getApplicationContext(),() -> {
                            fetchChat(movieId);
                        });
                        recyclerView.setAdapter(adapterChats);
                        adapterChats.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatListResponse>> call, Throwable t) {

                }
            });
        });
    }
}