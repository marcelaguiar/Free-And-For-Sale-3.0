package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
                    final ParseObject parseObject = new ParseObject("Item");
                    parseObject.put("title", titleStr);
                    parseObject.put("description", descriptionStr);
                    parseObject.getACL().setPublicReadAccess(true);

                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            submit.setEnabled(true);
                            if (e == null) {
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                currentUser.addUnique("sellProduct", parseObject.getObjectId());

                                currentUser.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if (e == null) {
                                            Toast.makeText(SellPageActivity.this, "Post successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(SellPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            } else {
                                Toast.makeText(SellPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                dialog();
        }


        return super.onOptionsItemSelected(item);
    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SellPageActivity.this);
        builder.setMessage(R.string.logout_verify);
        builder.setTitle(R.string.hint);

        builder.setPositiveButton(R.string.action_logout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //logout
                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    ParseUser.logOut();
                    if (ParseUser.getCurrentUser() != null) {
                        Toast.makeText(SellPageActivity.this, R.string.logout_fail, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SellPageActivity.this, R.string.logout_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SellPageActivity.this, LoginActivity.class);
                        SellPageActivity.this.finish();
                        startActivity(intent);
                    }
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


}
