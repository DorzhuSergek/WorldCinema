package com.example.myapplication.network.service;

import com.example.myapplication.network.models.LoginResponse;
import com.example.myapplication.network.models.MovieResponse;
import com.example.myapplication.network.models.LoginBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("movies?filter=new")
    Call<List<MovieResponse>> fetchMovie();

    @POST("/auth/login")
    Call<LoginResponse> registrationAccount(@Body LoginBody registerAccount);
}
