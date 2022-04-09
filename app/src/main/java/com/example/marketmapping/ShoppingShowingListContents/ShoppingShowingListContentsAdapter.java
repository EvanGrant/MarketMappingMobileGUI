package com.example.marketmapping.ShoppingShowingListContents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marketmapping.R;

import java.util.ArrayList;

public class ShoppingShowingListContentsAdapter extends RecyclerView.Adapter<ShoppingShowingListContentsAdapter.ShoppingShowingListContentsViewHolder> {

    private Context mContext;
    private ArrayList<ShoppingShowingListContentsItem> mShoppingShowingListContentsList;

    public ShoppingShowingListContentsAdapter(Context context, ArrayList<ShoppingShowingListContentsItem> shoppingShowingListContentsList ) {
        mContext = context;
        mShoppingShowingListContentsList = shoppingShowingListContentsList;
    }

    @NonNull
    @Override
    public ShoppingShowingListContentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.add_to_list_item, parent, false);
        return new ShoppingShowingListContentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingShowingListContentsViewHolder holder, int position) {
        ShoppingShowingListContentsItem currentItem = mShoppingShowingListContentsList.get(position);

        String foodName = currentItem.getFoodName();
        int itemAisle = currentItem.getItemAisle();
        int itemSection = currentItem.getItemSection();

        holder.mTextViewFoodName.setText(foodName);
        holder.mTextViewItemAisle.setText("Aisle: " + itemAisle);
        holder.mTextViewItemSection.setText("Section: " + itemSection);

    }

    @Override
    public int getItemCount() {

        return mShoppingShowingListContentsList.size();

    }


    public class ShoppingShowingListContentsViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewFoodName;
        public TextView mTextViewItemAisle;
        public TextView mTextViewItemSection;

        public ShoppingShowingListContentsViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewFoodName = itemView.findViewById(R.id.text_view_food_name);
            mTextViewItemAisle = itemView.findViewById(R.id.text_view_aisle_of_item);
            mTextViewItemSection = itemView.findViewById(R.id.text_view_shelf_of_item);
        }
    }

}
