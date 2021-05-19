package com.example.topmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.topmovies.model.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {


    private TextView txtTitle;
    private TextView txtDetails;
    private ImageView imgMain;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        init();
        getDataFromIntent();


    }


    private void getDataFromIntent(){
        if (getIntent().hasExtra("movie")){
            MovieModel movieModel=getIntent().getParcelableExtra("movie");
            float mmm=getIntent().getFloatExtra("rating",0);
            txtTitle.setText(movieModel.getTitle());
            txtDetails.setText(movieModel.getOverview());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+movieModel.getPoster_path()).into(imgMain);
            ratingBar.setRating(mmm/2);




        }
    }

    private void init() {
        txtTitle=findViewById(R.id.details_txtTitle);
        txtDetails=findViewById(R.id.details_txtDetails);
        imgMain=findViewById(R.id.details_imgMain);
        ratingBar=findViewById(R.id.details_ratingBar);
        ratingBar.setEnabled(false);


    }
}