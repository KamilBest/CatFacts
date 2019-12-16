package com.best.catfacts.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.best.catfacts.api.responses.CatsFactsResponse;
import com.best.catfacts.model.CatsFacts;
import com.best.catfacts.utils.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class CatsFactsApiClient {
    private static final String TAG = CatsFactsApiClient.class.getSimpleName();
    private static CatsFactsApiClient instance;
    private MutableLiveData<List<CatsFacts>> listMutableLiveData;
    private RetrieveCatFactsRunnable retrieveCatFactsRunnable;
    public static final int NETWORK_TIMEOUT = 3000;

    public static CatsFactsApiClient getInstance() {
        if (instance == null)
            instance = new CatsFactsApiClient();
        return instance;
    }

    private CatsFactsApiClient() {
        listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<CatsFacts>> getCatFacts() {
        return listMutableLiveData;
    }

    public void searchCatFactsApi(int limit) {
        if (retrieveCatFactsRunnable != null)
            retrieveCatFactsRunnable = null;
        retrieveCatFactsRunnable = new RetrieveCatFactsRunnable(limit);
        final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveCatFactsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveCatFactsRunnable implements Runnable {

        private int limit;
        boolean cancelRequest;

        public RetrieveCatFactsRunnable() {
            cancelRequest = false;
        }

        public RetrieveCatFactsRunnable(int limit) {
            this.limit = limit;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getCatFacts(limit).execute();
                if (cancelRequest)
                    return;
                if (response.isSuccessful()) {
                    List<CatsFacts> list = new ArrayList<>(((CatsFactsResponse) response.body()).getData());
                    listMutableLiveData.postValue(list);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    listMutableLiveData.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                listMutableLiveData.postValue(null);
            }
        }

        private Call<CatsFactsResponse> getCatFacts(int limit) {
            return RetrofitServiceGenerator.getCatFactsApi().catFactsList(limit);
        }

        private void cancelRequest() {
            Log.d(TAG, "Canceling request");
            cancelRequest = true;
        }
    }
}
