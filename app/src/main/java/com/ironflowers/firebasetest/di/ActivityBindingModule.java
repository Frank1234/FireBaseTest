package com.ironflowers.firebasetest.di;

import com.ironflowers.firebasetest.ui.content.ContentActivity;
import com.ironflowers.firebasetest.ui.home.HomeModule;
import com.ironflowers.firebasetest.ui.signin.SignInActivity;
import com.ironflowers.firebasetest.ui.signin.SignInModule;
import com.ironflowers.firebasetest.ui.content.ContentModule;
import com.ironflowers.firebasetest.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
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
