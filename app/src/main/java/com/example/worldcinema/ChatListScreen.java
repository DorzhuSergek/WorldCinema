package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ImageView;


import com.example.worldcinema.network.ChatsListAPI;

import com.example.worldcinema.network.service.ApiService;

public class ChatListScreen extends AppCompatActivity {

    RecyclerView recyclerView;


    ImageView arrowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list_screen);
        arrowBack = findViewById(R.id.back_arrow);
        arrowBack.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        recyclerView = findViewById(R.id.chat_recycler);

    }

}