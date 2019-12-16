package com.best.catfacts;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.best.catfacts.model.CatsFacts;
import com.best.catfacts.viewmodel.CatsFactsListViewModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private CatsFactsListViewModel catsFactsListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        catsFactsListViewModel = ViewModelProviders.of(this).get(CatsFactsListViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        catsFactsListViewModel.getFacts().observe(this, new Observer<List<CatsFacts>>() {
            @Override
            public void onChanged(List<CatsFacts> catsFactsList) {
                if (catsFactsList != null) {
                    for (CatsFacts catsFacts : catsFactsList)
                        Log.d(TAG, "onChanged: " + catsFacts.getFact());
                }
            }
        });
    }

    private void searchCatFactsApi(int limit) {
        catsFactsListViewModel.searchCatFactsApi(limit);
    }

    private void testRetrofitRequest() {
        final int NUMBER_OF_FACTS = 10;
        searchCatFactsApi(NUMBER_OF_FACTS);
    }

    @OnClick(R.id.test_retrofit_button)
    public void onButtonClick() {
        testRetrofitRequest();
    }
}
