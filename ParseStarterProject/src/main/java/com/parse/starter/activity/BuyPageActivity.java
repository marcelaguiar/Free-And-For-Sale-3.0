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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;


public class BuyPageActivity extends AppCompatActivity {
        ListView mListView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_buy_page);
            setTitle(R.string.title_activity_buy_page);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog();
                }
            });

            mListView = (ListView)findViewById(R.id.buying_list);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                    try {
                        //send buying item to product detail page
                        //TODO CAST ITEM
                        ParseObject item = (ParseObject)parent.getItemAtPosition(position);
                        Intent intent = new Intent(BuyPageActivity.this, ProductDetailActivity.class);
                        //intent.putExtra("product", item);
                        startActivity(intent);
                    } catch (Exception e) {
                        //Logger.e("", "", e);
                    }
                }
            });

            //TODO GET THE LIST CONTENT

        }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BuyPageActivity.this);
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
                        Toast.makeText(BuyPageActivity.this, "log out failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BuyPageActivity.this, "log out successed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BuyPageActivity.this, LoginActivity.class);
                        BuyPageActivity.this.finish();
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
