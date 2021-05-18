package com.example.topmovies.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.topmovies.AppExecutors;
import com.example.topmovies.model.MovieModel;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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



    public void searchMovie(){
        final Future myHandler= AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);

            }
        },3500, TimeUnit.MICROSECONDS);

    }


}
