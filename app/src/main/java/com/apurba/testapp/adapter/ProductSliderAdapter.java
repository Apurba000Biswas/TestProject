package com.apurba.testapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apurba.testapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductSliderAdapter extends SliderViewAdapter<ProductSliderAdapter.SliderAdapterVH> {


    private Context context;
    private List<String> mSliderItems = new ArrayList<>();

    public ProductSliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<String> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_slider_view, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        String sliderUrl = mSliderItems.get(position);

        Picasso.get()
                .load(sliderUrl)
                .fit()
                .centerCrop()
                .into(viewHolder.productImage);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {


        ImageView productImage;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product_image);

        }


    }
}
