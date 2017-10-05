package com.ironflowers.firebasetest.ui.signin;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ironflowers.firebasetest.auth.AuthConnector;
import com.ironflowers.firebasetest.ui.home.HomePresenter;
import com.ironflowers.firebasetest.ui.signin.vm.SignInViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of {@link HomePresenter}
 */
public class SignInPresenterTest {

    @Mock
    private SignInContract.View signInView;
    @Mock
    private AuthConnector authConnector;
    @Mock
    private Task<AuthResult> authResultTask;

    private SignInPresenter signInPresenter;
    private SignInViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        viewModel = new SignInViewModel();
        signInPresenter = new SignInPresenter(authConnector, viewModel);
        signInPresenter.attachView(signInView);

        // The presenter wont't update the view unless it's active:
        when(signInView.isActive()).thenReturn(true);
    }

    @Test
    public void signInNewUser_CallViewToShowProgress() {

        when(authConnector.isSignedIn()).thenReturn(false);
        when(authConnector.signInAnonymously()).thenReturn(authResultTask);

        // because of the above the presenter will keep signing in forever:
        signInPresenter.onContinueButtonClicked();

        assertTrue(viewModel.isShowLoadingIndicator());
        assertFalse(viewModel.isShowErrorMessage());
    }

    @Test
    public void signInExistingUser_CallViewToContinueIntoApp() {

        when(authConnector.isSignedIn()).thenReturn(true);

        signInPresenter.onContinueButtonClicked();

        verify(signInView).continueIntoApp();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void signInFailure_CallViewToShowError() {

        // make the sign in fail:
        when(authResultTask.isSuccessful()).thenReturn(false);
        when(authResultTask.isComplete()).thenReturn(true);
        when(authResultTask.addOnCompleteListener(any())).thenReturn(null);
        when(authConnector.signInAnonymously()).thenReturn(
                authResultTask);
        when(authResultTask.addOnCompleteListener(any(OnCompleteListener.class))).thenAnswer(
                invocation -> {
                    ((OnCompleteListener) invocation.getArguments()[0]).onComplete(authResultTask);
                    return null;
                });

        signInPresenter.onContinueButtonClicked();

        assertFalse(viewModel.isShowLoadingIndicator());
        assertTrue(viewModel.isShowErrorMessage());
    }


    @Test
    public void assertViewActive_True() {
        assertTrue(signInPresenter.isViewActive(signInView));
    }

    @Test
    public void assertViewActive_False() {
        when(signInView.isActive()).thenReturn(false);
        assertFalse(signInPresenter.isViewActive(signInView));
    }

    @Test
    public void assertViewActive_Null() {
        assertFalse(signInPresenter.isViewActive(null));
    }
}