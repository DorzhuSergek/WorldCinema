package com.example.worldcinema.network.models;

public class APIError {

    //обработка ошибок
    private String error;

    public APIError(String error){
        this.error=error;
    }
    public String message(){
        return error;
    }

}
