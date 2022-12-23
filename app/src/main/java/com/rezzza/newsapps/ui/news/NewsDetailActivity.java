package com.rezzza.newsapps.ui.news;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.view.Loading;

public class NewsDetailActivity extends MyActivity {

    @Override
    protected int setLayout() {
        return R.layout.news_activity_detail;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initLayout() {
        Loading.showLoading(mActivity,"Please wait...");
        WebView wbvw_news = (WebView) findViewById(R.id.wbvw_news);
        wbvw_news.getSettings().setLoadsImagesAutomatically(true);
        wbvw_news.getSettings().setJavaScriptEnabled(true);
        wbvw_news.getSettings().setDomStorageEnabled(true);
        wbvw_news.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wbvw_news.loadUrl(getIntent().getStringExtra("url"));
        wbvw_news.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Loading.cancelLoading();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        new Handler().postDelayed(Loading::cancelLoading,20000);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.mrly_back).setOnClickListener(view -> onBackPressed());
    }
}
