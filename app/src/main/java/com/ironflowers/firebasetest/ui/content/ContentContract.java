package com.ironflowers.firebasetest.ui.content;

import com.ironflowers.firebasetest.BasePresenter;
import com.ironflowers.firebasetest.BaseView;
import com.ironflowers.firebasetest.ui.content.vm.ContentViewModel;

/**
 * Specifies the contract between the view and the presenter.
 */
public interface ContentContract {

    interface View extends BaseView<Presenter> {

        /**
         * Binds the ViewModel on the view.
         *
         * @param viewModel
         */
        void bind(ContentViewModel viewModel);

        boolean isActive();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
