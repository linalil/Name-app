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
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class NameListTest {


    @Rule
    public ActivityTestRule<NameList> mActivityRule = new ActivityTestRule<>(
            NameList.class);

    @Test
    public void goBack() throws Exception {

    }

    @Test
    public void testAddNewPersonButton() throws Exception {

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(addNewPersonActivity.class.getName(), null, false);
        NameList mainMenu = (NameList) getActivityInstance();
        final Button button = (Button) mainMenu.findViewById(R.id.addNewPersonBtn);

        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });

        addNewPersonActivity nextActivity = (addNewPersonActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
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