package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldcinema.Adapter.AdapterChats;
import com.example.worldcinema.network.ChatsListAPI;
import com.example.worldcinema.network.models.ChatBody;
import com.example.worldcinema.network.models.ChatListResponse;
import com.example.worldcinema.network.models.ChatPostResponse;
import com.example.worldcinema.network.models.ChatResponse;
import com.example.worldcinema.network.service.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatScreen extends AppCompatActivity {
    ImageView imageView;
    String movieId;
    Bundle arguments;
    TextView nameChat;
    private String token;
    SharedPreferences sharedPreferences;
    private String myFirstName, mySecondName;
    AdapterChats adapterChats;
    RecyclerView recyclerView;
    String chatId;
    ImageButton sendMessageBtn;
    private EditText message;
    ApiService service = ChatsListAPI.getInstance().getService();
    private ArrayList<ChatListResponse> chatResponses;
    private ArrayList<ChatResponse> chatResponses1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        imageView = findViewById(R.id.back_arrow);
        nameChat = findViewById(R.id.textTitleCollections);
        sharedPreferences = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        sendMessageBtn = findViewById(R.id.btn_send);
        myFirstName = sharedPreferences.getString("firstName", "");
        mySecondName = sharedPreferences.getString("lastName", "");
        message = findViewById(R.id.textMessage);
        recyclerView = findViewById(R.id.recyclerViewChat);
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        arguments = getIntent().getExtras();
        if (arguments != null) {
            movieId = arguments.getString("movieId");

        }
        sendMessageBtn.setOnClickListener(view -> {
            sendMessage(token, chatId, new ChatBody(message.getText().toString()));
            adapterChats.notifyDataSetChanged();
            message.setText(null);
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                messageChat(chatId, token);
            }
        },0,    100);
        fetchChat(movieId);
    }

    private void fetchChat(String movieId) {
        AsyncTask.execute(() -> {
            service.getChats(movieId).enqueue(new Callback<List<ChatListResponse>>() {
                @Override
                public void onResponse(Call<List<ChatListResponse>> call, Response<List<ChatListResponse>> response) {
                    if (response.isSuccessful()) {
                        chatResponses = new ArrayList<>(response.body());
                        if (chatResponses.size() == 0) {
                            Toast.makeText(ChatScreen.this, "Чат не найден", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            chatId = response.body().get(0).getChatId();
                            messageChat(chatId, token);
                            nameChat.setText(response.body().get(0).getName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ChatListResponse>> call, Throwable t) {

                }
            });
        });
    }

    private void messageChat(String chatId, String token) {
        AsyncTask.execute(() -> {
            service.getInfoChats(chatId, token).enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    if (response.isSuccessful()) {
                        chatResponses1 = new ArrayList<>(response.body());
                        for (int i = 0; i < response.body().size(); i++) {
                            if (myFirstName == response.body().get(i).getFirstName() && mySecondName == response.body().get(i).getLastName()) {
                                chatResponses1.get(i).setViewType(0);
                            } else {
                                chatResponses1.get(i).setViewType(1);
                            }
                        }
                        adapterChats = new AdapterChats(chatResponses1);
                        recyclerView.setAdapter(adapterChats);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.scrollToPosition(chatResponses1.size() - 1);
                    }
                }

                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {

                }
            });
        });
    }

    private void sendMessage(String token, String chatId, ChatBody text) {
        AsyncTask.execute(() -> {
            service.getSendMessage(token, chatId, text).enqueue(new Callback<List<ChatPostResponse>>() {
                @Override
                public void onResponse(Call<List<ChatPostResponse>> call, Response<List<ChatPostResponse>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ChatScreen.this, "Все успешно", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatPostResponse>> call, Throwable t) {

                }
            });
        });
    }

}