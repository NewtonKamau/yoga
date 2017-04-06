package com.example.newnyc.yoga;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.example.newnyc.yoga.ui.MainActivity;
import com.example.newnyc.yoga.ui.StudioListActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

//import org.robolectric.RobolectricGradleTestRunner;

/**
 * Created by newnyc on 3/30/17.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
//@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
  activity = Robolectric.setupActivity((MainActivity.class));
    }
    @Test
    public  void  validateTextViewContent() {
        TextView appNameTextView = (TextView) activity.findViewById(R.id.appNametextView);

        assertTrue("Yoga Studio".equals(appNameTextView.getText().toString()));
    }
    @Test
    public void secondActivityStarted() {
        activity.findViewById(R.id.findStudioButton).performClick();
        Intent expectedIntent = new Intent(activity, StudioListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent  actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }

}
