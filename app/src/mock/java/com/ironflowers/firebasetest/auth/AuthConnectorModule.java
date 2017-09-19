package com.ironflowers.firebasetest.auth;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Used to be able to inject a fake authentication connector class.
 */
@Module
abstract public class AuthConnectorModule {

    @Singleton
    @Binds
    abstract AuthConnector provideAuthConnector(FakeAuthConnector authConnector);
}