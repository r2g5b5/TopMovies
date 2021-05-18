package com.example.topmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.topmovies.model.MovieModel;
import com.example.topmovies.request.Service;
import com.example.topmovies.response.MovieSearchResponse;
import com.example.topmovies.utils.Credentials;
import com.example.topmovies.utils.MovieApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mainBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getRetrofitResponse();
                getRetrofitById();
            }
        });



    }



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

}