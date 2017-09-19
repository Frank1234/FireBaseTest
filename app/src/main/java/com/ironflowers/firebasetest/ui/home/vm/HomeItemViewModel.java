package com.ironflowers.firebasetest.ui.home.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.BR;
import com.ironflowers.firebasetest.data.model.ContentItem;

/**
 * ViewModel for the displaying of one {@link ContentItem}.
 */
public class HomeItemViewModel extends BaseObservable {

    private int itemId;
    private String title;
    private StorageReference imageReference;

    public HomeItemViewModel(int itemId) {
        this.itemId = itemId;
    }

    @Bindable
    public int getItemId() {
        return itemId;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public StorageReference getImageReference() {
        return imageReference;
    }

    public void setImageReference(StorageReference imageReference) {
        this.imageReference = imageReference;
        notifyPropertyChanged(BR.imageReference);
    }
}
