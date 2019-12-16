package com.best.catfacts.ui.activity;


import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.best.catfacts.R;
import com.best.catfacts.model.CatsFact;
import com.best.catfacts.ui.fragment.CatsFactsListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CatsFactsListFragment.OnListFragmentInteractionListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.fragment)
    RelativeLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openFragment();
    }

    private void openFragment()
    {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment, new CatsFactsListFragment()).
                commit();
    }

    @Override
    public void onListFragmentInteraction(CatsFact catsFact) {

    }
}
