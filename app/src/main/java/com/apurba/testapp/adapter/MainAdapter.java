package com.apurba.testapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apurba.testapp.R;

public class MainAdapter extends RecyclerView.Adapter < RecyclerView.ViewHolder > {

    private static final int PRICE_VIEW_TYPE = 0;
    private static final int DESCRIPTION_VIEW_TYPE = 1;

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return PRICE_VIEW_TYPE;
            case 1:
                return DESCRIPTION_VIEW_TYPE;

        }
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case PRICE_VIEW_TYPE:
                View searchView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.price_view, parent, false);
                return new PriceViewHolder(searchView);
            case DESCRIPTION_VIEW_TYPE:
                //View newsFeedHeaderView = LayoutInflater.from(parent.getContext())
                  //      .inflate(R.layout.news_feed_header_view, parent, false);
                //return new NewsFeedHeaderViewHolder(newsFeedHeaderView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case PRICE_VIEW_TYPE:
                //PriceViewHolder viewHolder = (PriceViewHolder) holder;
                //viewHolder.bindNewsFeed(feedItems.get(position-5));
                break;
            case DESCRIPTION_VIEW_TYPE:
                //NewsFeedItemViewHolder viewHolder = (NewsFeedItemViewHolder) holder;
                //viewHolder.bindNewsFeed(feedItems.get(position-5));
                break;

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class PriceViewHolder extends RecyclerView.ViewHolder{

        PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.binding = binding;
        }

    }


}
