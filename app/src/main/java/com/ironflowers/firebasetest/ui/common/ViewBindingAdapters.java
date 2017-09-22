package com.ironflowers.firebasetest.ui.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Binding adapters for custom data binding attributes.
 */
public class ViewBindingAdapters {

    @BindingAdapter({"glideSrc"})
    public static void setImageViewResource(ImageView imageView, String publicImageUrl) {

        if (publicImageUrl == null) {
            return;
        }

        GlideApp.with(imageView.getContext())
                .load(publicImageUrl)
                .transition(withCrossFade())
                .into(imageView);
    }
}
