package com.rezzza.newsapps.data.api.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.ApiConfig;
import com.rezzza.newsapps.data.api.CallApiService;
import com.rezzza.newsapps.data.api.model.SourceResponseModel;
import com.rezzza.newsapps.data.api.usecase.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceRepository {

    private final ApiInterface apiInterface;
    private  MutableLiveData<SourceResponseModel> data;

    public SourceRepository(){
        CallApiService callApiService = new CallApiService();
        apiInterface = callApiService.getClient().create(ApiInterface.class);
    }

    public LiveData<SourceResponseModel> getSourceByCategory(String category){
        data = new MutableLiveData<>();

        String url = ApiConfig.GET_SOURCE+"?apiKey="+ApiConfig.API_KEY+"&category="+category;
        apiInterface.getSource(url).enqueue(new Callback<SourceResponseModel>() {
            @Override
            public void onResponse(Call<SourceResponseModel> call, Response<SourceResponseModel> response) {
                SourceResponseModel rep =  response.body();
                if (response.code() == 200 &&  rep!=null){
                    data.postValue(rep);
                }
                else {
                    Log.e("HomeRepository","response "+response.code());
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<SourceResponseModel> call, Throwable t) {
                Log.e("HomeRepository","onFailure");
                data.postValue(null);
            }
        });

        return data;
    }
}
