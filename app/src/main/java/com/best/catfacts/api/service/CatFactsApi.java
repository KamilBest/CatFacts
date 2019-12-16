package com.best.catfacts.api.service;

import com.best.catfacts.api.responses.CatsFactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatFactsApi {

    @GET("/facts")
    Call<CatsFactsResponse> catFactsList(@Query("limit") int limit);
}
