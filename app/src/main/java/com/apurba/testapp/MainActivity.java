package com.apurba.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.apurba.testapp.adapter.ProductSliderAdapter;
import com.apurba.testapp.databinding.ActivityMainBinding;
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


        loadDummyProductImage();

    }


    private void loadDummyProductImage(){
        List<String> imageList = new ArrayList<>();
        imageList.add("https://img5.cfcdn.club/c5/87/c5081ddd26058ea31cb3dd84afab3687_350x350.jpg");
        imageList.add("https://img.alicdn.com/imgextra/i1/2273235440/TB2k9MNdgLD8KJjSszeXXaGRpXa_!!2273235440.jpg");
        imageList.add("https://4.imimg.com/data4/FL/YQ/ANDROID-11914587/product-500x500.jpeg");
        imageList.add("https://ae01.alicdn.com/kf/H8d8eef92295d45b7a894b1a1b577e1d2v/Ivory-Suits-For-Wedding-Groom-Tuxedo-Black-Peak-Design-Man-Outfit-Tialored-Bridegroom-Attire-Best-Man.jpg");
        ProductSliderAdapter adapter = new ProductSliderAdapter(this);
        adapter.renewItems(imageList);

        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.startAutoCycle();
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.startAutoCycle();


    }
}