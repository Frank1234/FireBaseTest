package com.ironflowers.firebasetest.ui.content;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.databinding.ContentFragmentBinding;
import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.ui.content.vm.ContentViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class ContentFragment extends DaggerFragment implements ContentContract.View {

    @Inject
    ContentContract.Presenter presenter;

    private ContentFragmentBinding binding;

    @Inject
    public ContentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.content_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        presenter.dropView();
        super.onPause();
    }

    @Override
    public void bind(ContentViewModel viewModel) {
        binding.setItem(viewModel);
        binding.executePendingBindings();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}