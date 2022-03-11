package com.example.marketmapping;

public class add_to_list_item {

    private String mFoodName;
    private int mShelfNumber;
    private int mAisleNumber;

    public add_to_list_item(String FoodName, int ShelfNumber, int AisleNumber) {
        mFoodName = FoodName;
        mShelfNumber = ShelfNumber;
        mAisleNumber = AisleNumber;
    }

    public String getFoodName() {
        return mFoodName;
    }

    public int getAisleNumber() {
        return mShelfNumber;
    }

    public int getShelfNumber() {
        return mAisleNumber;
    }

}

