package com.example.rajrajas.bemoapp.Activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rajrajas.bemoapp.R;
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

/**
 * Created by rajrajas on 5/31/2017.
 */

public class MovieDetailsActivity extends AppCompatActivity
{
    private Dialog dialog;
    private String movie_id;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String trailer_id;
    private String url;
    private String async_tab;
    private String first_result;
    private String imdb_key, home_url;

    private ArrayList<String> video_key;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        dynamicToolbarColor();
        movie_id = getIntent().getStringExtra("Movie_id");

        url = dummy.movie_url_part + movie_id + dummy.Api_key;
        async_tab = "first async";
        new Async_class().execute();

    }

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.insignia);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });
    }

    public void play_trailer(View view) {
        call_youtube(trailer_id);
    }

    private void call_youtube(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    public void imdb_call(View view) {
        Intent intent = new Intent(MovieDetailsActivity.this, WebviewActivity.class);
        intent.putExtra("url", "http://www.imdb.com/title/" + imdb_key + "/");
        intent.putExtra("title", "IMDB");
        MovieDetailsActivity.this.startActivity(intent);
    }

    public void home_call(View view) {
        Intent intent = new Intent(MovieDetailsActivity.this, WebviewActivity.class);
        intent.putExtra("url", home_url);
        intent.putExtra("title", "Home Page");
        MovieDetailsActivity.this.startActivity(intent);
    }

    public void chat_Call(View view)
    {
        request_user_name();
    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Enter your name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Intent intent = new Intent(MovieDetailsActivity.this, ChatActivity.class);
                intent.putExtra("movie_id", movie_id);
                intent.putExtra("user", input_field.getText().toString());
                MovieDetailsActivity.this.startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name();
            }
        });

        builder.show();
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

                URL post_url = new URL(url);
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

                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }

        }

        protected void onProgressUpdate(String... progress) {

        }

        @Override
        protected void onPostExecute(String result) {
            if (async_tab.equals("first async")) {
                dialog.dismiss();
                url = dummy.movie_url_part + movie_id + "/videos" + dummy.Api_key;
                first_result = result;
                async_tab = "Second async";
                new Async_class().execute();
            } else {
                dialog.dismiss();
                try {
                    generating_data(first_result, result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    void generating_data(String first_result, String second_result) throws JSONException {
        JSONArray result_array = new JSONArray("[" + first_result + "]");
        JSONObject result_Data = result_array.getJSONObject(0);

        //dialog_Show();
        Glide.with(MovieDetailsActivity.this).load(dummy.Image_url_part + result_Data.getString("backdrop_path"))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        // dialog.dismiss();

                        return false;
                    }

                })
                .into(((ImageView) findViewById(R.id.Movie_img)));

        ((TextView) findViewById(R.id.Movie_ReleaseDate)).setText(result_Data.getString("release_date"));
        ((TextView) findViewById(R.id.Movie_Runningtime)).setText(result_Data.getString("runtime"));
        ((TextView) findViewById(R.id.user_rating)).setText(result_Data.getString("vote_average"));
        ((TextView) findViewById(R.id.vote_count)).setText(result_Data.getString("vote_count"));

        collapsingToolbarLayout.setTitle(result_Data.getString("original_title"));
        ((TextView) findViewById(R.id.Movie_title)).setText(result_Data.getString("original_title"));
        if (!result_Data.getString("tagline").equals("")) {
            ((TextView) findViewById(R.id.Movie_tag)).setText(result_Data.getString("tagline"));
            ((TextView) findViewById(R.id.Movie_tag)).setVisibility(View.VISIBLE);
        }
        ((TextView) findViewById(R.id.Movie_Overview)).setText(result_Data.getString("overview"));
        ((TextView) findViewById(R.id.Budget)).setText(Budget_Cal(result_Data.getString("budget")));
        ((TextView) findViewById(R.id.Status)).setText(result_Data.getString("status"));

        JSONArray language_array = new JSONArray(result_Data.getString("spoken_languages"));
        String Language = "";

        for (int i = 0; i < language_array.length(); i++) {
            JSONObject category_Data = language_array.getJSONObject(i);
            Language = Language + "," + category_Data.getString("name");
        }
        if(!Language.equals(",") && !Language.equals("") )
        ((TextView) findViewById(R.id.Languages)).setText(Language.substring(1, Language.length()));
        else
            ((TextView) findViewById(R.id.Languages)).setText("NA");


        JSONArray category_array = new JSONArray(result_Data.getString("genres"));
        String Categories = "";

        for (int i = 0; i < category_array.length(); i++) {
            JSONObject category_Data = category_array.getJSONObject(i);
            Categories = Categories + "," + category_Data.getString("name");
        }

        if(!Categories.equals(",") && !Categories.equals("") )
        ((TextView) findViewById(R.id.Categories)).setText(Categories.substring(1, Categories.length()));
        else
            ((TextView) findViewById(R.id.Categories)).setText("NA");
        imdb_key = result_Data.getString("imdb_id");
        home_url = result_Data.getString("homepage");


        JSONArray second_json_array = new JSONArray("[" + second_result + "]");
        JSONObject second_json_Data = second_json_array.getJSONObject(0);

        JSONArray second_data_array = new JSONArray(second_json_Data.getString("results"));

        video_key = new ArrayList<>();

        GridLayout l_layout = (GridLayout) findViewById(R.id.Videos_bt);

        int total = second_data_array.length();
        int column = 3;
        int row = total / column;
        l_layout.setColumnCount(3);
        l_layout.setRowCount(row + 1);

        for (int i = 0, c = 0, r = 0; i < second_data_array.length(); i++, c++) {

            final JSONObject second_Data = second_data_array.getJSONObject(i);

            if (second_Data.getString("site").equals("YouTube")) {

                if (c == column) {
                    c = 0;
                    r++;
                }

                if (second_Data.getString("type").equals("Trailer")) {
                    trailer_id = second_Data.getString("key");
                }

                String video_type = second_Data.getString("type");
                final String video_id = second_Data.getString("key");

                Button btn1 = new Button(this);

                video_key.add(video_type);

                int k = 0;

                for (int j = 0; j < video_key.size(); j++) {
                    if (video_key.get(j).equals(video_type)) {
                        k = k + 1;
                    }
                }

                btn1.setText(video_type + "-" + k);
                btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                btn1.setBackgroundResource(R.drawable.square_edit_ripple_effect);

                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = 150;
                param.width = LayoutParams.WRAP_CONTENT;
                param.rightMargin = 5;
                param.topMargin = 5;
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(c);
                param.rowSpec = GridLayout.spec(r);
                btn1.setLayoutParams(param);
                l_layout.addView(btn1);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call_youtube(video_id);
                    }
                });
            }
        }

        ((ImageView) findViewById(R.id.rating_circle)).setAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.rotate_anim));

    }

    void dialog_Show()
    {
        dialog = new Dialog(MovieDetailsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progressbar);
        dialog.setCancelable(false);
        dialog.show();
    }

    String Budget_Cal(String budget)
    {
        if(budget.equals("0"))
        {
            return "NA";
        }
        else
        {
            int bud_temp=Integer.parseInt(budget);
            String budget_string="";
            int i=0;
            while(bud_temp/10!=0)
            {
                i++;
                if(i%3==0)
                budget_string=","+bud_temp%10+budget_string;
                else
                    budget_string=bud_temp%10+budget_string;
                bud_temp=bud_temp/10;
            }

            budget_string=bud_temp%10+budget_string;

            return "$ "+budget_string;
        }
    }


}
