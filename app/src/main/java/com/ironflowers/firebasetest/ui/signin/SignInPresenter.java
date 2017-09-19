package com.ironflowers.firebasetest.ui.signin;

import android.support.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.ironflowers.firebasetest.auth.AuthConnector;
import com.ironflowers.firebasetest.ui.signin.vm.SignInViewModel;
import com.ironflowers.firebasetest.utils.rx.EspressoIdlingResource;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Shows the Splash Screen and signs in the user anonymously. Continues into the app after
 * the user is signed in.
 */
public class SignInPresenter implements SignInContract.Presenter {

    @Nullable
    private SignInContract.View view;

    private AuthConnector authConnector;
    private SignInViewModel viewModel;

    @Inject
    SignInPresenter(AuthConnector authConnector, SignInViewModel viewModel) {
        this.authConnector = authConnector;
        this.viewModel = viewModel;
    }

    @Override
    public void startSignInFlow() {

        if (view != null) {
            view.bind(viewModel);
        }

        if (authConnector.isSignedIn()) {

            if (isViewActive(view)) {

                //noinspection ConstantConditions
                view.continueIntoApp();
            }
        } else {

            signIn();
        }
    }

    /**
     * Signs the user in at the FireBase backend. On success, this sign in screen will be closed.
     */
    private void signIn() {

        if (!isViewActive(view)) {
            return;
        }

        EspressoIdlingResource.increment();

        //noinspection ConstantConditions
        viewModel.setShowErrorMessage(false);
        viewModel.setShowContinueButton(false);
        viewModel.setShowLoadingIndicator(true);

        authConnector.signInAnonymously()
                .addOnCompleteListener(task -> {
                    onSignInFinished(task);
                    EspressoIdlingResource.decrement();
                });
    }

    private void onSignInFinished(Task task) {

        if (!isViewActive(view)) {
            return;
        }

        if (task.isSuccessful()) {

            //noinspection ConstantConditions
            view.continueIntoApp();
        } else {

            Timber.w(task.getException(), "signInAnonymously:failure");

            //noinspection ConstantConditions
            viewModel.setShowErrorMessage(true);
            viewModel.setShowContinueButton(true);
            viewModel.setShowLoadingIndicator(false);
        }
    }

    @Override
    public void attachView(SignInContract.View view) {
        this.view = view;
        startSignInFlow();
    }

    @Override
    public void dropView() {
        view = null;
    }

    boolean isViewActive(SignInContract.View view) {
        return view != null && view.isActive();
    }
}