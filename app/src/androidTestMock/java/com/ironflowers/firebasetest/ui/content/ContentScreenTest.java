package com.ironflowers.firebasetest.ui.content;

import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
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

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ironflowers.firebasetest.util.MyTestUtils.createFakeContentItem;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Tests for the Home screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContentScreenTest {

    @Rule
    public ActivityTestRule<ContentActivity> contentActivityLauncher =
            new ActivityTestRule<>(ContentActivity.class, true, false);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    public void createActivity(int contentItemId) {
        Intent startIntent = new Intent();
        startIntent.putExtra(ContentActivity.EXTRA_CONTENT_ITEM_ID, contentItemId);
        contentActivityLauncher.launchActivity(startIntent);
    }

    @Test
    public void loadContentFailed_ShowsErrorMessage() throws Exception {

        FakeContentRepository.setFakeData(null);

        createActivity(2);

        onView(withId(R.id.textDescription)).check(matches(withText(isEmptyOrNullString())));
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(isDisplayed()));
    }

    @Test
    public void itemNotFound_ShowsErrorMessage() throws Exception {

        FakeContentRepository.setFakeData(Arrays.asList(
                createFakeContentItem(1),
                createFakeContentItem(2),
                createFakeContentItem(3),
                createFakeContentItem(4)));

        // fetch an item that does not exist:
        createActivity(-1);

        onView(withId(R.id.textDescription)).check(matches(withText(isEmptyOrNullString())));
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(isDisplayed()));
    }

    @Test
    public void loadContentSuccess_ShowsItem() throws Exception {

        FakeContentRepository.setFakeData(Arrays.asList(
                createFakeContentItem(1),
                createFakeContentItem(2),
                createFakeContentItem(3),
                createFakeContentItem(4)));

        createActivity(2);

        onView(withId(R.id.textDescription)).check(matches(withText("Description 2")));
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textLoadingError)).check(matches(not(isDisplayed())));
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}