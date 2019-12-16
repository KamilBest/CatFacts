package com.best.catfacts.api.repository;

import androidx.lifecycle.LiveData;

import com.best.catfacts.api.CatsFactsApiClient;
import com.best.catfacts.model.CatsFacts;

import java.util.List;

public class CatsFactsRepository {
    private static CatsFactsRepository instance;
    private CatsFactsApiClient catsFactsApiClient;

    public static CatsFactsRepository getInstance() {
        if (instance == null)
            instance = new CatsFactsRepository();
        return instance;
    }

    private CatsFactsRepository() {
        catsFactsApiClient = CatsFactsApiClient.getInstance();
    }

    public LiveData<List<CatsFacts>> getCatFacts() {
        return catsFactsApiClient.getCatFacts();
    }

    public void searchCatFactsApi(int limit)
    {
        catsFactsApiClient.searchCatFactsApi(limit);
    }
}
