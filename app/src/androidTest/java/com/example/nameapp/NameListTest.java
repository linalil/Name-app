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
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

/**
 * Created by linalil on 31.01.2017.
 */
public class NameListTest {


    @Rule
    public ActivityTestRule<NameList> mActivityRule = new ActivityTestRule<>(
            NameList.class);

    @Test
    public void goBack() throws Exception {
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()));

    }

    @Test
    public void testAddNewPersonButton() throws Exception {

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(addNewPersonActivity.class.getName(), null, false);
        NameList mainMenu = (NameList) getActivityInstance();
        final Button button = (Button) mainMenu.findViewById(R.id.addNewPersonBtn);

        onView(withId(R.id.addNewPersonBtn)).perform(click());

        addNewPersonActivity nextActivity = (addNewPersonActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
    }


    /*
    * Sjekkar at personar fins i lista.
    * */
    @Test
    public void personsInList() throws Exception {
        onData(hasToString(containsString("Lina")));
        onData(hasToString(containsString("Thea")));
        onData(hasToString(containsString("Marita")));
    }

    @Test
    public void personsNotInList() throws Exception {
        onData(not(hasToString(containsString("Kari"))));
    }

    /*
    * Metode som hentar noverande aktivitet.
    * */
    public Activity getActivityInstance() {

        final Activity[] currentActivity = new Activity[1];

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity act : resumedActivities) {
                    Log.d("Your current activity: ", act.getClass().getName());
                    currentActivity[0] = act;
                    break;
                }
            }
        });

        return currentActivity[0];
    }


}