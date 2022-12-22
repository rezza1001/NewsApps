package com.rezzza.newsapps.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class MyActivity extends AppCompatActivity {

    protected AppCompatActivity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mActivity = this;
        initLayout();
        initListener();
    }

    protected abstract int setLayout();
    protected abstract void initLayout();
    protected abstract void initListener();
}
