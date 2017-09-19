package com.ironflowers.firebasetest.di;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.data.control.ContentItemController;
import com.ironflowers.firebasetest.utils.rx.AppSchedulerProvider;
import com.ironflowers.firebasetest.utils.rx.SchedulerProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static com.ironflowers.firebasetest.data.repo.ContentRepository.DB_PATH_CONTENT;

@Module
public abstract class ApplicationModule {

    public final static String NAME_ROOT_STORAGE = "NAME_ROOT_STORAGE";

    @Binds
    @Singleton
    protected abstract Context bindContext(Application application);

    @Binds
    @Singleton
    protected abstract SchedulerProvider schedulerProvider(AppSchedulerProvider provider);

    @Provides
    @Singleton
    static ContentItemController contentItemController() {
        return new ContentItemController();
    }

    @Provides
    @Singleton
    @Named(DB_PATH_CONTENT)
    static DatabaseReference databaseReference(FirebaseDatabase firebaseDatabase) {
        return firebaseDatabase.getReference(DB_PATH_CONTENT);
    }

    @Provides
    @Singleton
    static FirebaseDatabase firebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    static FirebaseStorage firebaseStorage() {
        return FirebaseStorage.getInstance();
    }

    @Provides
    @Singleton
    @Named(NAME_ROOT_STORAGE)
    static StorageReference storageReference(FirebaseStorage firebaseStorage) {
        return firebaseStorage.getReference();
    }
}