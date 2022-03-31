package com.example.marketmapping;

public class ShowContentsOfListItem {

    private String mFoodName;
    private int mShelfNumber;
    private int mAisleNumber;

    public ShowContentsOfListItem(String FoodName, int ShelfNumber, int AisleNumber) {
        mFoodName = FoodName;
        mShelfNumber = ShelfNumber;
        mAisleNumber = AisleNumber;
    }

    public String getFoodName() {
        return mFoodName;
    }

    public int getAisleNumber() {
        return mAisleNumber;
    }

    public int getShelfNumber() {
        return mShelfNumber;
    }

}

