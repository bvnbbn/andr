package com.example.vikas.movie.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.example.vikas.movie.R;
import com.example.vikas.movie.adapter.MovieAdapter;
import com.example.vikas.movie.model.Movie;
import com.example.vikas.movie.model.MoviesResponse;
import com.example.vikas.movie.rest.ApiClient;
import com.example.vikas.movie.rest.ApiInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterListener
{

    private static final String TAG=MainActivity.class.getSimpleName();
    //get which got from web API
    private final static String API_KEY="ddfffa28b4ace50cacf67274370469a1";
    private CoordinatorLayout coordinatorLayout;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinate_layout);

        if(API_KEY.isEmpty())
        {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "Please Enter The key", Snackbar.LENGTH_LONG);

            snackbar.show();
            return;
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call= apiInterface.getNowPlayingMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>()
        {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response)
            {

                movieList= response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movieList.size());
                recyclerView.setAdapter(new MovieAdapter(movieList, R.layout.recyclerview_item_movie, getApplicationContext(),MainActivity.this));
                // movieAdapter.notifyDataSetChanged();
               // Log.d(TAG, "Number of movies received: " + movieList.);


            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t)
            {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Please Check your Internet Connection", Snackbar.LENGTH_LONG);

                snackbar.show();
                Log.e(TAG, t.toString());

            }
        });

    }

    @Override
    public void onItemClicked(int position)
    {
       Movie movie = movieList.get(position);
       // Toast.makeText(getApplicationContext(), "read: "+ movie.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),SelectedMovie.class);
        i.putExtra("movie_id",movie.getId());
        startActivity(i);
    }


}



