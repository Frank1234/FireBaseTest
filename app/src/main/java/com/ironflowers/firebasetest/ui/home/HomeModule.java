package com.ironflowers.firebasetest.ui.home;

import com.google.firebase.storage.StorageReference;

import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.di.FragmentScoped;
import com.ironflowers.firebasetest.ui.home.vm.HomeItemViewModelFactory;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.ironflowers.firebasetest.di.ApplicationModule.NAME_ROOT_STORAGE;

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
    static HomeItemViewModelFactory homeItemViewModelFactory(@Named(NAME_ROOT_STORAGE) StorageReference rootStorageReference) {
        return new HomeItemViewModelFactory(rootStorageReference);
    }
}
