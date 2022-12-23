package com.rezzza.newsapps.ui.source;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rezzza.newsapps.R;
import com.rezzza.newsapps.data.api.model.SourceDataModel;
import com.rezzza.newsapps.entities.Category;
import com.rezzza.newsapps.entities.Source;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.view.Loading;

import java.util.ArrayList;

public class FindActivity extends MyActivity {
    private TextView txvw_find;
    private EditText edtx_search;
    private ImageView imvw_clear;

    private ArrayList<Source> listSource = new ArrayList<>();
    private Category mCategory;
    private SourceAdapter mAdapter;


    private SourceViewModel sourceViewModel;


    @Override
    protected int setLayout() {
        return R.layout.source_activity_find;
    }

    @Override
    protected void initLayout() {

        txvw_find = findViewById(R.id.txvw_find);
        edtx_search = findViewById(R.id.edtx_search);
        imvw_clear = findViewById(R.id.imvw_clear);
        imvw_clear.setVisibility(View.INVISIBLE);

        RecyclerView rcvw_data = findViewById(R.id.rcvw_data);
        rcvw_data.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new SourceAdapter(listSource);
        rcvw_data.setAdapter(mAdapter);

        init();
    }

    @Override
    protected void initListener() {

        mAdapter.setOnSelectedListener(data -> {

        });

        findViewById(R.id.mrly_back).setOnClickListener(view -> onBackPressed());
        edtx_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.isEmpty() && imvw_clear.getVisibility() == View.VISIBLE){
                    imvw_clear.setVisibility(View.INVISIBLE);
                }
                else if (!text.isEmpty() && imvw_clear.getVisibility() == View.INVISIBLE){
                    imvw_clear.setVisibility(View.VISIBLE);
                }
            }
        });

        imvw_clear.setOnClickListener(view -> edtx_search.setText(null));

    }

    private void init(){
        listSource.clear();
        mCategory = (Category) getIntent().getSerializableExtra("category");
        String sNote = getResources().getString(R.string.find_source_from_sport).replace("X1", mCategory.getName());
        txvw_find.setText(sNote);

        sourceViewModel = new ViewModelProvider(this).get(SourceViewModel.class);


        loadData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData(){
        Loading.showLoading(mActivity, "Please wait..");
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
            Loading.cancelLoading();
            mAdapter.notifyDataSetChanged();
        });
    }
}
