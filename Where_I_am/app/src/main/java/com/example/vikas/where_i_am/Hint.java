package com.example.vikas.where_i_am;

/**
 * Created by vikas on 7/4/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Hint extends RecyclerView.Adapter<Hint.MyViewHolder>
{

    private List<Hint_Adapter> hintList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name,hint;

        public MyViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            hint = (TextView) view.findViewById(R.id.hint);
        }
    }


    public Hint(List<Hint_Adapter> hintList)
    {
        this.hintList =hintList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hint_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Hint_Adapter movie = hintList.get(position);
        holder.name.setText(movie.getName());
        holder.hint.setText(movie.getHint());
    }

    @Override
    public int getItemCount()
    {
        return hintList.size();
    }
}