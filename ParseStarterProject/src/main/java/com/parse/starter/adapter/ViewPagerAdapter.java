package com.parse.starter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.parse.starter.fragment.ContentMyListingsBuy;
import com.parse.starter.fragment.ContentMyListingsSell;

import java.util.Locale;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        // Returns the number of tabs
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        // Returns a new instance of the fragment
        switch (position) {
            case 0:
                return new ContentMyListingsSell();
            case 1:
                return new ContentMyListingsBuy();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Buy".toUpperCase(l);
            case 1:
                return "Sell".toUpperCase(l);
        }
        return null;
    }
}
