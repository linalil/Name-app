package com.example.nameapp;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class addOwnerTest {


    @Rule
    public ActivityTestRule<addOwner> mActivityRule = new ActivityTestRule<>(addOwner.class);

    /*
    * Tester om det står "Type your name:" i tekstboksen med id assNameText
    * */
    @Test
    public void checkForNameText() throws Exception {
        onView(withId(R.id.addNameText)).check(matches(withText("Type your name:")));
    }

    /*
    * Legger inn teksten "Per" i textView med id edit_name og sjekker om denne teksten faktisk finnes i textViewet
    */
    @Test
    public void validateEditText() {
        onView(withId(R.id.edit_owner)).perform(typeText("Per")).check(matches(withText("Per")));
    }

}