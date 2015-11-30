package com.parse.starter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.starter.R;
import com.parse.starter.activity.BuyPageActivity;
import com.parse.starter.activity.ProductDetailActivity;
import com.parse.starter.object.Item;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TextViewHolder> {

    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<Item> items;
    private int whichCase;

    public RecyclerViewAdapter(Context context, int whichCase) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.whichCase = whichCase;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(layoutInflater.inflate(R.layout.list_buy_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.title.setText(items.get(position).title);
        holder.description.setText(items.get(position).description);

        final int curPosition = position;

        if (whichCase == 1 || whichCase == 2) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("item", items.get(curPosition));
                    intent.putExtra("whichCase", whichCase);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        View itemView;

        TextViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            itemView = view;

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
//                }
//            });
        }
    }
}
