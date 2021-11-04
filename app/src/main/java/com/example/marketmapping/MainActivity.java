package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    //  syntax for making a button go to the desired page \|/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.SubmitButtonActivityMain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChoosingStoreActivity();
            }
        });
    }

    public void openChoosingStoreActivity()
    {
        Intent intent = new Intent(this, ChoosingStoreActivity.class);
        startActivity(intent);
    }

    // syntax for making a button go to the desired page ^

}

