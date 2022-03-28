package com.example.worldcinema.network.service;

import com.example.worldcinema.network.models.ChatListResponse;
import com.example.worldcinema.network.models.ChatResponse;
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
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("chats/{movieId}")
    Call<List<ChatListResponse>> getChats(@Path("movieId") String movieId);

    @POST("chats/1/messages")
    Call<List<ChatResponse>> getInfoChats(@Header("Authorization") String token);
}

