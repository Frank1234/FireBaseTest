package com.ironflowers.firebasetest.ui.signin;

import com.ironflowers.firebasetest.BasePresenter;
import com.ironflowers.firebasetest.BaseView;
import com.ironflowers.firebasetest.ui.signin.vm.SignInViewModel;

/**
 * Specifies the contract between the view and the presenter.
 */
public interface SignInContract {

    interface View extends BaseView<Presenter> {

        void continueIntoApp();

        /**
         * Binds the ViewModel on the view.
         *
         * @param viewModel
         */
        void bind(SignInViewModel viewModel);

        boolean isActive();
    }

    interface Presenter extends BasePresenter<View> {

        void startSignInFlow();
    }
}
