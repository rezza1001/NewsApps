package com.rezzza.newsapps.data.api.usecase;

import com.rezzza.newsapps.data.api.model.SourceResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    public Call<SourceResponseModel> getSource(@Url String url );

}
