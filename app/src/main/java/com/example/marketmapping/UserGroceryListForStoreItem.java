package com.example.marketmapping;

public class UserGroceryListForStoreItem {
    private String mStoreName;
    private String mAddress;

    public UserGroceryListForStoreItem(String storeName, String address) {
        mStoreName = storeName;
        mAddress = address;
    }

    public String getStoreName() {
        return mStoreName;
    }

    public String getAddress() {
        return mAddress;
    }


}
