package com.rezzza.newsapps.ui.category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.data.api.model.ArticlesModel;
import com.rezzza.newsapps.entities.News;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.news.NewsAdapter;
import com.rezzza.newsapps.ui.news.NewsDetailActivity;
import com.rezzza.newsapps.ui.news.NewsViewModel;
import com.rezzza.newsapps.ui.view.ErrorDialog;
import com.rezzza.newsapps.utility.Utility;

import java.util.ArrayList;
import java.util.Date;

public class FindAllActivity extends MyActivity {
    private TextView txvw_find;
    private EditText edtx_search;
    private ImageView imvw_find;
    private ShimmerFrameLayout shmr_load;

    private final ArrayList<News> listNews = new ArrayList<>();
    private NewsAdapter mAdapter;


    private NewsViewModel mainViewModel;


    @Override
    protected int setLayout() {
        return R.layout.news_activity_find;
    }

    @Override
    protected void initLayout() {

        txvw_find = findViewById(R.id.txvw_find);
        edtx_search = findViewById(R.id.edtx_search);
        imvw_find = findViewById(R.id.imvw_clear);
        shmr_load = findViewById(R.id.shmr_load);
        imvw_find.setVisibility(View.INVISIBLE);

        RecyclerView rcvw_data = findViewById(R.id.rcvw_data);
        rcvw_data.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new NewsAdapter(listNews);
        rcvw_data.setAdapter(mAdapter);


        edtx_search.setHint(getResources().getString(R.string.search_news));

        init();
    }

    @Override
    protected void initListener() {

        mAdapter.setOnSelectedListener(data -> {
            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
            intent.putExtra("url", data.getUrl());
            startActivity(intent);
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
                if (text.isEmpty() && imvw_find.getVisibility() == View.VISIBLE){
                    imvw_find.setVisibility(View.INVISIBLE);
                }
                else if (!text.isEmpty() && imvw_find.getVisibility() == View.INVISIBLE){
                    imvw_find.setVisibility(View.VISIBLE);
                }
            }
        });

        imvw_find.setOnClickListener(view -> loadData());

    }

    @SuppressLint("SetTextI18n")
    private void init(){
        listNews.clear();
        txvw_find.setText("Find All news");

        mainViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData(){
        listNews.clear();

        shmr_load.setVisibility(View.VISIBLE);
        shmr_load.startShimmerAnimation();
        txvw_find.setVisibility(View.INVISIBLE);

        mainViewModel.findAllNews(1,edtx_search.getText().toString().trim());
        mainViewModel.getLiveData().observe(this, newsResponseModel -> {

            if (newsResponseModel == null){
                return;
            }

            if (!newsResponseModel.getCode().equals("ok")){
                showError(newsResponseModel.getMessage());
                hideLoading();
                txvw_find.setText(newsResponseModel.getMessage());
                return;
            }

            for (ArticlesModel article : newsResponseModel.getArticles()){
                News news = new News();
                news.setCategory("All");
                news.setAuthor("");
                news.setTitle(article.getTitle());
                news.setUrl(article.getUrl());
                news.setUrlToImage(article.getUrlToImage());
                news.setDescription(article.getDescription());

                Date date = Utility.getDate(article.getPublishedAt(),"yyyy-MM-dd HH:mm:ss"); //"2022-12-23T02:11:00+00:00
                news.setPublishedAt(Utility.getDateString(date,"EEEE, dd MMMM yyyy HH:mm"));
                news.setPublishDate(date);
                listNews.add(news);
            }
            hideLoading();
            mAdapter.notifyDataSetChanged();
            if (listNews.size() == 0){
                txvw_find.setVisibility(View.VISIBLE);
                String note = edtx_search.getText()+" Not found";
                txvw_find.setText(note);
            }
        });
    }

    private void hideLoading(){
        if (shmr_load.getVisibility() == View.VISIBLE){
            shmr_load.setVisibility(View.GONE);
            shmr_load.stopShimmerAnimation();
        }
    }

    private void showError(String message){
        ErrorDialog dialog = new ErrorDialog(mActivity);
        dialog.show("Failed API", message);
    }
}
