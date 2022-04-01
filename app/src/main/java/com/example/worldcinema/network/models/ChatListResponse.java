package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class ChatListResponse {
    //это то что мы будем получать

    @SerializedName("chatId")
    private String chatId;
    @SerializedName("name")
    private String name;

    public ChatListResponse(String chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
