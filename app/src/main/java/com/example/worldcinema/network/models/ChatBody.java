package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class ChatBody {
    @SerializedName("text")
    private String textMessage;

    public ChatBody(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getText() {
        return textMessage;
    }

    public void setText(String textMessage) {
        this.textMessage = textMessage;
    }
}
