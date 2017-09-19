package com.ironflowers.firebasetest.ui.home.vm;

import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.data.model.ContentItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates ViewModels for one {@link ContentItem}s.
 */
public class HomeItemViewModelFactory {

    private StorageReference rootStorageReference;

    public HomeItemViewModelFactory(StorageReference rootStorageReference) {
        this.rootStorageReference = rootStorageReference;
    }

    /**
     * Creates viewModel objects for the given {@link ContentItem}s.
     */
    public List<HomeItemViewModel> createViewModels(List<ContentItem> items) {

        List<HomeItemViewModel> viewModels = new ArrayList<>();

        for (ContentItem item : items) {

            viewModels.add(createViewModel(item));
        }
        return viewModels;
    }

    /**
     * Creates one viewModel object for the given {@link ContentItem}.
     */
    private HomeItemViewModel createViewModel(ContentItem item) {

        HomeItemViewModel viewModel = new HomeItemViewModel(item.getId());

        viewModel.setTitle(item.getTitle());
        if (item.getImageUrl() != null) {
            viewModel.setImageReference(rootStorageReference.child(item.getImageUrl()));
        }

        return viewModel;
    }
}
