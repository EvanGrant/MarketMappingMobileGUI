package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ShowListNamesPage extends AppCompatActivity {

    public int passedUserID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_names_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);

    }
}