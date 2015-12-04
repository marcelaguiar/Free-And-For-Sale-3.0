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

/* Given that I am logged in
 * I click the sell button
 * I type in the title and description of the item that I want to sell
 * I click the submit button
 * I click the mylisting button
 * and I find that the item that I posted is there
 * */

@RunWith(AndroidJUnit4.class)
public class sellTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);
    @Test
    public void test() throws Exception{
        // Given that I am logged in
        if (ParseUser.getCurrentUser() == null) ParseUser.logIn("test", "test");

        // I click the sell button
        onView(withId(R.id.sellButton)).perform(click());

        Thread.sleep(2000);

        // I type in the title and description of the item that I want to sell
        onView(withId(R.id.title)).perform(typeText("testTitle"));
        onView(withId(R.id.description)).perform(typeText("testDesc"));
        closeSoftKeyboard();

        // I click the submit button
        onView(withId(R.id.submitButton)).perform(click());

        Thread.sleep(2000);

        // I click the mylisting button
        onView(withId(R.id.myListingsButton)).perform(click());

        // and I find that the item that I posted is there
        Thread.sleep(2000);
    }
}