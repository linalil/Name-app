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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by linalil on 31.01.2017.
 */
public class addNewPersonActivityTest {

    @Rule
    public ActivityTestRule<addNewPersonActivity> mActivityRule = new ActivityTestRule<>(addNewPersonActivity.class);

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
        onView(withId(R.id.edit_name)).perform(typeText("Per")).check(matches(withText("Per")));
    }

    /*
    * Sjekker om aktiviteten slutter når man trykker på legg "Add person"-knappen
    * */
    @Test
    public void addPersonToAppFinishing() throws Exception {
        final addNewPersonActivity addNewPerson = (addNewPersonActivity) getActivityInstance();
        final Button button = (Button) addNewPerson.findViewById(R.id.goToNameList);

        addNewPerson.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
                assertTrue(addNewPerson.isFinishing());
            }
        });
    }

    /*
    * Sjekker om aktiviteten slutter når man trykker på legg "Cancel"-knappen
    * */
    @Test
    public void cancelAddPersonToAppFinishing() throws Exception {
        final addNewPersonActivity addNewPerson = (addNewPersonActivity) getActivityInstance();
        final Button button = (Button) addNewPerson.findViewById(R.id.cancelAddPerson);

        addNewPerson.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
                assertTrue(addNewPerson.isFinishing());
            }
        });
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