package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class ChatResponse {

    //это то что мы будем получать


    @SerializedName("chatId")
    private String chatId;
    @SerializedName("messageId")
    private String messageId;
    @SerializedName("creationDateTime")
    private String creationDateTime;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("text")
    private String text;
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;

    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public ChatResponse(String chatId, String messageId, String creationDateTime, String firstName, String lastName, String avatar, String text) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.creationDateTime = creationDateTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.text = text;
    }

    public String getChatId() {

        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
