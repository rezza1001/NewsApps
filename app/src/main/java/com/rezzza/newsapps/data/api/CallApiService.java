package com.rezzza.newsapps.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApiService {

    private Retrofit mRetrofit;

    public Retrofit getClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(100, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(100, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = clientBuilder.build();

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mRetrofit;
    }
}
