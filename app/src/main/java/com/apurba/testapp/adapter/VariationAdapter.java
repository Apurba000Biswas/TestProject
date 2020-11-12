package com.apurba.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apurba.testapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VariationAdapter extends BaseAdapter {

    private List<String> imageList;
    private LayoutInflater layoutInflater;


    public VariationAdapter(Context context, List<String> imageList){
        this.imageList = imageList;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.variation_image_view, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);

        Picasso.get()
                .load(imageList.get(position))
                .fit()
                .centerCrop()
                .into(icon);

        return convertView;
    }
}
