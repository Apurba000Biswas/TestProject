package com.apurba.testapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;


public class VariationDialog  extends BottomSheetDialogFragment {

    private List<String> variationImages;
    private int selectedIndex;
    private View rootView;
    private List<View> variationImageViewList;
    private int quantity;

    public VariationDialog(List<String> variationImages, int selectedIndex){
        this.variationImages = variationImages;
        this.selectedIndex = selectedIndex;
        variationImageViewList = new ArrayList<>();
        quantity = 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.variation_dialog, container, false);
        rootView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rootView.findViewById(R.id.iv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedIndex++;
                if (variationImages.size() <= selectedIndex)selectedIndex = 0;
                setSelectedVariationView(selectedIndex);
            }
        });

        rootView.findViewById(R.id.tv_seize_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSizeSelectionView(view);
            }
        });

        rootView.findViewById(R.id.tv_seize_medium).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSizeSelectionView(view);
            }
        });

        rootView.findViewById(R.id.tv_seize_large).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSizeSelectionView(view);
            }
        });

        rootView.findViewById(R.id.tv_seize_xl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSizeSelectionView(view);
            }
        });

        rootView.findViewById(R.id.iv_plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                setQuantityView(quantity);
            }
        });

        rootView.findViewById(R.id.iv_minus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity == 0) return;
                quantity--;
                setQuantityView(quantity);

            }
        });

        //loadImage(selectedIndex, rootView.findViewById(R.id.image_variation));
        setVariationView();
        setSelectedVariationView(selectedIndex);

        return rootView;

    }

    private void loadImage(int index, ImageView imageView){
        Picasso.get()
                .load(variationImages.get(index))
                .fit()
                .centerCrop()
                .into(imageView);
    }

    private void setVariationView(){
        LinearLayout variationHolder = rootView.findViewById(R.id.variation_holder);

        int count = 0;
        View rowBaseHolder =  LayoutInflater.from(getContext())
                .inflate(R.layout.variation_row_view, null, false);

        for (int i=0; i<variationImages.size(); i++){
            //String img = variationImages.get(i);

            LinearLayout rowHolder = rowBaseHolder.findViewById(R.id.row_holder);
            View item = LayoutInflater.from(getContext())
                    .inflate(R.layout.variation_image_view, null, false);
            loadImage(i, item.findViewById(R.id.image_variation));

            variationImageViewList.add(item);

            CardView cardView = item.findViewById(R.id.card_view);
            int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedIndex = finalI;
                    setSelectedVariationView(selectedIndex);
                }
            });


            rowHolder.addView(item);
            count++;

            if (count == 5){
                variationHolder.addView(rowHolder);
                rowBaseHolder =  LayoutInflater.from(variationHolder.getContext())
                        .inflate(R.layout.variation_row_view, null, false);
                count = 0;
            }
        }
        variationHolder.addView(rowBaseHolder);
    }

    private void setSelectedVariationView(int index){
        loadImage(index, rootView.findViewById(R.id.image_variation));
        for (View itemView : variationImageViewList){
            itemView.findViewById(R.id.green_border).setVisibility(View.GONE);
        }
        variationImageViewList.get(index).findViewById(R.id.green_border).setVisibility(View.VISIBLE);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setSizeSelectionView(View view){
        rootView.findViewById(R.id.tv_seize_small).setBackground(null);
        rootView.findViewById(R.id.tv_seize_medium).setBackground(null);
        rootView.findViewById(R.id.tv_seize_large).setBackground(null);
        rootView.findViewById(R.id.tv_seize_xl).setBackground(null);

        view.setBackground(rootView.getContext().getDrawable(R.drawable.shape_rectangle_stroke_green_border));
    }

    private void setQuantityView(int quantity){
        ((TextView) rootView.findViewById(R.id.tv_counter)).setText(String.valueOf(quantity));
    }
}
