package com.rezzza.newsapps.ui.source;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.view.SearchView;
import com.rezzza.newsapps.entities.Category;

public class SourceActivity extends MyActivity {

    private SearchView scvw_search;
    private TextView txvw_header;

    private Category mCategory;

    @Override
    protected int setLayout() {
        return R.layout.source_activity;
    }

    @Override
    protected void initLayout() {
        scvw_search = findViewById(R.id.scvw_search);
        scvw_search.create(getResources().getString(R.string.search_source));

        txvw_header = findViewById(R.id.txvw_header);

        RecyclerView rcvw_source = findViewById(R.id.rcvw_source);
        rcvw_source.setLayoutManager(new LinearLayoutManager(mActivity));

        init();
    }

    @Override
    protected void initListener() {
        scvw_search.setOnSearchListener(() -> {

        });

        findViewById(R.id.mrly_back).setOnClickListener(view -> onBackPressed());
    }

    private void init(){
        mCategory = (Category) getIntent().getSerializableExtra("category");
        txvw_header.setText(mCategory.getName());
    }
}
