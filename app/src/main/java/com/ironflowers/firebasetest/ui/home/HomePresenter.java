package com.ironflowers.firebasetest.ui.home;

import android.support.annotation.Nullable;

import com.ironflowers.firebasetest.data.model.ContentItem;
import com.ironflowers.firebasetest.data.repo.ContentRepository;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModelFactory;
import com.ironflowers.firebasetest.utils.rx.EspressoIdlingResource;
import com.ironflowers.firebasetest.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Presents a list of {@link ContentItem}s on the screen.
 */
public class HomePresenter implements HomeContract.Presenter {

    // TODO maybe add a ViewModel for the main screen (progress and error text) as well.

    @Nullable
    private HomeContract.View view;

    private ContentRepository contentRepository;
    private Disposable getContentItemsDisposable;
    private SchedulerProvider schedulerProvider;
    private HomeItemViewModelFactory homeItemViewModelFactory;

    @Inject
    HomePresenter(ContentRepository contentRepository, SchedulerProvider schedulerProvider,
                  HomeItemViewModelFactory homeItemViewModelFactory) {
        this.contentRepository = contentRepository;
        this.schedulerProvider = schedulerProvider;
        this.homeItemViewModelFactory = homeItemViewModelFactory;
    }

    void loadItems() {

        if (view == null) {
            return;
        }

        view.showProgressIndicator(true);

        EspressoIdlingResource.increment();

        getContentItemsDisposable = contentRepository.getContentItems()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        contentItems -> onLoadItemsSuccess(contentItems),
                        e -> onLoadItemsFailed()
                );
    }

    void onLoadItemsFailed() {

        if (isViewActive(view)) {

            // noinspection ConstantConditions
            view.showProgressIndicator(false);
            view.showLoadingError(view.getItemCount() == 0);
        }

        EspressoIdlingResource.decrement();
    }

    void onLoadItemsSuccess(List<ContentItem> contentItems) {

        EspressoIdlingResource.decrement();

        if (!isViewActive(view)) {
            return;
        }

        List<HomeItemViewModel> viewModels = homeItemViewModelFactory.createViewModels(contentItems);

        // noinspection ConstantConditions
        view.setContentItems(viewModels);
        view.showProgressIndicator(false);
        view.showLoadingError(view.getItemCount() == 0);
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
        loadItems();
    }

    @Override
    public void dropView() {

        view = null;

        if (getContentItemsDisposable != null) {
            getContentItemsDisposable.dispose();
        }
    }

    boolean isViewActive(HomeContract.View view) {
        return view != null && view.isActive();
    }

    @Override
    public void onHomeItemClicked(HomeItemViewModel viewModel) {

        if (isViewActive(view)) {
            //noinspection ConstantConditions
            view.openContentPage(viewModel.getItemId(), viewModel.getTitle());
        }
    }
}