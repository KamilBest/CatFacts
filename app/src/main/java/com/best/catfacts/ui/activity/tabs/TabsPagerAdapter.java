package com.best.catfacts.ui.activity.tabs;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.best.catfacts.R;
import com.best.catfacts.ui.fragment.cats_facts_list.CatsFactsListFragment;
import com.best.catfacts.ui.fragment.favourites_cats_fact.FavouriteCatsFactsListFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private final int NUMBER_OF_TABS = 2;
    @StringRes
    private static final int[] TAB_TITLES =
            new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CatsFactsListFragment();
            case 1:
                return new FavouriteCatsFactsListFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}