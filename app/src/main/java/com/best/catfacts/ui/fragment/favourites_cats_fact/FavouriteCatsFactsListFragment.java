package com.best.catfacts.ui.fragment.favourites_cats_fact;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.best.catfacts.R;
import com.best.catfacts.database.DatabaseHelper;
import com.best.catfacts.ui.fragment.cats_facts_list.CatsFactsListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavouriteCatsFactsListFragment extends Fragment {
    private static final String TAG = FavouriteCatsFactsListFragment.class.getSimpleName();
    private Unbinder unbinder;
    private DatabaseHelper databaseHelper;

    @BindView(R.id.facts_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;


    public FavouriteCatsFactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_cats_facts, container, false);
        bindButterknife(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        displayDataFromDatabase();
        setupSwipeRefreshLayout();
        return view;
    }

    private void bindButterknife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void setupSwipeRefreshLayout() {
        setupItemTouchHelper();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            displayDataFromDatabase();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    /*
  ItemTouchHelper to prevent swipeRefreshLayout from refreshing list when swiping
   */
    private void setupItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    swipeRefreshLayout.setEnabled(false);
                } else {
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void displayDataFromDatabase() {
        Cursor data = databaseHelper.getData();
        List<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }

        recyclerView.setAdapter(new FavouritesCatsFactsListRecyclerViewAdapter(listData));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
