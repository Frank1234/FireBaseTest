package com.ironflowers.firebasetest.ui.common;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.google.firebase.storage.StorageReference;
import com.ironflowers.firebasetest.util.FirebaseImageLoader;

import java.io.InputStream;

/**
 * Module used to load Glide images from Firebase Storage.
 */
@GlideModule
public class FirebaseGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.append(StorageReference.class, InputStream.class, new FirebaseImageLoader.Factory());
    }
}