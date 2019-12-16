package com.best.catfacts.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.best.catfacts.api.repository.CatsFactsRepository;
import com.best.catfacts.model.CatsFact;

import java.util.List;

public class CatsFactsListViewModel extends ViewModel {
    private CatsFactsRepository catsFactsRepository;

    public CatsFactsListViewModel() {
        catsFactsRepository = CatsFactsRepository.getInstance();
    }

    public LiveData<List<CatsFact>> getFacts() {
        return catsFactsRepository.getCatFacts();
    }

    public void searchCatFactsApi(int limit)
    {
        catsFactsRepository.searchCatFactsApi(limit);
    }
}
