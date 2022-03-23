package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
}
