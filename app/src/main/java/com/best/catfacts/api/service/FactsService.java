package com.best.catfacts.api.service;

import com.best.catfacts.api.model.CatsFactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactsService {
    @GET("/facts")
    Call<CatsFactsResponse> getCatsFacts(); //limit
}
