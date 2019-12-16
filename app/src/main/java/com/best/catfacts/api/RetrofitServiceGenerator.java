package com.best.catfacts.api;

import com.best.catfacts.api.service.CatFactsApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {
    private static final String BASE_URL = "https://catfact.ninja";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build();
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static CatFactsApi catFactsApi = retrofit.create(CatFactsApi.class);

    public static CatFactsApi getCatFactsApi() {
        return catFactsApi;
    }
}
