package com.example.marketmapping.EditListShowContentsOfList;
//THIS IS FOR EDIT LIST ROUTE, GETS CREATED WHEN CLICKING ON A STORE IN EDIT LIST BUTTON IN HOME PAGE
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

