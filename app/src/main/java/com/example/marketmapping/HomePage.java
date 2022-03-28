package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class HomePage extends AppCompatActivity {
    private Button button;
    private Button buttonViewListofUser;

    public String passedEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent = getIntent();
        passedEmail = intent.getStringExtra("passedEmail");




        button = (Button) findViewById(R.id.startShoppingButtonHomePage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityChoosingStorePage(); }


        });

        buttonViewListofUser = (Button) findViewById(R.id.viewListsofUserButton);
        buttonViewListofUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityShowStoresofUserListsPage();
                 }
        });


    }


    public void openActivityChoosingStorePage()
    {
        Intent intent = new Intent(this, ChoosingStoreActivity.class);
        startActivity(intent);
    }

    public void openActivityShowStoresofUserListsPage()
    {
        Intent intent = new Intent(this, ShowStoresOfUserListsPage.class);
        startActivity(intent);
    }

}