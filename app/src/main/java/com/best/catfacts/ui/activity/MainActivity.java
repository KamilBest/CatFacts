package com.best.catfacts.ui.activity;


import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.best.catfacts.R;
import com.best.catfacts.model.CatsFact;
import com.best.catfacts.ui.TabsPagerAdapter;
import com.best.catfacts.ui.fragment.CatsFactsListFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureTabs();
    }

    private void configureTabs() {
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}
