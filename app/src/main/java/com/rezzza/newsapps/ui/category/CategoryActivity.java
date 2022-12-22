package com.rezzza.newsapps.ui.category;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.source.SourceActivity;
import com.rezzza.newsapps.ui.view.SearchView;
import com.rezzza.newsapps.entities.Category;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryActivity extends MyActivity {

    protected SearchView scvw_search;

    ArrayList<Category> listCategory = new ArrayList<>();
    protected CategoryAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.category_activity;
    }

    @Override
    protected void initLayout() {
        scvw_search = findViewById(R.id.scvw_search);
        scvw_search.create(getResources().getString(R.string.search_news));

        RecyclerView rcvw_category = findViewById(R.id.rcvw_category);
        rcvw_category.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mAdapter = new CategoryAdapter(listCategory);
        rcvw_category.setAdapter(mAdapter);

        loadData();
    }

    @Override
    protected void initListener() {
        scvw_search.setOnSearchListener(() -> {

        });

        mAdapter.setOnSelectedListener(data -> {
            Intent intent = new Intent(mActivity, SourceActivity.class);
            intent.putExtra("category", data);
            startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData(){
        listCategory.addAll(Arrays.asList(Category.values()));
        mAdapter.notifyDataSetChanged();
    }

}
