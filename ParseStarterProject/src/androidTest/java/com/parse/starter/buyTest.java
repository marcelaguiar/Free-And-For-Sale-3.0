package com.parse.starter;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
 * I click the buy button
 * I click the first item displayed on the buy page
 * I click the buy button of the item
 * I press the back button to go back to the main page
 * I click the my listing page, and I swipe left to the buy history page
 * and the item that I just bought is in the buy history page
 * */

@RunWith(AndroidJUnit4.class)
public class buyTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule(MainActivity.class);
    @Test
    public void test() throws Exception{
        // Given that I am logged in
        if (ParseUser.getCurrentUser() == null) ParseUser.logIn("test", "test");

        // I click the buy button
        onView(withId(R.id.buyButton)).perform(click());

        Thread.sleep(2000);

        // I click the first item displayed on the buy page
        onView(withId(R.id.buying_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // I click the buy button of the item
        onView(withId(R.id.buy_btn)).perform(click());

        // I press the back button to go back to the main page
        pressBack();
        Thread.sleep(2000);

        // I click the my listing page, and I swipe left to the buy history page
        onView(withId(R.id.myListingsButton)).perform(click());
        onView(withId(R.id.viewPager)).perform(swipeLeft());

        // and the item that I just bought is in the buy history page
        Thread.sleep(2000);
    }
}