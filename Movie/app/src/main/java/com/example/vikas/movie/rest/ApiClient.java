package com.example.vikas.movie.rest;

/**
 * Created by vikas on 22/4/17.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient
{
    public static final String URL="http://api.themoviedb.org/3/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient()
    {
        if(retrofit==null)
        {
            retrofit= new Retrofit.Builder()//netsed class of Retrofit used to create a new instance of retrofit class
                    .baseUrl(URL)//loaded the base URL which will be used later for all api calls
                    .addConverterFactory(GsonConverterFactory.create())//added converter factory for
                    // serialization and desserialization of objects

                    .build();//created the retrofit instance with configured values

        }
        return retrofit;
    }

}
