package com.ironflowers.firebasetest.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

/**
 * Fake auth connector, for easy espresso testing.
 */
public class FakeAuthConnector implements AuthConnector {

    private static boolean fakeSuccess = false;

    @Inject
    public FakeAuthConnector() {
    }

    public static void setFakeSuccess(boolean fakeSuccess) {
        FakeAuthConnector.fakeSuccess = fakeSuccess;
    }

    @Override
    public Task<AuthResult> signInAnonymously() {
        return new FakeAuthTask(fakeSuccess);
    }

    @Override
    public boolean isSignedIn() {
        return false;
    }
}