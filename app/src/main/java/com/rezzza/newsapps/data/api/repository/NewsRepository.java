package com.rezzza.newsapps.data.api.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezzza.newsapps.data.api.ApiConfig;
import com.rezzza.newsapps.data.api.CallApiService;
import com.rezzza.newsapps.data.api.ErrorCode;
import com.rezzza.newsapps.data.api.model.NewsResponseModel;
import com.rezzza.newsapps.data.api.usecase.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private final ApiInterface apiInterface;
    private  MutableLiveData<NewsResponseModel> data;

    public NewsRepository(){
        CallApiService callApiService = new CallApiService();
        apiInterface = callApiService.getClient().create(ApiInterface.class);
    }

    public LiveData<NewsResponseModel> getNewsBySource(String source, int page){
        data = new MutableLiveData<>();

        int pageSize = 10;
        String url = ApiConfig.GET_NEWS +
                "?apiKey="+ApiConfig.API_KEY
                +"&sources="+source
                +"&page="+page
                +"&pageSize="+ pageSize;
        apiInterface.getNews(url).enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                NewsResponseModel rep =  response.body();
                ErrorCode code = ErrorCode.map(response.code());

                if (rep == null){
                    rep = new NewsResponseModel();
                    rep.setCode(code.getCode()+"");
                    rep.setMessage(code.getMessage());
                    data.postValue(rep);
                    return;
                }
                rep.setCode(code.getMessage());
                data.postValue(rep);
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Log.e("HomeRepository","onFailure");
                NewsResponseModel rep = new NewsResponseModel();
                rep.setCode("failure");
                rep.setMessage("Unknown response message");
                data.postValue(rep);
            }
        });

        return data;
    }

    public LiveData<NewsResponseModel> findNews(String source, int page, String find){
        data = new MutableLiveData<>();

        int pageSize = 50;
        String url = ApiConfig.GET_NEWS +
                "?apiKey="+ApiConfig.API_KEY
                +"&sources="+source
                +"&q="+find
                +"&page="+page
                +"&pageSize="+ pageSize;
        apiInterface.getNews(url).enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                NewsResponseModel rep =  response.body();
                ErrorCode code = ErrorCode.map(response.code());

                if (rep == null){
                    rep = new NewsResponseModel();
                    rep.setCode(code.getCode()+"");
                    rep.setMessage(code.getMessage());
                    data.postValue(rep);
                    return;
                }
                rep.setCode(code.getMessage());
                data.postValue(rep);
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Log.e("HomeRepository","onFailure");
                NewsResponseModel rep = new NewsResponseModel();
                rep.setCode("failure");
                rep.setMessage("Unknown response message");
                data.postValue(rep);
            }
        });

        return data;
    }
    public LiveData<NewsResponseModel> findAllNews(int page, String find){
        data = new MutableLiveData<>();

        int pageSize = 30;
        String url = ApiConfig.GET_NEWS +
                "?apiKey="+ApiConfig.API_KEY
                +"&q="+find
                +"&page="+page
                +"&pageSize="+ pageSize;
        apiInterface.getNews(url).enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                NewsResponseModel rep =  response.body();
                ErrorCode code = ErrorCode.map(response.code());

                if (rep == null){
                    rep = new NewsResponseModel();
                    rep.setCode(code.getCode()+"");
                    rep.setMessage(code.getMessage());
                    data.postValue(rep);
                    return;
                }
                rep.setCode(code.getMessage());
                data.postValue(rep);
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Log.e("HomeRepository","onFailure");
                NewsResponseModel rep = new NewsResponseModel();
                rep.setCode("failure");
                rep.setMessage("Unknown response message");
                data.postValue(rep);
            }
        });

        return data;
    }
}
