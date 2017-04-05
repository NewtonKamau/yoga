package com.example.newnyc.yoga;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.newnyc.yoga.ui.StudioListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by newnyc on 3/31/17.
 */

public class StudioActivityInstrumentationTest {


    @Rule
    public ActivityTestRule<StudioListActivity> activityTestRule =
            new ActivityTestRule<>(StudioListActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectStudio() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String studioName = "Mi Mero Mole";
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());
        onView(withText(studioName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(studioName)));
    }
}
