package com.parse.starter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.starter.activity.LoginActivity;
import com.parse.starter.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static junit.framework.Assert.*;

/* Given that I have an account for the app
 * I type in my username and password
 * I click the sign in button
 * and I am logged in and redirected to the main page
 * */

@RunWith(AndroidJUnit4.class)
public class loginTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule(LoginActivity.class);
    @Test
    public void firstTest() throws Exception{
        if (ParseUser.getCurrentUser() != null) ParseUser.logOut();

        // Given that I have an account for the app

        // I type in my username and password
        onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));

        // I click the sign in button
        onView(withId(R.id.signin_button)).perform(click());

        // and I am logged in and redirected to the main page
        Thread.sleep(2000);

    }
}