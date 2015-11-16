package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;

public class SellPageActivity extends AppCompatActivity {

    Button submit;
    EditText title;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

        initialize();
    }

    private void initialize() {
        submit = (Button) findViewById(R.id.submitButton);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = title.getText().toString().trim();
                String descriptionStr = description.getText().toString().trim();

                if (!titleStr.isEmpty() && !descriptionStr.isEmpty()) {
                    submit.setEnabled(false);
                    ParseObject parseObject = new ParseObject("Item");
                    parseObject.put("title", titleStr);
                    parseObject.put("description", descriptionStr);

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            submit.setEnabled(true);
                            if (e == null) {
                                Toast.makeText(SellPageActivity.this, "Post successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SellPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SellPageActivity.this);
        builder.setMessage("Are you sure to logout?");
        builder.setTitle("Hint");

        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //logout
                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    ParseUser.logOut();
                    if (ParseUser.getCurrentUser() != null) {
                        Toast.makeText(SellPageActivity.this, "log out failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SellPageActivity.this, "log out successed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SellPageActivity.this, LoginActivity.class);
                        SellPageActivity.this.finish();
                        startActivity(intent);
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


}