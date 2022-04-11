package com.example.marketmapping.ShoppingListRouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.marketmapping.R;

public class ShoppingListRoutingPage extends AppCompatActivity {

    public int passedUserID = 0;
    public int passedStoreID = 0;
    public String passedListName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_routing_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedListName = intent.getStringExtra("passedListName");



    }
}