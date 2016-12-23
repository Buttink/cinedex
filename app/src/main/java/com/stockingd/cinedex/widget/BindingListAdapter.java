package com.stockingd.cinedex.widget;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BindingListAdapter<T extends Identifiable,
                                         S extends BindingViewHolder<T>>
        extends RecyclerView.Adapter<S> {

    @NonNull private final List<T> model = new ArrayList<>();

    public void updateModel(List<T> newModel) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return model.size();
            }

            @Override
            public int getNewListSize() {
                return newModel.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                T old = model.get(oldItemPosition);
                T present = newModel.get(newItemPosition);
                return old.id() == present.id();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                T old = model.get(oldItemPosition);
                T present = newModel.get(newItemPosition);
                return old.equals(present);
            }
        });
        model.clear();
        model.addAll(newModel);
        diffResult.dispatchUpdatesTo(this);
    }

    public abstract S onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onBindViewHolder(S holder, int position) {
        holder.bind(model.get(position));
    }
}
