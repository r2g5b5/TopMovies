package com.example.topmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topmovies.R;
import com.example.topmovies.model.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private OnMovieListener onMovieListener;
    private List<MovieModel> movieModels=new ArrayList<>();



    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    public void setOnMovieListener(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;

    }



    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.ViewHolder holder, int position) {

        holder.ratingBar.setRating(movieModels.get(position).getVote_average());
        holder.ratingBar.setEnabled(false);

        Glide.with(holder.imgMain.getContext()).
                load("https://image.tmdb.org/t/p/w500/"+movieModels.get(position).
                        getPoster_path()).placeholder(R.mipmap.ic_launcher).
                into(holder.imgMain);

    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView imgMain;

        private RatingBar ratingBar;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMain=itemView.findViewById(R.id.movie_row_imageView);
            ratingBar=itemView.findViewById(R.id.movie_row_ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieListener.onMovieClicked(movieModels.get(getAdapterPosition()));
                }
            });

        }
    }

}
