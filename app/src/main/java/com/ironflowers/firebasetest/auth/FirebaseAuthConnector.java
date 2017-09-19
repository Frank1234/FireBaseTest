package com.ironflowers.firebasetest.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class FirebaseAuthConnector implements AuthConnector {

    @Inject
    public FirebaseAuthConnector() {
    }

    @Override
    public Task<AuthResult> signInAnonymously() {
        return FirebaseAuth.getInstance().signInAnonymously();
    }

    @Override
    public boolean isSignedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}
