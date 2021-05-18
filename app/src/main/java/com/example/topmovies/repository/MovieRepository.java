package com.example.topmovies.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.topmovies.model.MovieModel;
import com.example.topmovies.request.MovieApiClient;

import java.util.List;

public class MovieRepository {


    private MovieApiClient movieApiClient;


    private static MovieRepository instance;

    public static synchronized MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
        }

        return instance;
    }

    private MovieRepository(){
        movieApiClient=MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }


}
