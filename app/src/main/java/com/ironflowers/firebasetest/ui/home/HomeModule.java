package com.ironflowers.firebasetest.ui.home;

import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.di.FragmentScoped;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * A Dagger module with View dependency to the {@link HomePresenter}.
 */
@Module
public abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();

    @ActivityScoped
    @Binds
    abstract HomeContract.Presenter homePresenter(HomePresenter presenter);

    @ActivityScoped
    @Provides
    static HomeItemViewModelFactory homeItemViewModelFactory() {
        return new HomeItemViewModelFactory();
    }
}
