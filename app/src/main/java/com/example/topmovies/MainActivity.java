package com.example.topmovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.topmovies.adapter.MovieAdapter;
import com.example.topmovies.adapter.OnMovieListener;
import com.example.topmovies.model.MovieModel;

import com.example.topmovies.viewmodel.MovieListViewModel;


import org.jetbrains.annotations.NotNull;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMovieListener {



    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private Toolbar toolbar;
    private SearchView searchView;

    private boolean isPopular=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        observeChanges();
        observePopularMovies();
        movieListViewModel.searchPopularMovie(1);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovie(query,1);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollHorizontally(1)){
                movieListViewModel.searchNextPage();
                }
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular=false;
            }
        });






    }

    private void observePopularMovies() {
        movieListViewModel.getPopularMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null){
                    adapter.setMovieModels(movieModels);
                }
            }
        });

    }


    private void observeChanges() {
        movieListViewModel.getMovies().observe(MainActivity.this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels!=null){
                   adapter.setMovieModels(movieModels);
                }


            }
        });
    }

    private void init() {
        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);
        recyclerView=findViewById(R.id.main_recyclerView);
        adapter=new MovieAdapter();
        adapter.setOnMovieListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);
        toolbar=findViewById(R.id.main_toolbar);
        searchView=findViewById(R.id.main_searchView);

    }

    @Override
    public void onMovieClicked(MovieModel movieModel) {
        Intent intent=new Intent(MainActivity.this,MovieDetailsActivity.class);
        intent.putExtra("rating",movieModel.getVote_average());
        intent.putExtra("movie",movieModel);
        startActivity(intent);
    }

    @Override
    public void onCategoryClicked(int position) {

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