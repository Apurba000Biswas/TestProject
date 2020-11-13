package com.apurba.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class VariationDialog  extends BottomSheetDialogFragment {

    private List<String> variationImages;
    private int selectedIndex;
    private View rootView;

    public VariationDialog(List<String> variationImages, int selectedIndex){
        this.variationImages = variationImages;
        this.selectedIndex = selectedIndex;
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

        loadImage(selectedIndex, rootView.findViewById(R.id.image_variation));
        setVariationView();

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

            /*
            CardView cardView = item.findViewById(R.id.card_view);
            int finalI = i;
            cardView.setOnClickListener(view ->
                    showVariationDialog(variationImages, finalI)
            );

             */

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
}
