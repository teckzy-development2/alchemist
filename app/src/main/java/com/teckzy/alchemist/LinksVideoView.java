package com.teckzy.alchemist;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class LinksVideoView extends Activity
{
    WebView webView;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        link = getIntent().getStringExtra("link");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }
}
