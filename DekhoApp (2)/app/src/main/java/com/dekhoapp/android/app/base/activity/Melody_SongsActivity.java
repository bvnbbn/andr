package com.dekhoapp.android.app.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dekhoapp.android.app.base.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by vikas on 1/5/17.
 */

public class Melody_SongsActivity extends AppCompatActivity {
    private ListView mlistview;
    private Firebase mRef;
    private ArrayList<String> titles = new ArrayList<String>();
    private String TAG = Melody_SongsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.melody_songs);
        mlistview = (ListView) findViewById(R.id.melod_songs);

        DatabaseReference hindi = FirebaseDatabase.getInstance().getReference().child("Vikas").child("Melody");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        mlistview.setAdapter(arrayAdapter);

        hindi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot hindi_songs : dataSnapshot.getChildren()) {
                    for (DataSnapshot hindi_songs_children : hindi_songs.getChildren()) {
                        if (hindi_songs_children.getKey().equals("title")) {
                            Log.d(TAG, "run");
                            String value = hindi_songs_children.getValue().toString();
                            titles.add(value);
                        }

                    }
                }
                Log.d(TAG, "titles" + titles.size());
                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        mlistview.setAdapter(arrayAdapter);
    }
}