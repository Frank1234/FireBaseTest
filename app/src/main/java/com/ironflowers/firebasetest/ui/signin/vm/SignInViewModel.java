package com.ironflowers.firebasetest.ui.signin.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ironflowers.firebasetest.BR;
import com.ironflowers.firebasetest.data.model.ContentItem;

/**
 * ViewModel for the displaying of one {@link ContentItem}.
 */
public class SignInViewModel extends BaseObservable {

    private boolean showLoadingIndicator;
    private boolean showErrorMessage;
    private boolean showContinueButton;

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

    @Bindable
    public boolean isShowContinueButton() {
        return showContinueButton;
    }

    public void setShowContinueButton(boolean showContinueButton) {
        this.showContinueButton = showContinueButton;
        notifyPropertyChanged(BR.showContinueButton);
    }
}
