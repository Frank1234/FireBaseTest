package com.ironflowers.firebasetest.ui.home;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ironflowers.firebasetest.R;
import com.ironflowers.firebasetest.data.repo.FakeContentRepository;
import com.ironflowers.firebasetest.utils.rx.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.ironflowers.firebasetest.util.MyTestUtils.createFakeContentItem;
import static com.ironflowers.firebasetest.util.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;

/**
 * Tests for the Home screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeScreenTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityLauncher =
            new ActivityTestRule<>(HomeActivity.class, true, false);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    public void createActivity() {
        Intent startIntent = new Intent();
        homeActivityLauncher.launchActivity(startIntent);
    }

    @Test
    public void loadItemsFailed_ShowsErrorMessage() throws Exception {

        FakeContentRepository.setFakeData(null);

        createActivity();

        onView(withId(R.id.recyclerContent)).check(matches(isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(isDisplayed()));
    }

    @Test
    public void loadItemsSuccess_ShowsItems() throws Exception {

        FakeContentRepository.setFakeData(new ArrayList<>(Arrays.asList(
                createFakeContentItem(1),
                createFakeContentItem(2),
                createFakeContentItem(3),
                createFakeContentItem(4))));

        createActivity();

        onView(withId(R.id.recyclerContent)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerContent)).check(withItemCount(4));

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void onItemClicked_OpenContentScreen() throws Exception {

        FakeContentRepository.setFakeData(new ArrayList<>(Arrays.asList(
                createFakeContentItem(1),
                createFakeContentItem(2),
                createFakeContentItem(3),
                createFakeContentItem(4))));

        createActivity();

        onView(withId(R.id.recyclerContent)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerContent)).check(withItemCount(4));

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(not(isDisplayed())));

        // click on the first item in the list:
        onView(withId(R.id.recyclerContent))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // content screen should be opened:
        onView(withId(R.id.textDescription)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}