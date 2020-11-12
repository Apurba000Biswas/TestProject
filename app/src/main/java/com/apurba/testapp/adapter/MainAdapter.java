package com.apurba.testapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apurba.testapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter < RecyclerView.ViewHolder > {

    private static final int PRICE_DESCRIPTION__VIEW_TYPE = 0;
    private static final int PRODUCT_TYPE_VARIATION_VIEW_TYPE = 1;
    private static final int SHIPPING_VIEW_TYPE = 2;
    private static final int MENU_VIEW_TYPE = 3;
    private static final int DESCRIPTION_VIEW_TYPE = 4;

    private List<String> variationImageList;

    public void setVariationImageList(List<String> variationImageList){
        this.variationImageList = variationImageList;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return PRICE_DESCRIPTION__VIEW_TYPE;
            case 1:
                return PRODUCT_TYPE_VARIATION_VIEW_TYPE;
            case 2:
                return SHIPPING_VIEW_TYPE;
            case 3:
                return MENU_VIEW_TYPE;
            case 4:
                return DESCRIPTION_VIEW_TYPE;
            default:
                return 0;
        }
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case PRICE_DESCRIPTION__VIEW_TYPE:
                View searchView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.price_description_view, parent, false);
                return new PriceViewHolder(searchView);
            case PRODUCT_TYPE_VARIATION_VIEW_TYPE:
                View productVariationView = LayoutInflater.from(parent.getContext())
                     .inflate(R.layout.product_type_variation_view, parent, false);
                return new VariationViewTypeHolder(productVariationView);
            case SHIPPING_VIEW_TYPE:
                View shippingView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.shipping_view, parent, false);
                return new ShippingViewHolder(shippingView);

            case MENU_VIEW_TYPE:
                View menuView =   LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.menu_view, parent, false);

                return new MenuViewHolder(menuView);
            case DESCRIPTION_VIEW_TYPE:
                View descriptionView =  LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.description_view, parent, false);
                return new DescriptionViewHolder(descriptionView);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case PRICE_DESCRIPTION__VIEW_TYPE:
                //PriceViewHolder viewHolder = (PriceViewHolder) holder;
                //viewHolder.bindNewsFeed(feedItems.get(position-5));
                break;
            case PRODUCT_TYPE_VARIATION_VIEW_TYPE:
                //NewsFeedItemViewHolder viewHolder = (NewsFeedItemViewHolder) holder;
                //viewHolder.bindNewsFeed(feedItems.get(position-5));
                VariationViewTypeHolder viewHolder = (VariationViewTypeHolder) holder;
                viewHolder.bindViews(variationImageList);
                break;
            case  DESCRIPTION_VIEW_TYPE:
                DescriptionViewHolder descriptionViewHolder = (DescriptionViewHolder) holder;

                // lets check with the dummy url
                String largeImage = "https://www.revzilla.com/blog_content_image/image/66087/MerlineOlivia_Jeans_CT_GG_2020.jpg";
                String leftImage = "https://static01.nyt.com/images/2010/01/21/fashion/21jeans-2/popup.jpg?quality=90&auto=webp";
                String rightImage = "https://ae01.alicdn.com/kf/HTB19QnTa.vrK1RjSspcq6zzSXXas/Enjeolon-2020-New-Mens-Jeans-Brand-Black-Jeans-Men-Fashion-Long-Trousers-Mens-Denim-Jeans-Pants.jpg";


                descriptionViewHolder.bindView(largeImage, leftImage, rightImage);

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class PriceViewHolder extends RecyclerView.ViewHolder{

        PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.binding = binding;
        }

    }

    class VariationViewTypeHolder extends RecyclerView.ViewHolder{

        private GridView gridView;

        public VariationViewTypeHolder(@NonNull View itemView) {
            super(itemView);
            gridView = itemView.findViewById(R.id.variationImageGridView);
        }

        void bindViews(List<String> variationImages){
            if (variationImages == null) return;

            VariationAdapter adapter = new VariationAdapter(gridView.getContext(), variationImages);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // set an Intent to Another Activity
                    //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    //intent.putExtra("image", logos[position]); // put image data in Intent
                    //startActivity(intent); // start Intent
                    Toast.makeText(gridView.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ShippingViewHolder extends  RecyclerView.ViewHolder{

        public ShippingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MenuViewHolder extends  RecyclerView.ViewHolder{

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class DescriptionViewHolder extends   RecyclerView.ViewHolder{

        private ImageView descriptionImageLarge;
        private ImageView descriptionImageLeft;
        private ImageView descriptionImageRight;

        public DescriptionViewHolder(@NonNull View itemView) {
            super(itemView);

            descriptionImageLarge = itemView.findViewById(R.id.iv_description_image_large);
            descriptionImageLeft = itemView.findViewById(R.id.iv_description_image_left);
            descriptionImageRight = itemView.findViewById(R.id.iv_description_image_right);
        }

        void bindView(String largeImgUrl, String leftImgUrl, String rightImgUrl){



            Picasso.get()
                    .load(largeImgUrl)
                    .fit()
                    .centerCrop()
                    .into(descriptionImageLarge);

            Picasso.get()
                    .load(leftImgUrl)
                    .fit()
                    .centerCrop()
                    .into(descriptionImageLeft);

            Picasso.get()
                    .load(rightImgUrl)
                    .fit()
                    .centerCrop()
                    .into(descriptionImageRight);

        }
    }


}
