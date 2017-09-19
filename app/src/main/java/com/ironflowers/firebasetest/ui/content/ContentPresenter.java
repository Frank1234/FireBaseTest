package com.ironflowers.firebasetest.ui.content;

import android.support.annotation.Nullable;

import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.data.model.ContentItem;
import com.ironflowers.firebasetest.data.repo.ContentRepository;
import com.ironflowers.firebasetest.ui.content.vm.ContentViewModel;
import com.ironflowers.firebasetest.utils.rx.EspressoIdlingResource;
import com.ironflowers.firebasetest.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;

import static com.ironflowers.firebasetest.di.ApplicationModule.NAME_ROOT_STORAGE;

/**
 * Presents one {@link ContentItem} on the screen.
 */
public class ContentPresenter implements ContentContract.Presenter {

    @Nullable
    private ContentContract.View view;

    private ContentRepository contentRepository;
    private Disposable getContentItemsDisposable;
    private SchedulerProvider schedulerProvider;
    private ContentViewModel viewModel;
    private StorageReference rootStorageReference;

    /**
     * Content item id to show on this page.
     */
    private int contentItemId;

    @Inject
    ContentPresenter(ContentRepository contentRepository, SchedulerProvider schedulerProvider,
                     @Named(NAME_ROOT_STORAGE) StorageReference rootStorageReference,
                     int contentItemId, ContentViewModel viewModel) {

        this.contentRepository = contentRepository;
        this.schedulerProvider = schedulerProvider;
        this.viewModel = viewModel;
        this.rootStorageReference = rootStorageReference;
        this.contentItemId = contentItemId;
    }

    void loadContent() {

        if (view == null) {
            return;
        }

        view.bind(viewModel);
        viewModel.setShowLoadingIndicator(true);

        EspressoIdlingResource.increment();

        getContentItemsDisposable = contentRepository.getContentItem(contentItemId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        contentItems -> onLoadItemSuccess(contentItems),
                        e -> onLoadItemFailed()
                );
    }

    void onLoadItemFailed() {

        if (isViewActive(view)) {
            viewModel.setShowLoadingIndicator(false);
            viewModel.setShowErrorMessage(true);
        }

        EspressoIdlingResource.decrement();
    }

    void onLoadItemSuccess(ContentItem contentItem) {

        if (!isViewActive(view)) {
            return;
        }

        viewModel.setShowLoadingIndicator(false);
        viewModel.setShowErrorMessage(false);

        updateViewModelWithItemData(contentItem);

        EspressoIdlingResource.decrement();
    }

    /**
     * Updates the ViewModel with the data from item.
     */
    private void updateViewModelWithItemData(ContentItem item) {

        viewModel.setContentDescription(item.getDescription());

        if (item.getImageUrl() != null) {
            viewModel.setImageReference(rootStorageReference.child(item.getImageUrl()));
        }
    }

    @Override
    public void attachView(ContentContract.View view) {
        this.view = view;
        loadContent();
    }

    @Override
    public void dropView() {

        view = null;

        if (getContentItemsDisposable != null) {
            getContentItemsDisposable.dispose();
        }
    }

    boolean isViewActive(ContentContract.View view) {
        return view != null && view.isActive();
    }
}