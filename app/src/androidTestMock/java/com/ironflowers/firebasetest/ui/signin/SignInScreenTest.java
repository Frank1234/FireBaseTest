package com.ironflowers.firebasetest.ui.signin;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.auth.FakeAuthConnector;
import com.ironflowers.firebasetest.util.MyTestUtils;
import com.ironflowers.firebasetest.utils.rx.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Tests for the Home screen.
 */
@LargeTest
public class SignInScreenTest {

    @Rule
    public ActivityTestRule<SignInActivity> SignInActivityLauncher =
            new ActivityTestRule<>(SignInActivity.class, true, false);

    /**
     * Signs the user in on the firebase backend.
     */
    @Before
    public void signIn() throws InterruptedException {
        MyTestUtils.signInForTesting();
    }

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    public void createActivity() {
        Intent startIntent = new Intent();
        SignInActivityLauncher.launchActivity(startIntent);
    }

    @Test
    public void signInExistingUserSuccess_ShowsHomeScreen() throws Exception {

        FakeAuthConnector.setFakeSuccess(true);

        createActivity();

        // sign in screen should be closed:
        Espresso.onView(withId(R.id.buttonContinue)).check(doesNotExist());
        // home screen should be opened:
        Espresso.onView(withId(R.id.recyclerContent)).check(matches(isDisplayed()));
    }

    @Test
    public void signInNewUserSuccess_ShowsHomeScreen() throws Exception {

        FirebaseAuth.getInstance().signOut();
        FakeAuthConnector.setFakeSuccess(true);

        createActivity();

        // sign in screen should be closed:
        Espresso.onView(withId(R.id.buttonContinue)).check(doesNotExist());
        // home screen should be opened:
        Espresso.onView(withId(R.id.recyclerContent)).check(matches(isDisplayed()));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void signInFailure_ShowsErrorMessage() throws Exception {

        FirebaseAuth.getInstance().signOut();
        FakeAuthConnector.setFakeSuccess(false);

        createActivity();

        // error message should be showing:
        Espresso.onView(withId(R.id.textMessage)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.buttonContinue)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        // double check that the home screen is not shown:
        Espresso.onView(withId(R.id.recyclerContent)).check(doesNotExist());
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void signOut() {
        MyTestUtils.signOutForTesting();
    }
}