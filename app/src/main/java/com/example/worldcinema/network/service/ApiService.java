package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.models.MovieCoverResponse;
import com.example.worldcinema.network.models.MovieResponse;
import com.example.worldcinema.network.models.ProfileResponse;
import com.example.worldcinema.network.models.RegisterBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);

    @GET("cover")
    Call<MovieCoverResponse> fetchMovieCover();

    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> fetchMovie();


    @POST("/auth/register")
    Call<RegisterBody> registrationAccount(@Body RegisterBody registerAccount);


    @GET("user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);
}

