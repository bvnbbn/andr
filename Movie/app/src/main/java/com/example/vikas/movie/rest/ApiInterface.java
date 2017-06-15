package com.example.vikas.movie.rest;

/**
 * Created by vikas on 23/4/17.
 */
import com.example.vikas.movie.model.Movie;
import com.example.vikas.movie.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface
{
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<Movie> getMovieVideo(@Path("id") int id, @Query("api_key") String apiKey);



}