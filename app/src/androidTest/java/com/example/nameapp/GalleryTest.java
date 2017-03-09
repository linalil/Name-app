package com.example.nameapp;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by linalil on 31.01.2017.
 */
public class GalleryTest {

    @Rule
    public ActivityTestRule<Gallery> mActivityRule = new ActivityTestRule<>(
            Gallery.class);

    /*
    * Sjekkar at ein går tilbake når ein trykkjer på back.
    * */
    @Test
    public void goBack() throws Exception {
        onView(withId(R.id.goBackButton)).perform(click());
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()));

    }

}