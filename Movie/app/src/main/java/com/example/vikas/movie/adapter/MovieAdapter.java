package com.example.vikas.movie.adapter;

/**
 * Created by vikas on 23/4/17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vikas.movie.R;
import com.example.vikas.movie.helper.CircleTransformation;
import com.example.vikas.movie.model.Movie;
import com.example.vikas.movie.model.MoviesResponse;

import java.util.List;

import retrofit2.Callback;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private static final String TAG=MovieAdapter.class.getSimpleName();

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private   MovieAdapterListener listener;


    public static class MovieViewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView imageView;


        public MovieViewHolder(View v)
        {
            super(v);
            moviesLayout = (RelativeLayout) v.findViewById(R.id.movie_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
            imageView=(ImageView)v.findViewById(R.id.movie_image);
        }



    }

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context,MovieAdapterListener listener)
    {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position)
    {
        Movie movie= movies.get(position);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getvote_average().toString());
        MovieImage(holder,movie);
        applyClickEvents(holder,position);


    }
    private void applyClickEvents(MovieViewHolder holder,final int position)
    {
        holder.moviesLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onItemClicked(position);
            }
        });


    }


    private void MovieImage(MovieViewHolder holder,Movie movie)
    {
        String base_URL="https://image.tmdb.org/t/p/";
        String size_Image="w500";
        String file_Path=movie.getPosterPath();

        StringBuilder image_Path=new StringBuilder();
        image_Path.append(base_URL).append(size_Image).append(file_Path);
        Log.d(TAG,"movie genere ids  "+movie.getGenreIds());
        Log.d(TAG,"movie relese date   "+movie.getReleaseDate());




        if(!TextUtils.isEmpty(image_Path))
        {
            Glide.with(context).load(image_Path.toString())
                    .thumbnail(0.5f)
                    .crossFade()
                    .transform(new CircleTransformation(context))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
        }
        else
        {
            holder.imageView.setImageResource(R.drawable.circle);
        }

    }

    public interface MovieAdapterListener
    {
        void onItemClicked(int position);

    }


    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}
