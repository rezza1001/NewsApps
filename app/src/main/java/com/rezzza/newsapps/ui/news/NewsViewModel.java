package com.rezzza.newsapps.ui.news;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.model.NewsResponseModel;
import com.rezzza.newsapps.data.api.repository.NewsRepository;

public class NewsViewModel extends AndroidViewModel {

   private final NewsRepository repository;
   private LiveData<NewsResponseModel> liveData;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        repository = new NewsRepository();
        liveData = new MutableLiveData<>();

    }

    public void getNews(String sourceId, int page){
        liveData =  repository.getNewsBySource(sourceId,page);

    }
    public void findNews(String sourceId, int page, String find){
        liveData =  repository.findNews(sourceId,page,find);

    }
    public void findAllNews(int page, String find){
        liveData =  repository.findAllNews(page,find);

    }

    public LiveData<NewsResponseModel> getLiveData() {
        return liveData;
    }
}
