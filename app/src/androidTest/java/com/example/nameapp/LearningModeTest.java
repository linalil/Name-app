package com.example.nameapp;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
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

    @Test
    public void checkScoreCorrectAnswer() throws Exception {

        final LearningMode learningMode = (LearningMode) getActivityInstance();
        final Button button = (Button) learningMode.findViewById(R.id.checkAnswer);

        learningMode.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String correct = learningMode.gameCenter.correctName;
                onView(withId(R.id.edit_message)).perform(typeText(correct));
                button.performClick();
            }
        });

    }

    public Activity getActivityInstance(){

        final Activity[] currentActivity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity act: resumedActivities){
                    Log.d("Your current activity: ", act.getClass().getName());
                    currentActivity[0] = act;
                    break;
                }
            }
        });

        return currentActivity[0];
    }


}