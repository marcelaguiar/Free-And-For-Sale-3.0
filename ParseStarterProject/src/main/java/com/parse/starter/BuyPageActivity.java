package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.logging.Logger;


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
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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

}
