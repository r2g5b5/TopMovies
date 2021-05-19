package com.example.topmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.topmovies.model.MovieModel;
import com.example.topmovies.request.Service;
import com.example.topmovies.response.MovieSearchResponse;
import com.example.topmovies.utils.Credentials;
import com.example.topmovies.utils.MovieApi;
import com.example.topmovies.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        observeChanges();


        findViewById(R.id.mainBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie("Fast",1);
            }
        });






    }

    private void searchMovie(String query,int page){
        movieListViewModel.searchMovie(query,page);
    }


    private void observeChanges() {
        movieListViewModel.getMovies().observe(MainActivity.this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null){
                    for (MovieModel movieModel:movieModels) {
                        Log.d("MainTag","onChanged: "+movieModel.getTitle());

                    }
                }


            }
        });
    }

    private void init() {
        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);
    }

/*
    private void getRetrofitResponse() {
        MovieApi movieApi= Service.getMovieApi();
        Call<MovieSearchResponse> responseCall=movieApi.searchMovie(Credentials.API_KEY,
                "joker",
                1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code()==200){
                    Log.d("Tag","Response: "+response.body().toString());
                    List<MovieModel> movies=new ArrayList<>(response.body().getMovies());
                    for (MovieModel movieModel: movies){
                        Log.d("Tag","The List: "+movieModel.getTitle());

                    }

                }else {
                    try {
                        Log.d("Tag","Error: "+response.errorBody().string());

                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });

    }

    private void getRetrofitById(){
        MovieApi movieApi=Service.getMovieApi();
        Call<MovieModel> movieModelCall=movieApi.getMovie(550,Credentials.API_KEY);
        movieModelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code()==200){
                    MovieModel movieModel=response.body();
                    Log.d("Tag","Response: "+movieModel.getTitle());
                }else {
                    try {
                        Log.d("Tag","Error: "+response.errorBody().string());

                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

 */

}