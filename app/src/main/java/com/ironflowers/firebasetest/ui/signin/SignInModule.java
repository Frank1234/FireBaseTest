package com.ironflowers.firebasetest.ui.signin;

import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.ui.signin.vm.SignInViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * A Dagger module with View dependency to the {@link SignInPresenter}.
 */
@Module
public abstract class SignInModule {

    @ActivityScoped
    @Binds
    abstract SignInContract.Presenter signInPresenter(SignInPresenter presenter);

    @Provides
    @ActivityScoped
    static SignInViewModel contentViewModel() {
        return new SignInViewModel();
    }
}
