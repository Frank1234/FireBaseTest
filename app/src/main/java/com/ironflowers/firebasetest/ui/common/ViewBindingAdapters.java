package com.ironflowers.firebasetest.ui.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Binding adapters for custom data binding attributes.
 */
public class ViewBindingAdapters {

    @BindingAdapter({"storageSrc"})
    public static void setImageViewResource(ImageView imageView, StorageReference storageImageUrl) {

        if (storageImageUrl == null) {
            return;
        }

        GlideApp.with(imageView.getContext())
                .load(storageImageUrl)
                .transition(withCrossFade())
                .into(imageView);
    }
}
