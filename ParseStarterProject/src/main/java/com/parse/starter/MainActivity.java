/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


  /** Called when the user clicks the Buy button */
  public void buyPage() {
    Intent intent = new Intent(MainActivity.this, BuyPageActivity.class);
    startActivity(intent);
  }

  /** Called when the user clicks the Sell button */
  public void sellPage() {
    Intent intent = new Intent(MainActivity.this, SellPageActivity.class);
    startActivity(intent);
  }

  /** Called when the user clicks the My Listings button */
  public void myListingsPage() {
    Intent intent = new Intent(MainActivity.this, MyListingsPageActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ParseObject testObject = new ParseObject("TestObject");
    testObject.put("oof", "rab");
    testObject.saveInBackground();
    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    // jump
    findViewById(R.id.buyButton).setOnClickListener(this);
    findViewById(R.id.sellButton).setOnClickListener(this);
    findViewById(R.id.myListingsButton).setOnClickListener(this);
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

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.buyButton:
        buyPage();
        break;
      case R.id.sellButton:
        sellPage();
        break;
      case R.id.myListingsButton:
        myListingsPage();
        break;
    }
  }
}
