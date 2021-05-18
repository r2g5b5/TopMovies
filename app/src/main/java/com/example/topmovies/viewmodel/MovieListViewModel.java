package com.example.topmovies.viewmodel;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.topmovies.model.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieMutableLiveData=new MutableLiveData<>();

    public MovieListViewModel() {

    }
    public LiveData<List<MovieModel>> getMovies(){
        return movieMutableLiveData;
    }


}
