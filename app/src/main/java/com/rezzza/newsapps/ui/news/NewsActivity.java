package com.rezzza.newsapps.ui.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.data.api.model.ArticlesModel;
import com.rezzza.newsapps.entities.News;
import com.rezzza.newsapps.entities.Source;
import com.rezzza.newsapps.ui.base.MyActivity;
import com.rezzza.newsapps.ui.view.ErrorDialog;
import com.rezzza.newsapps.utility.Utility;

import java.util.ArrayList;
import java.util.Date;

public class NewsActivity extends MyActivity {

    private TextView txvw_header;
    private RecyclerView rcvw_news;
    private ShimmerFrameLayout shmr_load;

    private final ArrayList<News> listNews = new ArrayList<>();
    private NewsAdapter mAdapter;
    private Source mSource;

    private NewsViewModel mViewModel;
    private int mPage = 1;
    private boolean isLoading = false;

    @Override
    protected int setLayout() {
        return R.layout.news_activity;
    }

    @Override
    protected void initLayout() {
        txvw_header = findViewById(R.id.txvw_header);
        shmr_load = findViewById(R.id.shmr_load);

        rcvw_news = findViewById(R.id.rcvw_news);
        rcvw_news.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new NewsAdapter(listNews);
        rcvw_news.setAdapter(mAdapter);

        init();

    }

    @Override
    protected void initListener() {
        findViewById(R.id.mrly_back).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.mrly_search).setOnClickListener(view -> {
            Intent intent = new Intent(mActivity, FindActivity.class);
            intent.putExtra("data", mSource);
            startActivity(intent);
        });

        rcvw_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) rcvw_news.getLayoutManager();
                if (!isLoading){
                    if (layoutManager != null  && layoutManager.findLastCompletelyVisibleItemPosition() == listNews.size() - 1) {

                        News news = new News();
                        news.setLoading(true);
                        listNews.add(news);
                        mAdapter.notifyItemInserted(listNews.size());

                        loadData();
                        isLoading = true;
                    }
                }
            }
        });

        mAdapter.setOnSelectedListener(data -> {
            Intent intent = new Intent(mActivity, NewsDetailActivity.class);
            intent.putExtra("url", data.getUrl());
            startActivity(intent);
        });
    }

    private void init(){
        mSource = (Source) getIntent().getSerializableExtra("data");
        txvw_header.setText(mSource.getName());

        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        shmr_load.setVisibility(View.VISIBLE);
        shmr_load.startShimmerAnimation();

        loadData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadData(){
        mViewModel.getNews(mSource.getId(), mPage);
        mViewModel.getLiveData().observe(this, newsResponseModel -> {
            if (listNews.size() > 0){
                listNews.remove(listNews.size() -1);
                mAdapter.notifyItemRemoved(listNews.size());
            }

            if (newsResponseModel == null){
                hideLoading();
                return;
            }
            if (!newsResponseModel.getCode().equals("ok")){
                showError(newsResponseModel.getMessage());
                hideLoading();
                return;
            }

            for (ArticlesModel article : newsResponseModel.getArticles()){
                News news = new News();
                news.setCategory(mSource.getCategory());
                news.setAuthor(mSource.getName());
                news.setTitle(article.getTitle());
                news.setUrl(article.getUrl());
                news.setUrlToImage(article.getUrlToImage());
                news.setDescription(article.getDescription());

                Date date = Utility.getDate(article.getPublishedAt(),"yyyy-MM-dd HH:mm:ss"); //"2022-12-23T02:11:00+00:00
                news.setPublishedAt(Utility.getDateString(date,"EEEE, dd MMMM yyyy HH:mm"));
                news.setPublishDate(date);
                listNews.add(news);
            }
            if (newsResponseModel.getTotalResults() > listNews.size()){
                mPage ++;
                isLoading = false;
            }
            hideLoading();
            mAdapter.notifyDataSetChanged();
        });
    }

    private void showError(String message){
        ErrorDialog dialog = new ErrorDialog(mActivity);
        dialog.show("Failed API", message);
    }

    private void hideLoading(){
        if (shmr_load.getVisibility() == View.VISIBLE){
            shmr_load.setVisibility(View.GONE);
            shmr_load.stopShimmerAnimation();
        }
    }
}
