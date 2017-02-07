package com.example.nameapp;

import android.app.Activity;
import android.app.Instrumentation;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class MainMenuTest {

    @Rule
    public ActivityTestRule<MainMenu> mActivityRule = new ActivityTestRule<>(
            MainMenu.class);

    @Test
    public void testOpenNameList() throws Exception {

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NameList.class.getName(), null, false);
        MainMenu mainMenu = (MainMenu) getActivityInstance();
        final Button button = (Button) mainMenu.findViewById(R.id.goToNameList);

        onView(withId(R.id.goToNameList)).perform(click());

        NameList nextActivity = (NameList) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();

    }

    @Test
    public void testOpenGallery() throws Exception {

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Gallery.class.getName(), null, false);
        MainMenu mainMenu = (MainMenu) getActivityInstance();
        final Button button = (Button) mainMenu.findViewById(R.id.goToGallery);

        onView(withId(R.id.goToGallery)).perform(click());

        Gallery nextActivity = (Gallery) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();

    }

    @Test
    public void testOpenLearningMode() throws Exception {

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LearningMode.class.getName(), null, false);
        MainMenu mainMenu = (MainMenu) getActivityInstance();
        final Button button = (Button) mainMenu.findViewById(R.id.goToLearningMode);

        onView(withId(R.id.goToLearningMode)).perform(click());

        LearningMode nextActivity = (LearningMode) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();


    }


    /*
    * Metode som hentar noverande aktivitet.
    * */
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