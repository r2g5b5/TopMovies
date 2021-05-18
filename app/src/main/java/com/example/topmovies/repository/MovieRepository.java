package com.example.topmovies.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.topmovies.model.MovieModel;

import java.util.List;

public class MovieRepository {

    private MutableLiveData<List<MovieModel>> movieMutableLiveData;

    private static MovieRepository instance;

    public static synchronized MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
        }

        return instance;
    }

    private MovieRepository(){
        movieMutableLiveData=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieMutableLiveData;
    }


}
