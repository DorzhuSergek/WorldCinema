package com.example.worldcinema.network;

import com.example.worldcinema.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosterApi {
    private static PosterApi mInstance;

    private static final String BASE_URL="http://cinema.areas.su/movies/";
    private Retrofit retrofit;

    public PosterApi(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    public static PosterApi getInstance(){
        if(mInstance==null){
            mInstance=new PosterApi();
        }
        return  mInstance;
    }

    public ApiService getService(){
        return  retrofit.create(ApiService.class);
    }

}
