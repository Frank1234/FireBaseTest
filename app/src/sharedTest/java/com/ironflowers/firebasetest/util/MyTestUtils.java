package com.ironflowers.firebasetest.util;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ironflowers.firebasetest.data.model.ContentItem;

/**
 * Helper utilities methods for Android Tests.
 */
public class MyTestUtils {

    /**
     * Signs the user in on the firebase backend, for testing. Only returns when the sign in
     * finished (failed or success).
     */
    public static void signInForTesting() throws InterruptedException {

        Task task = FirebaseAuth.getInstance().signInAnonymously();

        /*
         * Because there are no views involved, we cannot use the IdlingRegistry here. We will wait
         * on the sign in call to stop this method from returning until the login finished.
         */
        while (!task.isComplete()) {
            Thread.sleep(100);
        }
    }

    /**
     * Signs the user out from the firebase backend, for testing.
     */
    public static void signOutForTesting() {
        FirebaseAuth.getInstance().signOut();
    }

    public static ContentItem createFakeContentItem(int id) {
        ContentItem item = new ContentItem();
        item.setId(id);
        item.setTitle("Title " + id);
        item.setDescription("Description " + id);
        return item;
    }
}
