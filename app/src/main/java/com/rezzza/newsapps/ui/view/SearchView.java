package com.rezzza.newsapps.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyView;

public class SearchView extends MyView {

    private TextView txvw_search;
    private EditText edtx_search;
    private ImageView imvw_clear;
    private MaterialRippleLayout mrly_serach;

    private OnActionListener onActionListener;
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
        edtx_search = findViewById(R.id.edtx_search);
        imvw_clear = findViewById(R.id.imvw_clear);

        edtx_search.setVisibility(GONE);
        imvw_clear.setVisibility(GONE);
        txvw_search.setVisibility(VISIBLE);
    }

    @Override
    protected void initListener() {
        mrly_serach.setOnClickListener(view -> {
            if (onActionListener != null){
                onActionListener.onAction();
            }
        });

        edtx_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String text = editable.toString().trim();
                if (text.isEmpty() && imvw_clear.getVisibility() == View.VISIBLE){
                    imvw_clear.setVisibility(View.GONE);
                }
                else if (!text.isEmpty() && imvw_clear.getVisibility() == View.GONE){
                    imvw_clear.setVisibility(View.VISIBLE);
                }

                if (onSearchListener != null){
                    onSearchListener.onSearch(text);
                }
            }
        });


        imvw_clear.setOnClickListener(view -> edtx_search.setText(null));
    }

    public void create(String title){
        super.create();
        txvw_search.setText(title);
    }

    public void createWithTyping(String hint){
        super.create();
        edtx_search.setVisibility(VISIBLE);
        txvw_search.setVisibility(GONE);
        mrly_serach.setVisibility(GONE);
        edtx_search.setHint(hint);
    }

    public void setOnActionListener(OnActionListener listener){
        onActionListener = listener;
    }

    public interface OnActionListener {
        void onAction();
    }


    public void setonSearchListener(OnSearchListener onSearchListener){
        this.onSearchListener = onSearchListener;
    }
    public interface OnSearchListener {
        void onSearch(String text);
    }



}
