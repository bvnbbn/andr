package com.example.rajrajas.bemoapp.Activity;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.rajrajas.bemoapp.Adapter.MovieAdapter;
import com.example.rajrajas.bemoapp.Data.MovieItem;
import com.example.rajrajas.bemoapp.R;
import com.example.rajrajas.bemoapp.databinding.ActivityMainBinding;
import com.example.rajrajas.bemoapp.dummy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajrajas on 5/30/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Dialog dialog;
    private int total_pages;
    private int current_page = 1;
    private List<MovieItem> movie_data;
    private ActivityMainBinding activitymainbinding;
    private RecyclerView movie_recyclerView;
    private String exec = "";
    private Button first_round,second_round,third_round,fourth_round,fifth_round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitymainbinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("Bemo App");
        }

        first_round=(Button) findViewById(R.id.first_id);
        second_round=(Button) findViewById(R.id.second_id);
        third_round=(Button) findViewById(R.id.third_id);
        fourth_round=(Button) findViewById(R.id.fourth_id);
        fifth_round=(Button) findViewById(R.id.fifth_id);

        first_round.setOnClickListener(this);
        second_round.setOnClickListener(this);
        third_round.setOnClickListener(this);
        fourth_round.setOnClickListener(this);
        fifth_round.setOnClickListener(this);


        new Async_class().execute();

    }

    public void prev_bt(View view)
    {
        current_page=current_page-1;
        new Async_class().execute();
    }

    public void next_bt(View view) {
        current_page=current_page+1;
        new Async_class().execute();
    }

    @Override
    public void onClick(View v)
    {
        current_page=Integer.parseInt(((Button)v).getText().toString());
        new Async_class().execute();
    }


    private class Async_class extends AsyncTask<String, String, String> {

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            dialog_Show();
        }

        @Override
        protected String doInBackground(String... f_url) {
            String result;
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                URL post_url = new URL(dummy.movie_url_part + "now_playing" + dummy.Api_key+"&page="+current_page);
                connection = (HttpURLConnection) post_url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString();
                try {
                    generating_data(result);
                } catch (JSONException e) {
                    exec = exec + "json " + e.getMessage();
                    e.printStackTrace();
                }

                return result;
            } catch (IOException e) {
                exec = exec + " " + e.getMessage();
                e.printStackTrace();
                return "Exception";
            }

        }

        protected void onProgressUpdate(String... progress) {

        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            try {
                initRecycler(activitymainbinding.itemList);
                generate_bottom_lay();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private void initRecycler(RecyclerView productsRecycler) {
        productsRecycler.setHasFixedSize(true);
        productsRecycler.setAdapter(new MovieAdapter(MainActivity.this, movie_data));
    }

    void generating_data(String result) throws JSONException
    {
        movie_data = new ArrayList<>();
        JSONArray intial_array = new JSONArray("[" + result + "]");

        JSONObject intial_data = null;
        JSONArray result_array = null;
        for (int i = 0; i < intial_array.length(); i++) {
            intial_data = intial_array.getJSONObject(i);
            current_page= Integer.parseInt(intial_data.getString("page"));
            total_pages = Integer.parseInt(intial_data.getString("total_pages"));
            result_array = new JSONArray(intial_data.getString("results"));
        }

        assert result_array != null;
        for (int i = 0; i < result_array.length(); i++) {
            JSONObject result_Data = result_array.getJSONObject(i);
            MovieItem item = new MovieItem(Integer.parseInt(result_Data.getString("id")), result_Data.getString("title"), result_Data.getString("overview"), result_Data.getString("release_date"), result_Data.getString("poster_path"), result_Data.getString("popularity"), result_Data.getString("vote_count"), result_Data.getString("vote_average"));
            movie_data.add(item);
        }

}

    void dialog_Show() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progressbar);
        dialog.setCancelable(false);

        dialog.show();
    }

    void generate_bottom_lay()
    {
        default_bottom_lay_color();

        if(current_page>3)
        {
            second_round.setVisibility(View.VISIBLE);
            third_round.setVisibility(View.VISIBLE);
            fourth_round.setVisibility(View.GONE);
            fifth_round.setVisibility(View.GONE);

            first_round.setText(current_page-2+"");
            first_round.setVisibility(View.VISIBLE);

            second_round.setText(current_page-1+"");
            third_round.setText(current_page+"");

            third_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.priary_circle));
            third_round.setTextColor(getResources().getColor(R.color.whiteColor));

            if(total_pages>current_page+1)
            {
                fourth_round.setText(current_page + 1 + "");
                fourth_round.setVisibility(View.VISIBLE);
            }
            if(total_pages>current_page+2) {
                fifth_round.setText(current_page + 2 + "");
                fifth_round.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            second_round.setVisibility(View.GONE);
            third_round.setVisibility(View.GONE);
            fourth_round.setVisibility(View.GONE);
            fifth_round.setVisibility(View.GONE);

            if(current_page==1)
            {
                first_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.priary_circle));
                first_round.setTextColor(getResources().getColor(R.color.whiteColor));
            }
            else if (current_page==2)
            {
                second_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.priary_circle));
                second_round.setTextColor(getResources().getColor(R.color.whiteColor));
            }
            else if (current_page==3)
            {
                third_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.priary_circle));
                third_round.setTextColor(getResources().getColor(R.color.whiteColor));
            }

            first_round.setText("1");
            first_round.setVisibility(View.VISIBLE);
            if(total_pages>1)
            {
                second_round.setText("2");
                second_round.setVisibility(View.VISIBLE);
            }
            if(total_pages>2)
            {
                third_round.setText("3");
                third_round.setVisibility(View.VISIBLE);
            }
            if(total_pages>3)
            {
                fourth_round.setText("4");
                fourth_round.setVisibility(View.VISIBLE);
            }
            if(total_pages>4)
            {
                fifth_round.setText("5");
                fifth_round.setVisibility(View.VISIBLE);
            }

        }

        if(current_page==1)
            ((Button) findViewById(R.id.prev_bt)).setVisibility(View.GONE);
        else
            ((Button) findViewById(R.id.prev_bt)).setVisibility(View.VISIBLE);

        if(current_page==total_pages)
            ((Button) findViewById(R.id.next_bt)).setVisibility(View.GONE);
        else
            ((Button) findViewById(R.id.next_bt)).setVisibility(View.VISIBLE);
    }

    void default_bottom_lay_color()
    {
        first_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        second_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        third_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        fourth_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));
        fifth_round.setBackgroundDrawable(getResources().getDrawable(R.drawable.circle));

        first_round.setTextColor(getResources().getColor(R.color.textColor));
        second_round.setTextColor(getResources().getColor(R.color.textColor));
        third_round.setTextColor(getResources().getColor(R.color.textColor));
        fourth_round.setTextColor(getResources().getColor(R.color.textColor));
        fifth_round.setTextColor(getResources().getColor(R.color.textColor));

    }


}
