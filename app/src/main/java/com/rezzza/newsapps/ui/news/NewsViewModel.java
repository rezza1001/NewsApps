package com.rezzza.newsapps.ui.news;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.ErrorCode;
import com.rezzza.newsapps.data.api.model.NewsResponseModel;
import com.rezzza.newsapps.data.api.model.SourceResponseModel;
import com.rezzza.newsapps.data.api.repository.NewsRepository;

public class NewsViewModel extends AndroidViewModel {

   private final NewsRepository repository;
   private LiveData<NewsResponseModel> liveData;
   private Application application;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new NewsRepository();
        liveData = new MutableLiveData<>();

    }

    public void getNews(String sourceId, int page){
        if (!isNetworkConnected()){
            MutableLiveData<NewsResponseModel> data = new MutableLiveData<>();
            data.postValue(initErrorInternet());
            liveData = data;
            return;
        }
        liveData =  repository.getNewsBySource(sourceId,page);

    }
    public void findNews(String sourceId, int page, String find){
        if (!isNetworkConnected()){
            MutableLiveData<NewsResponseModel> data = new MutableLiveData<>();
            data.postValue(initErrorInternet());
            liveData = data;
            return;
        }
        liveData =  repository.findNews(sourceId,page,find);

    }
    public void findAllNews(int page, String find){
        if (!isNetworkConnected()){
            MutableLiveData<NewsResponseModel> data = new MutableLiveData<>();
            data.postValue(initErrorInternet());
            liveData = data;
            return;
        }
        liveData =  repository.findAllNews(page,find);
    }

    private NewsResponseModel initErrorInternet(){
        NewsResponseModel rep =  new NewsResponseModel();
        rep.setCode(ErrorCode.INTERNET_PROBLEM.getCode()+"");
        rep.setMessage(ErrorCode.INTERNET_PROBLEM.getMessage());
        return rep;
    }

    public LiveData<NewsResponseModel> getLiveData() {
        return liveData;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
