package com.example.marketmapping;

import static com.example.marketmapping.ChoosingStoreActivity.EXTRA_STORE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChoosingCategoryOfItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_category_of_item); //THIS ISNT WORKING BECAUSE I NEED
                                                                    // ANOTHER LAYOUT DETAIL SIMILAR TO CHOOSING STORE ACTIVITY

        Intent intent = getIntent();
        String storeName = intent.getStringExtra(EXTRA_STORE_NAME);

        TextView textViewStore = findViewById(R.id.text_view_storename);

        textViewStore.setText(storeName);

    }
}