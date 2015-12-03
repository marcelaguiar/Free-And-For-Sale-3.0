package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.object.Item;

import java.util.Arrays;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvPrice;
    TextView tvTag;
    TextView tvDescription;
    Button btnBuy;
    Button btnCancel;
    Item item;
    int whichCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail_page);
        setTitle(R.string.title_activity_product_detail_page);

        initialize();
    }

    private void initialize() {

        tvTitle = (TextView) findViewById(R.id.item_title);
//        tvPrice = (TextView) findViewById(R.id.item_price);
//        tvTag = (TextView) findViewById(R.id.item_tag);
        tvDescription = (TextView) findViewById(R.id.item_discription);
        btnBuy = (Button)findViewById(R.id.buy_btn);
        btnCancel = (Button)findViewById(R.id.cancel_btn);


        Intent intent = getIntent();
        whichCase = intent.getIntExtra("whichCase", 0);
        item = intent.getParcelableExtra("item");
        tvTitle.setText(item.title);
        tvDescription.setText(item.description);

        if (whichCase == 1) { // handle the case when the user is on a buy page
            btnBuy.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // put the item and user in database
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    currentUser.addUnique("buyProduct", item.objectId);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(ProductDetailActivity.this,
                                        "An email with your info has already sent to seller successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });
        } else if (whichCase == 2) { // handle the case when the user is on my listing page
            btnBuy.setText("Delete");
            btnBuy.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // delete the item in database
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
                    query.whereEqualTo("objectId", item.objectId);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null) {
                                for (ParseObject delete: objects) {
                                    delete.deleteInBackground();
                                    Toast.makeText(ProductDetailActivity.this, "Delete Successfully", Toast.LENGTH_SHORT);
                                }
                                finish();
//                                ProductDetailActivity.this.startActivity(new Intent(ProductDetailActivity.this, MyListingsPageActivity.class));
                            } else {
                                Toast.makeText(ProductDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                            }
                        }
                    });

                }
            });
        }

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailActivity.this);
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
                        Toast.makeText(ProductDetailActivity.this, R.string.logout_fail, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductDetailActivity.this, R.string.logout_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        ProductDetailActivity.this.finish();
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
