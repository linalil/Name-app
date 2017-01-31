package com.example.nameapp;

import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class LearningModeTest{


    private static final String mStringToBeTyped = "Espresso";

    @Rule
    public ActivityTestRule<LearningMode> mActivityRule = new ActivityTestRule<>(
            LearningMode.class);

/*
    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(mStringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(mStringToBeTyped)));
    }*/


    @Test
    public void sendAnswer() throws Exception {

    }

    @Test
    public void updateScore() throws Exception {

    }

    @Test
    public void goBack() throws Exception {

    }

}