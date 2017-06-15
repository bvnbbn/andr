package com.example.rajrajas.bemoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rajrajas.bemoapp.Activity.MovieDetailsActivity;
import com.example.rajrajas.bemoapp.Data.MovieItem;
import com.example.rajrajas.bemoapp.databinding.MovieListBinding;

import java.util.Collections;
import java.util.List;

/**
 * Created by rajrajas on 5/30/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<MovieItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public MovieAdapter(Context context, List<MovieItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final MovieListBinding movieListBinding = MovieListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ItemViewHolder(context, movieListBinding);

    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.bind(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view)
                                               {
                                                   Intent intent = new Intent(context, MovieDetailsActivity.class);
                                                   intent.putExtra("Movie_id",data.get(holder.getPosition()).getMovie_Id()+"");
                                                   context.startActivity(intent);


                                               }
                                           }
        );


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
