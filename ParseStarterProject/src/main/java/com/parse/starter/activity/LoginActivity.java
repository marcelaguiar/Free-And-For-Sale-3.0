package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create the views for login page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.app_name);

        initialize();
    }

    private void initialize() {
        // if the user has logged in, simply go to main page
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            goToMain();
        }

        //
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.signin_button);
        Button register = (Button) findViewById(R.id.register_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        // get string from user
        String usernameStr = username.getText().toString().trim();
        String passwordStr = password.getText().toString();

        switch(v.getId()) {
            case R.id.signin_button:
                // signin the user only when username and password are non-empty
                if (!usernameStr.isEmpty() && !passwordStr.isEmpty()) {
                    ParseUser.logInInBackground(usernameStr, passwordStr, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            goToMain();
                        } else {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
                }
                break;

            case R.id.register_button:
                // register the user only when username and password are non-empty
                if (!usernameStr.isEmpty() && !passwordStr.isEmpty()) {
                    ParseUser user = new ParseUser();
                    user.setUsername(usernameStr);
                    user.setPassword(passwordStr);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                        if (e != null)
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                            goToMain();
                        }
                    });
                }
                break;
        }
    }
}