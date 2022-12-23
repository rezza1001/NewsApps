package com.rezzza.newsapps.ui.source;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.model.SourceResponseModel;
import com.rezzza.newsapps.data.api.repository.SourceRepository;

public class SourceViewModel extends AndroidViewModel {

   private final SourceRepository repository;
   private LiveData<SourceResponseModel> liveData;

    public SourceViewModel(@NonNull Application application) {
        super(application);

        repository = new SourceRepository();
        liveData = new MutableLiveData<>();

    }

    public void getSource(String category){
        liveData =  repository.getSourceByCategory(category);

    }

    public LiveData<SourceResponseModel> getLiveData() {
        return liveData;
    }
}
