package com.ironflowers.firebasetest.ui.content;

import com.ironflowers.firebasetest.di.ActivityScoped;
import com.ironflowers.firebasetest.di.FragmentScoped;
import com.ironflowers.firebasetest.ui.content.vm.ContentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link ContentPresenter}.
 */
@Module
public abstract class ContentModule {

    @FragmentScoped
    @ContributesAndroidInjector // TODO Low prio: maybe remove FragmentScope, we hardly use it
    abstract ContentFragment contentFragment();

    @Binds
    @ActivityScoped
    abstract ContentContract.Presenter contentPresenter(ContentPresenter presenter);

    @Provides
    @ActivityScoped
    static ContentViewModel contentViewModel() {
        return new ContentViewModel();
    }

    @Provides
    @ActivityScoped
    static Integer provideTaskId(ContentActivity activity) {
        return activity.getIntent().getIntExtra(ContentActivity.EXTRA_CONTENT_ITEM_ID, -1);
    }
}
