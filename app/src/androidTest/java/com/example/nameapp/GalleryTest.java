package com.example.nameapp;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class GalleryTest {

    @Rule
    public ActivityTestRule<Gallery> mActivityRule = new ActivityTestRule<>(
            Gallery.class);

    @Test
    public void goBack() throws Exception {
        onView(withId(R.id.goBackButton)).perform(click());
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()));

    }

}