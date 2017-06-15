package com.example.rajrajas.bemoapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rajrajas.bemoapp.R;

/**
 * Created by rajrajas on 5/31/2017.
 */

public class WebviewActivity  extends AppCompatActivity
{
    private String url,title;
    private WebView webview;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        title=getIntent().getStringExtra("title");
        url=getIntent().getStringExtra("url");

        webview=(WebView) findViewById(R.id.webview1);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

       // final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(WebviewActivity.this, title, "Loading...");

        webview.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing())
                {
                    progressBar.dismiss();
                }
            }
        });
        webview.loadUrl(url);
    }
}
