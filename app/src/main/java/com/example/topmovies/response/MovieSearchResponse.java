package com.example.topmovies.response;

import com.example.topmovies.model.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose()
    private int totalCount;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public int getTotalCount(){
        return totalCount;
    }

    public List<MovieModel> getMovies(){
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "totalCount=" + totalCount +
                ", movies=" + movies +
                '}';
    }
}
