package com.best.catfacts.ui.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.best.catfacts.R;
import com.best.catfacts.model.CatsFact;

import java.util.List;

public class CatsFactsListRecyclerViewAdapter extends RecyclerView.Adapter<CatsFactsListRecyclerViewAdapter.ViewHolder> {

    private final List<CatsFact> catsFactList;
    boolean[] selectedItems;

    public CatsFactsListRecyclerViewAdapter(List<CatsFact> items) {
        catsFactList = items;
        selectedItems = new boolean[items.size()];

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cats_facts_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CatsFact catsFact = catsFactList.get(position);
        holder.catsFact = catsFactList.get(position);
        holder.fact.setText(catsFactList.get(position).getFact());

        if(selectedItems[position]==true){
            holder.cardView.setCardBackgroundColor(Color.CYAN);
        }else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }

        holder.view.setOnClickListener(v -> {
            if(catsFact.isSelected())
            {
                holder.cardView.setCardBackgroundColor(Color.WHITE);
                selectedItems[position]=false;
            }
            else {
                holder.cardView.setCardBackgroundColor(Color.CYAN);
                selectedItems[position]=true;
            }
            catsFact.setSelected(!catsFact.isSelected());
        });
    }

    @Override
    public int getItemCount() {
        return catsFactList == null ? 0 : catsFactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final CardView cardView;
        public final TextView fact;
        public CatsFact catsFact;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.cardView = view.findViewById(R.id.card_view);
            fact = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + fact.getText() + "'";
        }
    }
}
