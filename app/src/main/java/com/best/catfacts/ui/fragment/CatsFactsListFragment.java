package com.best.catfacts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.best.catfacts.R;
import com.best.catfacts.model.CatsFact;
import com.best.catfacts.viewmodel.CatsFactsListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CatsFactsListFragment extends Fragment {
    private static final String TAG = CatsFactsListFragment.class.getSimpleName();
    private CatsFactsListViewModel catsFactsListViewModel;
    private Unbinder unbinder;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.add_to_favourites_btn)
    FloatingActionButton addToFavouritesBtn;

    public CatsFactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catsFactsListViewModel = ViewModelProviders.of(this).get(CatsFactsListViewModel.class);
        subscribeObservers();
        final int NUMBER_OF_RECORDS = 100; //TODO: change to all with pagination
        getCatsFactsListApi(NUMBER_OF_RECORDS);
    }

    private void getCatsFactsListApi(int limit) {
        catsFactsListViewModel.searchCatFactsApi(limit);
    }


    private void subscribeObservers() {
        catsFactsListViewModel.getFacts().observe(this, catsFactList -> {
            if (catsFactList != null) {
                recyclerView.setAdapter(new CatsFactsListRecyclerViewAdapter(catsFactList));
                Log.d(TAG, catsFactList.toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cats_facts_list, container, false);
        bindButterknife(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void bindButterknife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.add_to_favourites_btn)
    public void onAddToFavouritesBtnClick() {
        saveSelectedCatsFacts();
    }

    private void saveSelectedCatsFacts() {
        List<CatsFact> allCatsFactList = catsFactsListViewModel.getFacts().getValue();
        List<CatsFact> selectedCatsFacts = new ArrayList<>();
        if (allCatsFactList != null && !allCatsFactList.isEmpty()) {
            for (CatsFact catsFact : allCatsFactList) {
                if (catsFact.isSelected()) {
                    selectedCatsFacts.add(catsFact);
                }
            }
        }
        Toast.makeText(getContext(), "Saved " + selectedCatsFacts.size() + " record/s to favourites. ",
                Toast.LENGTH_LONG).show();
        unselectSavedRecords(selectedCatsFacts);

    }

    private void unselectSavedRecords(List<CatsFact> selectedCatsFacts) {
        for (CatsFact catsFact : selectedCatsFacts)
            catsFact.setSelected(false);
        recyclerView.setAdapter(new CatsFactsListRecyclerViewAdapter(catsFactsListViewModel.getFacts().getValue()));

    }

}
