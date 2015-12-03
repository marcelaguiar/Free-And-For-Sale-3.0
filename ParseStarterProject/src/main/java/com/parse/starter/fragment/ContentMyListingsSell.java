package com.parse.starter.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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


public class ContentMyListingsSell extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_my_listings_buy, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.buying_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        recyclerViewAdapter = new RecyclerViewAdapter(this.getActivity(), 2);
        recyclerView.setAdapter(recyclerViewAdapter);
        getSellData();
        return view;
    }

    // retrieve and display data of items bought by the current user, from parse
    private void getSellData() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        List<String> sellProducts = (List<String>)currentUser.get("sellProduct");

        if (sellProducts == null) return;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item").whereContainedIn("objectId", sellProducts);

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

                }
            }
        });

    }
}