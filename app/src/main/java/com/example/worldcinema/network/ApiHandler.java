package com.example.worldcinema.network;

import com.example.worldcinema.network.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private static ApiHandler mInstance;
    //Базовый URl
    private static final String BASE_URL = "http://cinema.areas.su/auth/";
    private Retrofit retrofit;

    public ApiHandler() {

        // Здесь мы описываем насколько много информации о выполнении запросы мы хотим видеть в логах
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        //
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;
    }

    //получаем экземпляр нашего ApiHandler
    public static ApiHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHandler();
        }
        return mInstance;
    }

    // метод возвращает сгенерированный на основе нашего ApiService класс
    // у которого мы будем вызвать запросы к Api
    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}
