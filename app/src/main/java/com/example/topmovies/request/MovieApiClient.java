package com.example.topmovies.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.topmovies.model.MovieModel;

import java.util.List;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> movieMutableLiveData;

    private static MovieApiClient instance;

    public static synchronized MovieApiClient getInstance(){
        if (instance==null){
            instance=new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        movieMutableLiveData=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieMutableLiveData;
    }


}
