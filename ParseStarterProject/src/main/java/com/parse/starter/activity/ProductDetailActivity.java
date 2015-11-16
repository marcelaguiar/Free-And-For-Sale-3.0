package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.object.Item;

import java.util.Arrays;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvPrice;
    TextView tvTag;
    TextView tvDescription;
    Button btnBuy;
    Button btnCancel;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail_page);
        setTitle(R.string.title_activity_product_detail_page);

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

        tvTitle = (TextView) findViewById(R.id.item_title);
//        tvPrice = (TextView) findViewById(R.id.item_price);
//        tvTag = (TextView) findViewById(R.id.item_tag);
        tvDescription = (TextView) findViewById(R.id.item_discription);
        btnBuy = (Button)findViewById(R.id.buy_btn);
        btnCancel = (Button)findViewById(R.id.cancel_btn);


        Intent intent = getIntent();
        item = intent.getParcelableExtra("item");
        tvTitle.setText(item.title);
        tvDescription.setText(item.description);

        btnBuy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO put the product and user into the data base
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

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailActivity.this);
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
                        Toast.makeText(ProductDetailActivity.this, "log out failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "log out successed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        ProductDetailActivity.this.finish();
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
