package com.example.newnyc.yoga;

import android.support.test.rule.ActivityTestRule;

import com.example.newnyc.yoga.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by newnyc on 3/30/17.
 */

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public  void validateEditText() {
        onView(withId(R.id.locationEditText)).perform(typeText("Portland"))
                .check(matches(withText("Portland")));
    }
    @Test
    public void locationIsSentToRestaurantActivity() {
        String location = "Portland";
        onView(withId(R.id.locationEditText)).perform(typeText(location), closeSoftKeyboard());
        onView(withId(R.id.findStudioButton)).perform(click());
        onView(withId(R.id.locationTextView)).check(matches(withText("Here are the studios near" + location)));
    }
}


