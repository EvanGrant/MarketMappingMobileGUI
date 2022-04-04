package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShoppingChoosingListPage extends AppCompatActivity {

    private Button List1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_choosing_list_page);



        List1Button = (Button) findViewById(R.id.List1Button);
        List1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { OpenActivityShoppingWithChosenListPage(); }
        });


    }

    public void OpenActivityShoppingWithChosenListPage() {

        Intent intent = new Intent(this, ShoppingWithChosenListPage.class);

        startActivity(intent);

    }


}