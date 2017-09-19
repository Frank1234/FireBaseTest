package com.ironflowers.firebasetest;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.database.FirebaseDatabase;
import com.ironflowers.firebasetest.di.AppComponent;
import com.ironflowers.firebasetest.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class MainApplication extends DaggerApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initLogging();
        initFirebaseDatabase();
    }

    protected void initFirebaseDatabase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    protected void initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}