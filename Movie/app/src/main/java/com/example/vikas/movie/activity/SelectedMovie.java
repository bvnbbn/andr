package com.example.vikas.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vikas.movie.R;
import com.example.vikas.movie.model.Movie;
import com.example.vikas.movie.model.MovieGenres;
import com.example.vikas.movie.model.MovieVedeo;
import com.example.vikas.movie.rest.ApiClient;
import com.example.vikas.movie.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vikas.movie.R.id.fab;

/**
 * Created by vikas on 25/4/17.
 */

public class SelectedMovie extends AppCompatActivity
{


    private static int id;
    private static final String TAG=SelectedMovie.class.getSimpleName();
    //API KEY
    private final static String API_KEY="ddfffa28b4ace50cacf67274370469a1";
    private final static String videos="&append_to_response=videos";
    private CoordinatorLayout coordinatorLayout;
    private ImageView selected_movie_image;
    private TextView selecetd_movie_title;
    private TextView seleceted_movie_overview;
    private TextView selecetd_movie_release_date;
    private TextView seleceted_movie_runtime;
    private TextView seleceted_movie_rating;
    private TextView seleceted_movie_genre;
   // private ArrayList<HashMap<Integer,String>> genre_List=new ArrayList<HashMap<Integer, String>>();

    private List<MovieGenres> movie_genres=new ArrayList<MovieGenres>();
    private List<String> genres_List = new ArrayList<String>();
    private List<MovieVedeo> movie_videos = new ArrayList<>();
    private String you_tube_key=null;
    private FloatingActionButton fab;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_movie);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.selected_movie_coordinate_layout);
        selected_movie_image=(ImageView)findViewById(R.id.selected_movie_image);
        selecetd_movie_title=(TextView)findViewById(R.id.selected_movie_title);
        seleceted_movie_overview=(TextView)findViewById(R.id.selected_movie_overview);
        seleceted_movie_runtime=(TextView)findViewById(R.id.runtime);
        seleceted_movie_rating=(TextView)findViewById(R.id.rating);
        seleceted_movie_genre=(TextView)findViewById(R.id.genre);

        // retrieving recieved values
        Bundle extras =getIntent().getExtras();
        if(extras!=null)
        {
             id = extras.getInt("movie_id");
            //genre_ids=extras.getIntegerArrayList("genre_ids");
        }

       // Log.d(TAG,"genre size "+genre_ids.size());


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VedeoActivity.class);
                intent.putExtra("you_tube_key",you_tube_key);
                startActivity(intent);
            }
        });


        if(API_KEY.isEmpty())
        {
            Log.d(TAG,"Please Enter the key");
            return;
        }
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        final Call<Movie>  moviecall=apiInterface.getMovieDetails(id,API_KEY);

        moviecall.enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                Movie movie_selected=response.body();
                MovieGenres genres;

                movie_genres=response.body().getMovie_genres();
                for(int i=0;i<movie_genres.size();i++)
                {
                    genres = movie_genres.get(i);
                    genres_List.add(genres.getGenre_name());

                }

                Log.d("TAG","Genre_list "+movie_genres.size());
               // Log.d("TAG","Genre_list "+genres.getGenre_name());
                if(genres_List.size()==3)
                {
                    seleceted_movie_genre.setText((genres_List.get(0)+ "   "+genres_List.get(1)+ "   "+genres_List.get(2)));

                }
                else if(genres_List.size()==1)
                {
                    seleceted_movie_genre.setText((genres_List.get(0)));

                }

                else
                {
                    seleceted_movie_genre.setText((genres_List.get(0))+ " " +genres_List.get(1));

                }

                String base_URL="https://image.tmdb.org/t/p/";
                String size_Image="w640";
                String file_Path=movie_selected.getBackdropPath();

                StringBuilder image_Path=new StringBuilder();
                image_Path.append(base_URL).append(size_Image).append(file_Path);
                Log.d(TAG,"source_imagePath "+image_Path);

                if(!TextUtils.isEmpty(image_Path))
                {
                    Glide.with(getApplicationContext()).load(image_Path.toString())
                            .crossFade()
                            .override(700,300)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(selected_movie_image);
                }



                int time = movie_selected.getRuntime();
                int time_hours=time/60;
                int time_minutes=time%60;
                selecetd_movie_title.setText(movie_selected.getTitle());
                seleceted_movie_overview.setText(movie_selected.getOverview());
                seleceted_movie_runtime.setText(String.valueOf(time_hours)+"h "+String.valueOf(time_minutes)+"m" );
                seleceted_movie_rating.setText(String.valueOf(movie_selected.getvote_average().toString()));




            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t)
            {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Please Check your Internet Connection", Snackbar.LENGTH_LONG);

                snackbar.show();
                Log.e(TAG, t.toString());

            }
        });


        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        final Call<Movie>  movie_video_call=apiInterface.getMovieVideo(id,API_KEY);

        movie_video_call.enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                MovieVedeo video;
                movie_videos=response.body().getMovie_vedeo();
                video=movie_videos.get(0);
                you_tube_key=video.getkey();


                Log.d(TAG,"movie vedeos list "+movie_videos.size());

                Log.d(TAG,"movie vedeos list "+video.getkey());


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t)
            {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Please Check your Internet Connection", Snackbar.LENGTH_LONG);

                snackbar.show();
                Log.e(TAG, t.toString());

            }
        });

    }




    /*private List<String> genre()
    {

        genre_ids.add(14);
        genre_ids.add(10749);

        initialise_generes();
        Log.d("TAG" ,"id size "+genre_ids.size());
        Log.d("TAG" ,"id size "+genre_List.size());

        List<String> selected_movie_genre = new ArrayList<>();
        for (HashMap<Integer, String> hashmap : genre_List)
        {

            for (Map.Entry<Integer, String> entry : hashmap.entrySet())
            {

                for (int i = 0; i < genre_ids.size(); i++)
                {

                    if (genre_ids.get(i).equals(entry.getKey()))
                    {

                        selected_movie_genre.add(entry.getValue());
                    }
                }
            }
        }

        return selected_movie_genre;

    }*/


}
