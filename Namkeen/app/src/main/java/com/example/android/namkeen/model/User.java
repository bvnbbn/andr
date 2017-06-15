package com.example.android.namkeen.model;

/**
 * Created by vikas on 5/5/17.
 */
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class User
{

    public String name;
    public String address;
    public String email;
    public String mobile_number;
    public String date_of_birth;

    public User()
    {

    }
    public User(String name,String address,String mobile_number,String email,String date_of_birth)
    {
        this.name=name;
        this.address=address;
        this.mobile_number=mobile_number;
        this.email=email;
        this.date_of_birth=date_of_birth;
    }


}
