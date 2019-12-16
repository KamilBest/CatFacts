package com.best.catfacts.ui.fragment.cats_facts_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.best.catfacts.R;
import com.best.catfacts.database.DatabaseHelper;
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
    private static final int NUMBER_OF_RECORDS = 100; //TODO: change to all with pagination
    private CatsFactsListViewModel catsFactsListViewModel;
    private Unbinder unbinder;
    private DatabaseHelper databaseHelper;

    @BindView(R.id.facts_list)
    RecyclerView recyclerView;

    @BindView(R.id.add_to_favourites_btn)
    FloatingActionButton addToFavouritesBtn;

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    public CatsFactsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        catsFactsListViewModel = ViewModelProviders.of(this).get(CatsFactsListViewModel.class);
        subscribeObservers();
        getCatsFactsListApi(NUMBER_OF_RECORDS);
        databaseHelper = new DatabaseHelper(getContext());
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
        setupSwipeRefreshLayout();
        return view;
    }

    private void bindButterknife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    private void setupSwipeRefreshLayout() {
       // setupItemTouchHelper();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getCatsFactsListApi(NUMBER_OF_RECORDS);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.add_to_favourites_btn)
    public void onAddToFavouritesBtnClick() {
        saveSelectedRecordsToDatabase(getSelectedCatsFacts());

    }

    private List<CatsFact> getSelectedCatsFacts() {
        List<CatsFact> allCatsFactList = catsFactsListViewModel.getFacts().getValue();
        List<CatsFact> selectedCatsFacts = new ArrayList<>();
        if (allCatsFactList != null && !allCatsFactList.isEmpty()) {
            for (CatsFact catsFact : allCatsFactList) {
                if (catsFact.isSelected()) {
                    selectedCatsFacts.add(catsFact);
                }
            }
        }
        return selectedCatsFacts;
    }

    private void saveSelectedRecordsToDatabase(List<CatsFact> selectedCatsFacts) {
        for (CatsFact catsFact : selectedCatsFacts) {
            saveDataToDatabase(catsFact.getFact());
        }
        toastMsg("Saved " + selectedCatsFacts.size() + " record/s to favourites. ");
        unselectSavedRecords(selectedCatsFacts);
    }

    private void unselectSavedRecords(List<CatsFact> selectedCatsFacts) {
        for (CatsFact catsFact : selectedCatsFacts)
            catsFact.setSelected(false);
        recyclerView.setAdapter(new CatsFactsListRecyclerViewAdapter(catsFactsListViewModel.getFacts().getValue()));
    }

    private void saveDataToDatabase(String data) {
        boolean insertedData = databaseHelper.addData(data);
        if (insertedData)
            toastMsg("Data successfully inserted!");
        else
            toastMsg("Something went wrong");
    }

    private void toastMsg(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
