package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.RecyclerViewAdapter;
import com.parse.starter.object.Item;

import java.util.ArrayList;
import java.util.List;


public class BuyPageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create the views for buy page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);
        setTitle(R.string.title_activity_buy_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.buying_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this, 1);
        recyclerView.setAdapter(recyclerViewAdapter);

        //get data from parse and display the items for the user to buy
        getData();
    }

    private void getData() {
        //get data from parse using a query
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<Item> items = new ArrayList<>();
                    for (ParseObject parseObject : objects) {
                        Item item = new Item();
                        item.objectId = parseObject.getObjectId();
                        item.title = parseObject.getString("title");
                        item.description = parseObject.getString("description");
                        item.createdAt = parseObject.getCreatedAt();
                        item.updatedAt = parseObject.getUpdatedAt();
                        items.add(item);
                    }
                    recyclerViewAdapter.setItems(items);
                } else {
                    Toast.makeText(BuyPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
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

    //display the dialog for logout
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BuyPageActivity.this);
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
                        Toast.makeText(BuyPageActivity.this, R.string.logout_fail, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BuyPageActivity.this, R.string.logout_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BuyPageActivity.this, LoginActivity.class);
                        BuyPageActivity.this.finish();
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
