package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    //это то что мы будем получать
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
