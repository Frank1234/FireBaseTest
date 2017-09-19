package com.ironflowers.firebasetest.data.repo;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Used to be able to inject a fake content repository class.
 */
@Module
abstract public class ContentRepositoryModule {

    @Singleton
    @Binds
    abstract ContentRepository provideContentRepository(FakeContentRepository contentRepository);
}