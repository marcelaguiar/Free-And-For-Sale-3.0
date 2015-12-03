package com.parse.starter.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.activity.LoginActivity;

public class loginTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private LoginActivity currActivity;
    private ParseUser testUser;

    EditText username;
    EditText password;

    Button login;
    Button register;

    public loginTest() {
        super(LoginActivity.class);
    }

    protected void setUp() throws Exception{
        super.setUp();
        //Parse.initialize(getInstrumentation().getTargetContext().getApplicationContext(), "gds31uY6gWaaWNDQN4cuzeLL9sw6rgksONFfUMDp", "wn0QCctHbE3sXOxl4WrfEe9GpNLbPNw9AbOZlhCV");
        //ParseACL defaultACL = new ParseACL();
        //ParseACL.setDefaultACL(defaultACL, true);

        currActivity = getActivity();
        testUser = ParseUser.getCurrentUser();


        username = (EditText) currActivity.findViewById(R.id.username);
        password = (EditText) currActivity.findViewById(R.id.password);
        Button login = (Button) currActivity.findViewById(R.id.signin_button);
        Button register = (Button) currActivity.findViewById(R.id.register_button);
    }

    public void test() {
        currActivity.runOnUiThread(new Runnable() {
            public void run() {
                // make sure testUser is null before trying to register
                assertNull(testUser);
                //testUser.getUsername();

                username.setText("test");
                password.setText("test");

                // register a dummy user
                register.callOnClick();

                //System.out.println(testUser.getUsername());

                // test whether testUser is null after registration.
                assertNotNull(testUser);
            }
        });
    }
}