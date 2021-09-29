package com.lgajic.primerrss.web;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    private static final String BASE_URL = "www.kurir.rs";

    private Context context;

    public MyWebViewClient(Context context) {
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (request.getUrl().getHost().equals(BASE_URL)) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
        context.startActivity(intent);
        return true;
    }
}
