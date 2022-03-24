package com.example.worldcinema.network.models;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("movieID")
    private String movieId;
    @SerializedName("poster")
    private String poster;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("age")
    private String age;

    public MovieResponse(String toString) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
