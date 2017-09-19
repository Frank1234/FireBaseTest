package com.ironflowers.firebasetest.ui.content.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.BR;
import com.ironflowers.firebasetest.data.model.ContentItem;

/**
 * ViewModel for the displaying of one {@link ContentItem}.
 */
public class ContentViewModel extends BaseObservable {

    private boolean showLoadingIndicator;
    private boolean showErrorMessage;

    private String contentDescription;
    private StorageReference imageReference;

    @Bindable
    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
        notifyPropertyChanged(BR.contentDescription);
    }

    @Bindable
    public StorageReference getImageReference() {
        return imageReference;
    }

    public void setImageReference(StorageReference imageReference) {
        this.imageReference = imageReference;
        notifyPropertyChanged(BR.imageReference);
    }


    @Bindable
    public boolean isShowLoadingIndicator() {
        return showLoadingIndicator;
    }

    public void setShowLoadingIndicator(boolean showLoadingIndicator) {
        this.showLoadingIndicator = showLoadingIndicator;
        notifyPropertyChanged(BR.showLoadingIndicator);
    }


    @Bindable
    public boolean isShowErrorMessage() {
        return showErrorMessage;
    }

    public void setShowErrorMessage(boolean showErrorMessage) {
        this.showErrorMessage = showErrorMessage;
        notifyPropertyChanged(BR.showErrorMessage);
    }
}
