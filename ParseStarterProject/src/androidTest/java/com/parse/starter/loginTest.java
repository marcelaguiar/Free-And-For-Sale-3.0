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

@RunWith(AndroidJUnit4.class)
public class loginTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule(LoginActivity.class);
    @Test
    public void firstTest(){
        // logout if already logged in
        if (ParseUser.getCurrentUser() != null) ParseUser.logOut();

        // try to login the dummy user
        onView(withId(R.id.username)).perform(typeText("test"));
        onView(withId(R.id.password)).perform(typeText("test"));
        onView(withId(R.id.signin_button)).perform(click());

    }
}