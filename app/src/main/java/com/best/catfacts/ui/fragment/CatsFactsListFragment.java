package com.best.catfacts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.best.catfacts.R;
import com.best.catfacts.model.CatsFact;
import com.best.catfacts.viewmodel.CatsFactsListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CatsFactsListFragment extends Fragment {
    private static final String TAG = CatsFactsListFragment.class.getSimpleName();
    private CatsFactsListViewModel catsFactsListViewModel;

    private OnListFragmentInteractionListener mListener;
    private Unbinder unbinder;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    public CatsFactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catsFactsListViewModel = ViewModelProviders.of(this).get(CatsFactsListViewModel.class);
        subscribeObservers();
        final int NUMBER_OF_RECORDS = 100; //TODO: change to all with pagination
        catsFactsListViewModel.searchCatFactsApi(NUMBER_OF_RECORDS);
    }

    private void getCatsFactsListApi(int limit) {
        catsFactsListViewModel.searchCatFactsApi(limit);
    }


    private void subscribeObservers() {
        catsFactsListViewModel.getFacts().observe(this, catsFactList -> {
            if (catsFactList != null) {
                recyclerView.setAdapter(new CatsFactsListRecyclerViewAdapter(catsFactList, mListener));
                Log.d(TAG, catsFactList.toString());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catsfactslist_list, container, false);
        bindButterknife(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void bindButterknife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(CatsFact catsFact);
    }

}
