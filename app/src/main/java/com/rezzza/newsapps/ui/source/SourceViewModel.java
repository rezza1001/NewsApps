package com.rezzza.newsapps.ui.source;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.ErrorCode;
import com.rezzza.newsapps.data.api.model.SourceResponseModel;
import com.rezzza.newsapps.data.api.repository.SourceRepository;

public class SourceViewModel extends AndroidViewModel {

   private final SourceRepository repository;
   private LiveData<SourceResponseModel> liveData;
   private Application application;

    public SourceViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new SourceRepository();
        liveData = new MutableLiveData<>();

    }

    public void getSource(String category){
        if (!isNetworkConnected()){
            MutableLiveData<SourceResponseModel> data = new MutableLiveData<>();
            SourceResponseModel rep =  new SourceResponseModel();
            rep.setCode(ErrorCode.INTERNET_PROBLEM.getCode()+"");
            rep.setMessage(ErrorCode.INTERNET_PROBLEM.getMessage());
            data.postValue(rep);
            liveData = data;
            return;
        }
        liveData =  repository.getSourceByCategory(category);

    }

    public LiveData<SourceResponseModel> getLiveData() {
        return liveData;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
