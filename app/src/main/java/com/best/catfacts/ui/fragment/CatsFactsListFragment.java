package com.best.catfacts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
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

    public static CatsFactsListFragment newInstance() {
        CatsFactsListFragment fragment = new CatsFactsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catsFactsListViewModel = ViewModelProviders.of(this).get(CatsFactsListViewModel.class);
        subscribeObservers();
        catsFactsListViewModel.searchCatFactsApi(10);
    }

    private void searchCatFactsApi(int limit) {
        catsFactsListViewModel.searchCatFactsApi(limit);
    }


    private void subscribeObservers() {
        catsFactsListViewModel.getFacts().observe(this, new Observer<List<CatsFact>>() {
            @Override
            public void onChanged(List<CatsFact> catsFactList) {
                if (catsFactList != null) {
                        recyclerView.setAdapter(new CatsFactsListRecyclerViewAdapter(catsFactList, mListener));
                }
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