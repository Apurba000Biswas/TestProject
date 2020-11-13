package com.apurba.testapp.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apurba.testapp.R;
import com.apurba.testapp.data.SuggestionModel;
import com.apurba.testapp.databinding.SuggestionItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter < RecyclerView.ViewHolder > {

    private static final int PRICE_DESCRIPTION__VIEW_TYPE = 0;
    private static final int PRODUCT_TYPE_VARIATION_VIEW_TYPE = 1;
    private static final int SHIPPING_VIEW_TYPE = 2;
    private static final int MENU_VIEW_TYPE = 3;
    private static final int DESCRIPTION_VIEW_TYPE = 4;
    private static final int SUGGESTION_HEADER_VIEW_TYPE = 5;
    private static final int SUGGESTION_VIEW_TYPE = 6;

    private List<String> variationImageList;
    private List<SuggestionModel> suggestionList;

    public void setVariationImageList(List<String> variationImageList){
        this.variationImageList = variationImageList;
    }

    public void setSuggestionList(List<SuggestionModel> suggestionList){
        this.suggestionList = suggestionList;
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
            case 5:
                return SUGGESTION_HEADER_VIEW_TYPE;
            default:
                return SUGGESTION_VIEW_TYPE;
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
            case SUGGESTION_HEADER_VIEW_TYPE:
                View suggestionHeaderView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.suggestion_header_view, parent, false);
                return new SuggestionViewHolder(suggestionHeaderView);

            default:
                SuggestionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.suggestion_item, parent, false);

                return new SuggestionItemHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case PRICE_DESCRIPTION__VIEW_TYPE:
                break;
            case PRODUCT_TYPE_VARIATION_VIEW_TYPE:

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
                break;
            case SUGGESTION_VIEW_TYPE:
                SuggestionItemHolder suggestionItemHolder = (SuggestionItemHolder) holder;

                int item1Pos = getItem1position(position-6);
                SuggestionModel item1 = (item1Pos< suggestionList.size())
                        ? suggestionList.get(item1Pos) : null;
                SuggestionModel item2 = (item1Pos+1 < suggestionList.size())
                        ? suggestionList.get(item1Pos+1) : null;

                suggestionItemHolder.bind(item1, item2);

                //

        }
    }

    private int getItem1position(int absolutePosition){
        return (absolutePosition*2);
    }

    @Override
    public int getItemCount() {
        if (suggestionList == null) return 6;

        if (suggestionList.size()%2 == 0) return suggestionList.size()/2 + 6;

        return (suggestionList.size()/2) + 1 + 6;
    }

    class PriceViewHolder extends RecyclerView.ViewHolder{

        PriceViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.binding = binding;
        }
    }

    class VariationViewTypeHolder extends RecyclerView.ViewHolder{

        private LinearLayout variationHolder;

        public VariationViewTypeHolder(@NonNull View itemView) {
            super(itemView);
            variationHolder = itemView.findViewById(R.id.variation_base_holder);
        }

        void bindViews(List<String> variationImages){
            if (variationImages == null) return;

            variationHolder.removeAllViews();
            if (variationImages.isEmpty()){
                return;
            }
            int count = 0;

            View rowBaseHolder =  LayoutInflater.from(variationHolder.getContext())
                    .inflate(R.layout.variation_row_view, null, false);

            for (int i=0; i<variationImages.size(); i++){
                String img = variationImages.get(i);

                LinearLayout rowHolder = rowBaseHolder.findViewById(R.id.row_holder);
                View item = LayoutInflater.from(variationHolder.getContext())
                        .inflate(R.layout.variation_image_view, null, false);
                loadImage(img, item.findViewById(R.id.image_variation));
                CardView cardView = item.findViewById(R.id.card_view);
                int finalI = i;
                cardView.setOnClickListener(view -> Toast.makeText(view.getContext(), "Clicked on "
                        + finalI, Toast.LENGTH_SHORT).show());

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
            loadImage(largeImgUrl, descriptionImageLarge);
            loadImage(leftImgUrl, descriptionImageLeft);
            loadImage(rightImgUrl, descriptionImageRight);

        }
    }

    class SuggestionViewHolder extends RecyclerView.ViewHolder{

        public SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class SuggestionItemHolder extends RecyclerView.ViewHolder{
        SuggestionItemBinding binding;

        public SuggestionItemHolder(@NonNull SuggestionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SuggestionModel item1, SuggestionModel item2){

            binding.setItem1(item1);
            if (item2 == null){
                binding.groupItem2Group.setVisibility(View.GONE);
                return;
            }
            binding.setItem2(item2);
        }
    }

    private void loadImage(String url, ImageView imageView){
        Picasso.get()
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView);
    }


}
