package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class ChatPostResponse {

    //это то что мы будем получать
    @SerializedName("text")
    private String text;

    public ChatPostResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
