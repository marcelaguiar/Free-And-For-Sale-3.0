package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;

public class ProductDetailActivity extends AppCompatActivity {

    TextView mTvName;
    TextView mTvPrice;
    TextView mTvTag;
    TextView mTvDescription;
    Button mBtnBuy;
    Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail_page);
        setTitle(R.string.title_activity_product_detail_page);

        initView();

        initData();
    }

    private void initView() {

        mTvName = (TextView) findViewById(R.id.item_name);
        mTvPrice = (TextView) findViewById(R.id.item_price);
        mTvTag = (TextView) findViewById(R.id.item_tag);
        mTvDescription = (TextView) findViewById(R.id.item_discription);
        mBtnBuy = (Button)findViewById(R.id.buy_btn);
        mBtnCancel = (Button)findViewById(R.id.cancel_btn);

        mBtnBuy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO put the product and user into the data base
                finish();

            }
        });

        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        //ParseObject item = (ParseObject)intent.getData();

        //TODO get the item's detail
    }

}
