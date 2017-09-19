package com.ironflowers.firebasetest;

import com.google.firebase.database.FirebaseDatabase;

public class MainTestApplication extends MainApplication {

    @Override
    protected void initFirebaseDatabase() {

        // for testing we disable caching:
        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
    }
}