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

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link
 * AppComponent}.
 */
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