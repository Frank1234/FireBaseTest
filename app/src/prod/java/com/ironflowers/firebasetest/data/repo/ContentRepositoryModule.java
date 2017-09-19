package com.ironflowers.firebasetest.data.repo;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
abstract public class ContentRepositoryModule {

    @Singleton
    @Binds
    abstract ContentRepository provideContentRepository(FireBaseContentRepository contentRepository);
}