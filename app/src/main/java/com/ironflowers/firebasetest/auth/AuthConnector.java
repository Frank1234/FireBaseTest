package com.ironflowers.firebasetest.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthConnector {

    Task<AuthResult> signInAnonymously();

    boolean isSignedIn();
}