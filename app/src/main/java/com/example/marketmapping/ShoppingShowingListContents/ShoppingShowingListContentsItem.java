package com.example.marketmapping.ShoppingShowingListContents;

public class ShoppingShowingListContentsItem {

    private String mFoodName;
    private int mItemAisle;
    private int mItemSection;

    public ShoppingShowingListContentsItem(String FoodName, int ItemAisle, int ItemSection) {

        mFoodName = FoodName;
        mItemAisle = ItemAisle;
        mItemSection = ItemSection;

    }

    public String getFoodName() { return mFoodName; }

    public int getItemAisle() { return mItemAisle; }

    public int getItemSection() { return mItemSection; }


}
