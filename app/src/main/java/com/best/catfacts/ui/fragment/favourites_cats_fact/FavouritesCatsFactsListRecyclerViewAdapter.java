package com.best.catfacts.ui.fragment.favourites_cats_fact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.best.catfacts.R;

import java.util.List;

public class FavouritesCatsFactsListRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesCatsFactsListRecyclerViewAdapter.ViewHolder> {

    private final List<String> catsFactList;

    public FavouritesCatsFactsListRecyclerViewAdapter(List<String> items) {
        catsFactList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cats_facts_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String fact = catsFactList.get(position);
        holder.fact.setText(fact);
    }

    @Override
    public int getItemCount() {
        return catsFactList == null ? 0 : catsFactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView fact;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            fact = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + fact.getText() + "'";
        }
    }
}


