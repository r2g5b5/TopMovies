package com.example.topmovies.adapter;

import com.example.topmovies.model.MovieModel;

public interface OnMovieListener {

    void onMovieClicked(MovieModel movieModel);
    void onCategoryClicked(int position);


}
