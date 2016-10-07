package com.silicon.rxjavaexample;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WidgetsActivityTest {

    @Rule
    public ActivityTestRule<WidgetsActivity> mActivityTestRule = new ActivityTestRule<>(WidgetsActivity.class);

    @Test
    public void widgetsActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button), withText("New Button"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button), withText("New Button"), isDisplayed()));
        appCompatButton2.perform(click());

    }

}
