package com.example.topmovies.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.topmovies.model.MovieModel;
import com.example.topmovies.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository=MovieRepository.getInstance();

    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }


}
