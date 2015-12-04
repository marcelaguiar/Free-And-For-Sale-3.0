package com.parse.starter;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.starter.activity.BuyPageActivity;
import com.parse.starter.activity.MainActivity;
import com.parse.starter.adapter.RecyclerViewAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.*;

@RunWith(AndroidJUnit4.class)
public class sellTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);
    @Test
    public void test() throws Exception{
        // login the dummy user
        if (ParseUser.getCurrentUser() == null) ParseUser.logIn("test", "test");

        // try to sell an item
        onView(withId(R.id.sellButton)).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.title)).perform(typeText("testTitle"));
        onView(withId(R.id.description)).perform(typeText("testDesc"));
        closeSoftKeyboard();

        onView(withId(R.id.submitButton)).perform(click());

        // check if the item is in my listing page
        Thread.sleep(2000);

        // a potential failure here:
        // tried to click myListingsButton before pressBack() is finished

        onView(withId(R.id.myListingsButton)).perform(click());

        // check if the bought items is displayed
        Thread.sleep(2000);
    }
}