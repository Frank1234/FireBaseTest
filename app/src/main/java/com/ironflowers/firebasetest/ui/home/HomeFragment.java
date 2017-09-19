package com.ironflowers.firebasetest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.ui.content.ContentActivity;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class HomeFragment extends DaggerFragment implements HomeContract.View {

    @BindView(R.id.recyclerContent)
    RecyclerView recyclerContent;
    @BindView(R.id.textLoadingError)
    TextView textLoadingError;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    HomeContract.Presenter presenter;

    private Unbinder butterKnifeUnBinder;
    public HomeAdapter adapter;

    @Inject
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home_fragment, container, false);

        butterKnifeUnBinder = ButterKnife.bind(this, root);

        initAdapter();

        return root;
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

    private void initAdapter() {

        adapter = new HomeAdapter(presenter);
        recyclerContent.setAdapter(adapter);
        recyclerContent.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showProgressIndicator(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setContentItems(List<HomeItemViewModel> contentItems) {

        adapter.setItems(contentItems);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingError(boolean show) {
        textLoadingError.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }

    @Override
    public void openContentPage(int contentItemId, String title) {

        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra(ContentActivity.EXTRA_CONTENT_ITEM_ID, contentItemId);
        intent.putExtra(ContentActivity.EXTRA_TOOLBAR_TITLE, title);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butterKnifeUnBinder.unbind();
    }
}