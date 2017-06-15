package com.example.vikas.movie.model;

/**
 * Created by vikas on 25/4/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class MovieGenres
{
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String  name;

    public MovieGenres(int id,String name )
    {
        this.id = id;
        this.name=name;
    }


    public String getGenre_name()
    {
        return name;
    }

    public void setGenre_name(String name)
    {
        this.name = name;
    }

    public int getId()
    {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
