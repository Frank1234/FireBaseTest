package com.ironflowers.firebasetest.auth;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
abstract public class AuthConnectorModule {

    @Singleton
    @Binds
    abstract AuthConnector provideAuthConnector(FirebaseAuthConnector authConnector);

}
