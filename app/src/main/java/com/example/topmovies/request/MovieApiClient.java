package com.example.topmovies.request;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.topmovies.AppExecutors;
import com.example.topmovies.model.MovieModel;
import com.example.topmovies.response.MovieSearchResponse;
import com.example.topmovies.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> movieMutableLiveData;

    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;

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



    public void searchMovie(String query,int page){

        if (retrieveMoviesRunnable!=null){
            retrieveMoviesRunnable=null;
        }


        final Future myHandler= AppExecutors.getInstance().networkIO().submit(new RetrieveMoviesRunnable(query,page));

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);

            }
        },3500, TimeUnit.MICROSECONDS);

    }


    private class RetrieveMoviesRunnable implements Runnable{
        private String query;
        private int pageNum;
        private boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNum) {
            this.query = query;
            this.pageNum = pageNum;
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response response=getMovies(query,pageNum).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code()==200){
                    List<MovieModel> movieModelList=new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if (pageNum==1){
                        movieMutableLiveData.postValue(movieModelList);
                    }else {
                        List<MovieModel> currentMovie=movieMutableLiveData.getValue();
                        currentMovie.addAll(movieModelList);
                        movieMutableLiveData.postValue(currentMovie);
                    }
                }else {

                    Log.d("Tag","MovieApiClient Error: "+response.errorBody().string());
                    movieMutableLiveData.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                movieMutableLiveData.postValue(null);
            }

            if (cancelRequest) {
                return;
            }
        }

            Call<MovieSearchResponse> getMovies(String query,int page){
                return Service.getMovieApi().searchMovie(Credentials.API_KEY
                ,query,page);


        }

        private void setCancelRequest(){
            cancelRequest=true;
        }

    }


}
