package com.dekhoapp.android.app.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dekhoapp.android.app.base.R;
//import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by vikas on 1/5/17.
 */

public class Hindi_SongsActivity extends AppCompatActivity
{
    private ListView mlistview;
    private Firebase mRef;
    private ArrayList<String> titles=new ArrayList<String>();
    private String TAG=English_SongsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hindi_songs_list);
        mlistview = (ListView) findViewById(R.id.hindi_songs);

   DatabaseReference hindi= FirebaseDatabase.getInstance().getReference().child("Vikas").child("Music").child("Hindi");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);

        mlistview.setAdapter(arrayAdapter);
       hindi.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                titles.clear();
                for(DataSnapshot hindi_songs: dataSnapshot.getChildren())
                {
                    for (DataSnapshot hindi_songs_children : hindi_songs.getChildren())
                    {
                        if (hindi_songs_children.getKey().equals("title"))
                        {
                            Log.d(TAG, "run");
                            String value = hindi_songs_children.getValue().toString();
                            titles.add(value);
                        }

                    }
                }
                Log.d(TAG,"titles"+titles.size());
                Toast.makeText(Hindi_SongsActivity.this,titles.get(0)+titles.get(1),Toast.LENGTH_LONG).show();
                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError)
            {


            }
        });

       // mlistview.setAdapter(arrayAdapter);


       /* final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        mlistview.setAdapter(arrayAdapter);

        mRef=new Firebase("https://default-demo-app-f823c.firebaseio.com/Vikas/Music/Hindi");
        mRef.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    for (com.firebase.client.DataSnapshot hindi_songs : dataSnapshot.getChildren())
                    {
                        if(hindi_songs.exists())
                        {


                            if (hindi_songs.getKey().equals("title")) {
                                String value = hindi_songs.getValue().toString();
                                titles.add(value);
                            }
                        }


                    }
                    Log.d(TAG,"titles"+titles.size());
                    arrayAdapter.notifyDataSetChanged();
                }
                else
                {
                    Log.d(TAG,"not there");
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError)
            {

                Log.d(TAG,"failed");
            }
        });*/





       /* mRef=new Firebase("https://default-demo-app-f823c.firebaseio.com/Vikas/Music/Hindi/s1");

        mlistview = (ListView) findViewById(R.id.hindi_songs);

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,titles);
        mlistview.setAdapter(arrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                titles.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
       /* DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReferenceFromUrl("https://default-demo-app-f823c.firebaseio.com/Vikas/Music/Hindi/s1");
        FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(
            this,
            String.class,
            android.R.layout.simple_list_item_1,
            mdatabase)
        {
        @Override
        protected void populateView(View v,String model,int position)
        {
            TextView textView=(TextView)findViewById(android.R.id.text1);
            textView.setText(model);

        }



        };
        mlistview.setAdapter(firebaseListAdapter);


    }*/


    }
}
