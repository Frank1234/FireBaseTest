package com.ironflowers.firebasetest.ui.home.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ironflowers.firebasetest.BR;
import com.ironflowers.firebasetest.data.model.ContentItem;

/**
 * ViewModel for the displaying of one {@link ContentItem}.
 */
public class HomeItemViewModel extends BaseObservable {

    private int itemId;
    private String title;
    private String imageUrl;

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
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
