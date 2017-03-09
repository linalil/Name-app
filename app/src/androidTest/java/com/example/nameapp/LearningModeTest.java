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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

/**
 * Created by linalil on 31.01.2017.
 */
public class LearningModeTest {


    private static final String mStringToBeTyped = "Espresso";

    @Rule
    public ActivityTestRule<LearningMode> mActivityRule = new ActivityTestRule<>(
            LearningMode.class);

    /*Sjekkar at score blir riktig berekna ved riktig svar.*/
    @Test
    public void checkScoreCorrectAnswer() throws Exception {

        final LearningMode learningMode = (LearningMode) getActivityInstance();
        final Button button = (Button) learningMode.findViewById(R.id.checkAnswer);

        String correct = learningMode.gameCenter.correctName;

        Integer scoreBefore = learningMode.gameCenter.attempts;
        Integer attemptsBefore = learningMode.gameCenter.attempts;

        onView(withId(R.id.edit_message)).perform(typeText(correct));

        onView(withId(R.id.checkAnswer)).perform(click());

        Integer scoreAfter = learningMode.gameCenter.score;
        Integer attemptsAfter = learningMode.gameCenter.attempts;

        assertTrue(scoreAfter == (scoreBefore + 1));
        assertTrue(attemptsAfter == (attemptsBefore + 1));
    }

    /* Sjekkar at score blir riktig berekna ved feil svar. */
    @Test
    public void checkScoreWrongAnswer() throws Exception {

        final LearningMode learningMode = (LearningMode) getActivityInstance();
        final Button button = (Button) learningMode.findViewById(R.id.checkAnswer);

        String wrong = "Feil";

        Integer scoreBefore = learningMode.gameCenter.attempts;
        Integer attemptsBefore = learningMode.gameCenter.attempts;

        onView(withId(R.id.edit_message)).perform(typeText(wrong));

        onView(withId(R.id.checkAnswer)).perform(click());

        Integer scoreAfter = learningMode.gameCenter.score;
        Integer attemptsAfter = learningMode.gameCenter.attempts;

        assertTrue(scoreAfter == scoreBefore);
        assertTrue(attemptsAfter == (attemptsBefore + 1));
    }

    //Hentar det som er den noverande aktiviteten.
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

    /* Testar at ein går tilbake på riktig måte. */
    @Test
    public void goBack() throws Exception {
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.imageView2)).check(matches(isDisplayed()));

    }


}