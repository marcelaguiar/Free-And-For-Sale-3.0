package com.parse.starter.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.ViewPagerAdapter;

public class MyListingsPageActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // the tablayout
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        // Set up the ViewPager with the sections adapter.
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);

        for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(viewPagerAdapter.getPageTitle(i)));
        }

        tabLayout.setOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);

    }

    // listener for TabLayout's tabs
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyListingsPageActivity.this);
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
                        Toast.makeText(MyListingsPageActivity.this, "log out failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyListingsPageActivity.this, "log out successed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MyListingsPageActivity.this, LoginActivity.class);
                        MyListingsPageActivity.this.finish();
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
