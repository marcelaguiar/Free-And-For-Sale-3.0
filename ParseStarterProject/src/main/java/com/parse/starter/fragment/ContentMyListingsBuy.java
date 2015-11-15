package com.parse.starter.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parse.starter.R;


public class ContentMyListingsBuy extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_my_listings_buy, container, false);
//        ImageView iv = new ImageView(getActivity());
//        iv.setImageResource(R.drawable.img1);
//
//        return iv;
    }

}
