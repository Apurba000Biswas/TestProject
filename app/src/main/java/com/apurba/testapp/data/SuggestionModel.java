package com.apurba.testapp.data;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class SuggestionModel {
    private String imgUrl;
    private String name;
    private String price;
    private String discount;
    private String soldAmount;
    private String ratings;

    public SuggestionModel(String imgUrl
            , String name
            , String price
            , String discount
            , String soldAmount
            , String ratings) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.soldAmount = soldAmount;
        this.ratings = ratings;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getSoldAmount() {
        return soldAmount;
    }

    public String getRatings() {
        return ratings;
    }

    // important code for loading image here
    @BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imageURL) {
        Picasso.get()
                .load(imageURL)
                .fit()
                .centerCrop()
                .into(imageView);
    }

}
