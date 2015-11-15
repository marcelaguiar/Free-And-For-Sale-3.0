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

        recyclerView = (RecyclerView) findViewById(R.id.buying_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        getData();

    }

    private void getData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    List<Item> items = new ArrayList<>();
                    for (ParseObject parseObject : objects) {
                        Item item = new Item();
                        item.title = parseObject.getString("title");
                        item.description = parseObject.getString("description");
                        item.createdAt = parseObject.getCreatedAt();
                        item.updatedAt = parseObject.getUpdatedAt();
                        items.add(item);
                        System.out.println(item.createdAt);
                    }
                    recyclerViewAdapter.setItems(items);
                } else {
                    Toast.makeText(BuyPageActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                }
            }
        });
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
