package com.example.myapplication.network.models;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("movieId")
    private String movieId;
    @SerializedName("poster")
    private String poster;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("age")
    private String age;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
