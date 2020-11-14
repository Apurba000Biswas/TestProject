package com.apurba.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;


import com.apurba.testapp.adapter.MainAdapter;
import com.apurba.testapp.adapter.ProductSliderAdapter;
import com.apurba.testapp.data.SuggestionModel;
import com.apurba.testapp.databinding.ActivityMainBinding;
import com.apurba.testapp.util.AnimationUtility;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContentView(this, R.layout.activity_main);
        setNotificationBarBlackNWhite();

        listenToCollapse();
        loadDummyProductImage();
        setAdapter();

    }


    private void loadDummyProductImage(){
        List<String> imageList = new ArrayList<>();
        imageList.add("https://img5.cfcdn.club/c5/87/c5081ddd26058ea31cb3dd84afab3687_350x350.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i1/2273235440/TB2k9MNdgLD8KJjSszeXXaGRpXa_!!2273235440.jpg");
        imageList.add("https://4.imimg.com/data4/FL/YQ/ANDROID-11914587/product-500x500.jpeg");
        imageList.add("https://ae01.alicdn.com/kf/H8d8eef92295d45b7a894b1a1b577e1d2v/Ivory-Suits-For-Wedding-Groom-Tuxedo-Black-Peak-Design-Man-Outfit-Tialored-Bridegroom-Attire-Best-Man.jpg");
        ProductSliderAdapter adapter = new ProductSliderAdapter(this);
        adapter.renewItems(imageList);
        adapter.setChangeListener(slideNum -> {
            String slideText = slideNum + "/" + imageList.size();
            binding.slideNumber.setText(slideText);
        });


        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.startAutoCycle();
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.startAutoCycle();


    }

    private void listenToCollapse(){
        Animation animation = AnimationUtility.getSlideUpAnimation(MainActivity.this);
        binding.appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0){
                //  Collapsed
                if (binding.collapsedAppBarViewHolder.getVisibility() != View.VISIBLE){
                    binding.collapsedAppBarViewHolder.setVisibility(View.VISIBLE);
                    binding.collapsedAppBarViewHolder.startAnimation(animation);
                    binding.expandedAppBarViewHolder.setVisibility(View.GONE);
                }

            }else{
                //Expanded
                binding.expandedAppBarViewHolder.setVisibility(View.VISIBLE);
                binding.collapsedAppBarViewHolder.setVisibility(View.GONE);
            }
        });
    }


    protected boolean isBuildVersionOk(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    protected void setNotificationBarBlackNWhite(){
        if (isBuildVersionOk()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



    private void setAdapter(){

        binding.rvProductDetails.setHasFixedSize(true);
        binding.rvProductDetails.setItemViewCacheSize(20);
        binding.rvProductDetails.setDrawingCacheEnabled(true);
        binding.rvProductDetails.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL
                , false);
        binding.rvProductDetails.setLayoutManager(layoutManager);

        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        List<String> variationImages = new ArrayList<>();
        variationImages.add("https://cdn.shopify.com/s/files/1/0094/8466/4890/products/product-image-464869218_1200x1200.jpg?v=1574547069");
        variationImages.add("https://cf.shopee.com.my/file/3e4564110e7cfa18ceca19998be8af4d");
        variationImages.add("https://ae01.alicdn.com/kf/HTB1EfLjN3TqK1RjSZPhq6xfOFXas/Classic-Plaid-Men-s-Suit-Dress-Slim-Fit-Tuxedo-Wedding-Evening-Clothing-British-Style-Checkered-Formal.jpg");
        variationImages.add("https://labane.com/wp-content/uploads/2019/10/Mens-Blazer-business-formal-Slim-Blaser-Clothing-Men-BL104.jpg");

        variationImages.add("https://cdn.shopify.com/s/files/1/0094/8466/4890/products/product-image-464869218_1200x1200.jpg?v=1574547069");
        variationImages.add("https://cf.shopee.com.my/file/3e4564110e7cfa18ceca19998be8af4d");
        variationImages.add("https://cdn.shopify.com/s/files/1/0094/8466/4890/products/product-image-464869218_1200x1200.jpg?v=1574547069");
        variationImages.add("https://cf.shopee.com.my/file/3e4564110e7cfa18ceca19998be8af4d");
        variationImages.add("https://ae01.alicdn.com/kf/HTB1EfLjN3TqK1RjSZPhq6xfOFXas/Classic-Plaid-Men-s-Suit-Dress-Slim-Fit-Tuxedo-Wedding-Evening-Clothing-British-Style-Checkered-Formal.jpg");
        variationImages.add("https://labane.com/wp-content/uploads/2019/10/Mens-Blazer-business-formal-Slim-Blaser-Clothing-Men-BL104.jpg");

        variationImages.add("https://cdn.shopify.com/s/files/1/0094/8466/4890/products/product-image-464869218_1200x1200.jpg?v=1574547069");
        variationImages.add("https://cf.shopee.com.my/file/3e4564110e7cfa18ceca19998be8af4d");


        //variationImages.add("https://ae01.alicdn.com/kf/HTB1EfLjN3TqK1RjSZPhq6xfOFXas/Classic-Plaid-Men-s-Suit-Dress-Slim-Fit-Tuxedo-Wedding-Evening-Clothing-British-Style-Checkered-Formal.jpg");
        //variationImages.add("https://labane.com/wp-content/uploads/2019/10/Mens-Blazer-business-formal-Slim-Blaser-Clothing-Men-BL104.jpg");
        adapter.setVariationImageList(variationImages);

        List<SuggestionModel> suggestionList = new ArrayList<>();
        suggestionList.add(new SuggestionModel("https://media.gq-magazine.co.uk/photos/5dbc4d5a8da8f900083b9076/master/w_1000,c_limit/20191028-watch-guide-baume.jpg", "Microsoft Game Console", "BDT. 22,850", "34% off", "Sold: 22554", "4.8"));
        suggestionList.add(new SuggestionModel("https://5.imimg.com/data5/BS/GH/MY-19259497/bagpack-500x500.jpg", "Ray & Ray Multicolor", "BDT. 3,250", "34% off", "Sold: 2251", "4.8"));

        suggestionList.add(new SuggestionModel("https://images-na.ssl-images-amazon.com/images/I/81VcECxgLAL._UL1500_.jpg", "Microsoft Game Console", "BDT. 22,850", "34% off", "Sold: 22554", "4.8"));
        suggestionList.add(new SuggestionModel("https://www.ikea.com/us/en/images/products/frakta-shopping-bag-large-blue__0711231_PE728076_S5.JPG", "Ray & Ray Multicolor", "BDT. 3,250", "34% off", "Sold: 2251", "4.8"));

        suggestionList.add(new SuggestionModel("https://image.shutterstock.com/image-photo/luxury-classic-watch-white-dial-260nw-1703753980.jpg", "Microsoft Game Console", "BDT. 22,850", "34% off", "Sold: 22554", "4.8"));
        suggestionList.add(new SuggestionModel("https://static.bhphoto.com/images/images2500x2500/1529950038_1418797.jpg", "Ray & Ray Multicolor", "BDT. 3,250", "34% off", "Sold: 2251", "4.8"));

        suggestionList.add(new SuggestionModel("https://cf.shopee.ph/file/fa89da4bc09f2301595bd475c5c6b675", "Microsoft Game Console", "BDT. 22,850", "34% off", "Sold: 22554", "4.8"));

        adapter.setSuggestionList(suggestionList);

        binding.rvProductDetails.setAdapter(adapter);
    }

}