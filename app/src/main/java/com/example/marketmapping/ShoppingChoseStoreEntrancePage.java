package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marketmapping.ShoppingListRouting.ShoppingListRoutingPage;

import java.util.ArrayList;

public class ShoppingChoseStoreEntrancePage extends AppCompatActivity {

    public Button leftButton;
    public Button rightButton;



    public int passedUserID = 0;
    public int passedStoreID = 0;
    public String passedListName = "";

    public ArrayList<String> foodNames;
    public ArrayList<String> itemsListFullString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_chose_store_entrance_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedListName = intent.getStringExtra("passedListName");
        foodNames = (ArrayList<String>) getIntent().getSerializableExtra("itemsList");
        itemsListFullString = (ArrayList<String>) getIntent().getSerializableExtra("itemsListFullString");

        leftButton = (Button) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShoppingListRoutingPage();
            }
        });



        rightButton = (Button) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShoppingListRoutingPage();
            }
        });

    }

    public void openShoppingListRoutingPage() {

        Intent intent = new Intent(this, ShoppingListRoutingPage.class);

        intent.putExtra("passedListName", passedListName);
        intent.putExtra("passedUserID", passedUserID);
        intent.putExtra("passedStoreID", passedStoreID);
        intent.putExtra("itemsList", foodNames);
        intent.putExtra("itemsListFullString", itemsListFullString);

        startActivity(intent);


    }



}