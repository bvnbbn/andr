package com.dekhoapp.android.app.base.activity;

/**
 * Created by vikas on 30/4/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.dekhoapp.android.app.base.R;
import com.dekhoapp.android.app.base.adapter.GalleryAdapter;

public class GridLayoutActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_content);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new GalleryAdapter(this));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                if(position==0)
                    {
                        Toast.makeText(GridLayoutActivity.this, "" + position,
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(GridLayoutActivity.this, Hindi_SongsActivity.class);
                        startActivity(i);
                    }

                if(position==1)
                {
                    Intent i = new Intent(getApplicationContext(), English_SongsActivity.class);
                    startActivity(i);
                }
                if(position==2)
                {
                    Intent i = new Intent(getApplicationContext(), Melody_SongsActivity.class);
                    startActivity(i);
                }

            }
        });



    }
}