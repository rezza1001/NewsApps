package com.rezzza.newsapps.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.category.CategoryActivity;

@SuppressLint("CustomSplashScreen")
public class MainSplashActivity extends MyActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initLayout() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(mActivity, CategoryActivity.class));
            mActivity.finish();
        },2000);
    }

    @Override
    protected void initListener() {

    }
}