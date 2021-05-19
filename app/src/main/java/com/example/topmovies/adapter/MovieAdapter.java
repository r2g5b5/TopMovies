package com.example.topmovies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.R;
import com.example.topmovies.model.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private OnMovieListener onMovieListener;
    private List<MovieModel> movieModels;

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }

    public void setOnMovieListener(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
        notifyDataSetChanged();
    }



    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle;
        private TextView txtDuration;
        private TextView txtCategory;

        private ImageView imgMain;

        private RatingBar ratingBar;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.movie_row_txtTitle);
            txtDuration=itemView.findViewById(R.id.movie_row_txtDuration);
            txtCategory=itemView.findViewById(R.id.movie_row_txtCategory);
            imgMain=itemView.findViewById(R.id.movie_row_imageView);
            ratingBar=itemView.findViewById(R.id.movie_row_ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieListener.onMovieClicked(getAdapterPosition());
                }
            });

        }
    }

}
