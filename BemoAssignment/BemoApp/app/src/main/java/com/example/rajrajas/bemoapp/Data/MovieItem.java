package com.example.rajrajas.bemoapp.Data;

/**
 * Created by rajrajas on 5/30/2017.
 */

public class MovieItem {
    int Movie_Id;
    String Movie_Name, Movie_Overview, Movie_release_date, Movie_Poster_path, Movie_Popularity, Movie_vote_count, Movie_Vote_average;


    public MovieItem(int Movie_Id, String Movie_Name, String Movie_Overview, String Movie_release_date, String Movie_Poster_path, String Movie_Popularity, String Movie_vote_count, String Movie_Vote_average) {
        this.Movie_Id = Movie_Id;
        this.Movie_Name = Movie_Name;
        this.Movie_Overview = Movie_Overview;
        this.Movie_release_date = Movie_release_date;
        this.Movie_Poster_path = Movie_Poster_path;
        this.Movie_Popularity = Movie_Popularity;
        this.Movie_vote_count = Movie_vote_count;
        this.Movie_Vote_average = Movie_Vote_average;
    }

    public int getMovie_Id() {
        return Movie_Id;
    }

    public String getMovie_Name() {
        return Movie_Name;
    }

    public String getMovie_Overview() {
        return Movie_Overview;
    }

    public String getMovie_release_date() {
        return Movie_release_date;
    }

    public String getMovie_Poster_path() {
        return Movie_Poster_path;
    }

    public String getMovie_Popularity() {
        return Movie_Popularity;
    }

    public String getMovie_vote_count() {
        return Movie_vote_count;
    }

    public String getMovie_Vote_average() {
        return Movie_Vote_average;
    }

    public void setMovie_Id(int movie_Id) {
        Movie_Id = movie_Id;
    }

    public void setMovie_Name(String movie_Name) {
        Movie_Name = movie_Name;
    }

    public void setMovie_Overview(String movie_Overview) {
        Movie_Overview = movie_Overview;
    }

    public void setMovie_release_date(String movie_release_date) {
        Movie_release_date = movie_release_date;
    }

    public void setMovie_Poster_path(String movie_Poster_path) {
        Movie_Poster_path = movie_Poster_path;
    }

    public void setMovie_Popularity(String movie_Popularity) {
        Movie_Popularity = movie_Popularity;
    }

    public void setMovie_vote_count(String movie_vote_count) {
        Movie_vote_count = movie_vote_count;
    }

    public void setMovie_Vote_average(String movie_Vote_average) {
        Movie_Vote_average = movie_Vote_average;
    }
}
