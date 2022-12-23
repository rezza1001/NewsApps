package com.rezzza.newsapps.ui.view;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyDialog;

public class ErrorDialog extends MyDialog {

    private TextView txvw_title,txvw_description;
    private CardView card_body;

    public ErrorDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int setLayout() {
        return R.layout.view_dialog_error;
    }

    @Override
    protected void initLayout(View view) {

        view.findViewById(R.id.mrly_action).setOnClickListener(view1 -> dismiss());

        txvw_title = view.findViewById(R.id.txvw_title);
        txvw_description = view.findViewById(R.id.txvw_description);
        card_body = view.findViewById(R.id.card_body);
        card_body.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        dismiss();
    }

    public void show(String title, String description){
        show();
        txvw_title.setText(title);
        txvw_description.setText(description);

        card_body.setVisibility(View.VISIBLE);
        card_body.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.zoom_in));
    }
}
