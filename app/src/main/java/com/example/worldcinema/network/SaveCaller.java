package com.example.worldcinema.network;

import android.os.AsyncTask;
import android.telecom.Call;

import javax.security.auth.callback.Callback;

import retrofit2.Response;

public class SaveCaller <T>{
//    public void safeCall(Call<T> apiCall, Runnable onSuccess, Runnable onServerError, Runnable onUnexpectedError){
//        AsyncTask.execute(() -> {
//            apiCall.enqueue(new Callback<T>() {
//                @Override
//                public void onResponse(Call<T> call, Response<T> response) {
//                    if (response.isSuccessful()){
//                        onSuccess.run();
//                    }else if (response.code() == 400){
//                        onServerError.run();
//                    }else{
//                        onUnexpectedError.run();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<T> call, Throwable t) {
//                    onUnexpectedError.run();
//                }
//            });
//        });
//    }
}
