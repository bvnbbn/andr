package com.example.vikas.movie.model;

/**
 * Created by vikas on 29/4/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Vedeo
{
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<MovieVedeo> results= new ArrayList<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public List<MovieVedeo> getResults()
    {
        return results;
    }

    public void setResults(List<MovieVedeo> results)
    {
        this.results = results;
    }
}
