package com.ironflowers.firebasetest.ui.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.databinding.HomeItemBinding;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter containing {@link HomeItemViewModel} objects.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public interface ItemListener {

        void onHomeItemClicked(HomeItemViewModel viewModel);
    }

    private List<HomeItemViewModel> viewModels = new ArrayList<>();

    private ItemListener itemListener;

    public HomeAdapter(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public HomeItemBinding itemBinding;

        public ViewHolder(HomeItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
            this.itemView.setOnClickListener(v ->
                    itemListener.onHomeItemClicked(viewModels.get(getAdapterPosition())));
        }

        public void bind(HomeItemViewModel viewModel) {
            itemBinding.setItem(viewModel);
            itemBinding.executePendingBindings();
        }
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HomeItemBinding itemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.home_item, parent, false);
        HomeAdapter.ViewHolder viewHolder = new HomeAdapter.ViewHolder(itemBinding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HomeItemViewModel viewModel = viewModels.get(position);
        holder.bind(viewModel);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public void setItems(List<HomeItemViewModel> viewModels) {
        this.viewModels = viewModels;
    }
}