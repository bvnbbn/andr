package com.example.vikas.movie.model;

/**
 * Created by vikas on 25/4/17.
 */
import com.google.gson.annotations.SerializedName;


public class MovieVedeo
{


    @SerializedName("id")
    private String id;
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("iso_31661_1")
    private String iso_31661_1;
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName("size")
    private String size;
    @SerializedName("type")
    private String type;


    public MovieVedeo(String id, String iso_639_1, String iso_31661_1, String key, String name, String site, String size, String type)
    {
        this.id = id;
        this.iso_639_1=iso_639_1;
        this.iso_31661_1=iso_31661_1;
        this.key=key;
        this.name=name;
        this.site=site;
        this.size=size;
        this.type=type;


    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {

        return id;
    }


    public void setIso_31661_1(String iso_31661_1)
    {
        this.iso_31661_1 = iso_31661_1;
    }

    public String getIso_31661_1()
    {
        return iso_31661_1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIso_639_1()
    {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1)
    {
        this.iso_639_1 = iso_639_1;
    }


    public String getSite()
    {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }


    public String getkey()
    {
        return key;
    }

    public void setkey(String key)
    {
        this.key = key;
    }




}
