package com.rezzza.newsapps.ui.view;

import android.app.ProgressDialog;
import android.content.Context;

public class Loading {
    private static ProgressDialog progressDialog;

    public static void showLoading(Context p_cContext, String msg)  {
        cancelLoading();
        progressDialog = new ProgressDialog(p_cContext);
        progressDialog.setTitle(null);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        if (progressDialog != null){
            progressDialog.show();
        }
    }

    public static void cancelLoading()
    {
        if (progressDialog != null)
        {
            progressDialog.cancel();
            progressDialog = null;
        }
    }
}