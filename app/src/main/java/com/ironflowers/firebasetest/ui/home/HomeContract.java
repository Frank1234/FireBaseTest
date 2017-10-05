package com.ironflowers.firebasetest.ui.home;

import com.ironflowers.firebasetest.BasePresenter;
import com.ironflowers.firebasetest.BaseView;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModel;

import java.util.List;

/**
 * Specifies the contract between the view and the presenter.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showProgressIndicator(boolean show);

        void setContentItems(List<HomeItemViewModel> contentItems);

        void showLoadingError(boolean show);

        boolean isActive();

        int getItemCount();

        void openContentPage(int contentItemId, String title);
    }

    interface Presenter extends BasePresenter<View>, HomeAdapter.ItemListener {

    }
}
