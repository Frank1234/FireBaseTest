package com.ironflowers.firebasetest.di;

import com.ironflowers.firebasetest.ui.content.ContentActivity;
import com.ironflowers.firebasetest.ui.home.HomeModule;
import com.ironflowers.firebasetest.ui.signin.SignInActivity;
import com.ironflowers.firebasetest.ui.signin.SignInModule;
import com.ironflowers.firebasetest.ui.content.ContentModule;
import com.ironflowers.firebasetest.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract SignInActivity signInActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ContentModule.class)
    abstract ContentActivity contentActivity();
}
