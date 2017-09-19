package com.ironflowers.firebasetest.auth;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

/**
 * A fake/mocked authentication task that always fails.
 */
public class FakeAuthTask extends Task<AuthResult> {

    private final int fakedDelayMs = 10;
    boolean fakeSuccess;

    public FakeAuthTask(boolean fakeSuccess) {
        this.fakeSuccess = fakeSuccess;
    }

    /**
     * Pretends this task is failing after a certain period.
     */
    private void fakeFailureAfterDelay(OnFailureListener onFailureListener) {
        new Handler().postDelayed(() -> onFailureListener.onFailure(new Exception("My mocked/fake error")), fakedDelayMs);
    }

    /**
     * Pretends this task is failing after a certain period.
     */
    private void fakeFailureAfterDelay(OnCompleteListener<AuthResult> onCompleteListener) {
        new Handler().postDelayed(() -> onCompleteListener.onComplete(this), fakedDelayMs);
    }

    /**
     * Pretends this task is failing after a certain period.
     */
    private void fakeSuccessAfterDelay(OnSuccessListener<? super AuthResult> onSuccessListener) {
        new Handler().postDelayed(() -> onSuccessListener.onSuccess(new AuthResult() {
            @Override
            public FirebaseUser getUser() {
                return null;
            }

            @Override
            public AdditionalUserInfo getAdditionalUserInfo() {
                return null;
            }
        }), 200);
    }

    /**
     * Pretends this task is failing after a certain period.
     */
    private void fakeSuccessAfterDelay(OnCompleteListener<AuthResult> onCompleteListener) {
        new Handler().postDelayed(() -> onCompleteListener.onComplete(this), fakedDelayMs);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isSuccessful() {
        return fakeSuccess;
    }

    @Override
    public AuthResult getResult() {
        return null;
    }

    @Override
    public <X extends Throwable> AuthResult getResult(@NonNull Class<X> aClass) throws X {
        return null;
    }

    @Nullable
    @Override
    public Exception getException() {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onSuccessListener);
        }
        return this;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onSuccessListener);
        }
        return this;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onSuccessListener);
        }
        return this;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        if (!fakeSuccess) {
            fakeFailureAfterDelay(onFailureListener);
        }
        return this;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        if (!fakeSuccess) {
            fakeFailureAfterDelay(onFailureListener);
        }
        return this;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        if (!fakeSuccess) {
            fakeFailureAfterDelay(onFailureListener);
        }
        return this;
    }

    @NonNull
    public Task<AuthResult> addOnCompleteListener(@NonNull OnCompleteListener<AuthResult> onCompleteListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onCompleteListener);
        } else {
            fakeFailureAfterDelay(onCompleteListener);
        }
        return this;
    }

    @NonNull
    public Task<AuthResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<AuthResult> onCompleteListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onCompleteListener);
        } else {
            fakeFailureAfterDelay(onCompleteListener);
        }
        return this;
    }

    @NonNull
    public Task<AuthResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<AuthResult> onCompleteListener) {
        if (fakeSuccess) {
            fakeSuccessAfterDelay(onCompleteListener);
        } else {
            fakeFailureAfterDelay(onCompleteListener);
        }
        return this;
    }
}
