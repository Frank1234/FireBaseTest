package com.ironflowers.firebasetest.util;

import android.view.View;

public class ViewUtils {

    /**
     * Starts a fade in animation on the view.
     */
    public static void animateFadeIn(View view) {

        view.animate().cancel();

        if (view.getAlpha() != 1f || view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(0f);
            view.animate().alpha(1f).start();
        } else {
            view.setAlpha(1f);
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Starts a fade out animation on the view.
     */
    public static void animateFadeOut(View view) {

        view.animate().cancel();

        if (view.getAlpha() != 0f || view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            view.setAlpha(1f);
            view.animate().alpha(0f).start();
        } else {
            view.setAlpha(0f);
            view.setVisibility(View.INVISIBLE);
        }
    }
}
