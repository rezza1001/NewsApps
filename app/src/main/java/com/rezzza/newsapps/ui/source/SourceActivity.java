package com.rezzza.newsapps.ui.source;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.data.api.model.SourceDataModel;
import com.rezzza.newsapps.entities.Category;
import com.rezzza.newsapps.entities.Source;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.view.SearchView;

import java.util.ArrayList;

public class SourceActivity extends MyActivity {

    private SearchView scvw_search;
    private TextView txvw_header;

    private ShimmerFrameLayout shmr_load;
    private final ArrayList<Source> listSource = new ArrayList<>();
    private final ArrayList<Source> listSourceFilter = new ArrayList<>();
    private Category mCategory;
    private SourceAdapter mAdapter;

    private SourceViewModel sourceViewModel;

    @Override
    protected int setLayout() {
        return R.layout.source_activity;
    }

    @Override
    protected void initLayout() {
        scvw_search = findViewById(R.id.scvw_search);
        scvw_search.createWithTyping(getResources().getString(R.string.search_source));

        txvw_header = findViewById(R.id.txvw_header);
        shmr_load   = findViewById(R.id.shmr_load);

        RecyclerView rcvw_source = findViewById(R.id.rcvw_source);
        rcvw_source.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new SourceAdapter(listSourceFilter);
        rcvw_source.setAdapter(mAdapter);

        shmr_load.startShimmerAnimation();
        init();
    }

    @Override
    protected void initListener() {


        scvw_search.setonSearchListener(this::filter);


        mAdapter.setOnSelectedListener(data -> {

        });

        findViewById(R.id.mrly_back).setOnClickListener(view -> onBackPressed());

    }

    private void init(){
        listSource.clear();
        mCategory = (Category) getIntent().getSerializableExtra("category");
        txvw_header.setText(mCategory.getName());

        sourceViewModel = new ViewModelProvider(this).get(SourceViewModel.class);
        loadData();
    }

    private void loadData(){
        sourceViewModel.getSource(mCategory.getName());
        sourceViewModel.getLiveData().observe(this, sourceResponseModel -> {
            if (sourceResponseModel == null){
                return;
            }

            for (SourceDataModel data : sourceResponseModel.getSources()){
                Source source = new Source();
                source.setCategory(data.getCategory());
                source.setName(data.getName());
                source.setId(data.getId());
                source.setDescription(data.getDescription());
                listSource.add(source);
            }

            if (shmr_load.getVisibility() == View.VISIBLE){
                shmr_load.setVisibility(View.GONE);
                shmr_load.stopShimmerAnimation();
            }
            filter("");

        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String text){
        listSourceFilter.clear();
        if (text.isEmpty()){
            listSourceFilter.addAll(listSource);
        }
        else {
            for (Source source : listSource){
                if (source.getName().toLowerCase().contains(text.toLowerCase())){
                    listSourceFilter.add(source);
                }
            }
        }

        mAdapter.notifyDataSetChanged();
    }
}
