package com.rezzza.newsapps.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyView;

public class SearchView extends MyView {

    private TextView txvw_search;
    private MaterialRippleLayout mrly_serach;

    private OnSearchListener onSearchListener;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int setlayout() {
        return R.layout.view_search;
    }

    @Override
    protected void initLayout() {
        txvw_search = findViewById(R.id.txvw_search);
        mrly_serach = findViewById(R.id.mrly_serach);
    }

    @Override
    protected void initListener() {
        mrly_serach.setOnClickListener(view -> {
            if (onSearchListener != null){
                onSearchListener.onAction();
            }
        });
    }

    public void create(String title){
        super.create();
        txvw_search.setText(title);
    }

    public void setOnSearchListener(OnSearchListener listener){
        onSearchListener = listener;
    }

    public interface OnSearchListener{
        void onAction();
    }



}
