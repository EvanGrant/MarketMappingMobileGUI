package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NamingListPage extends AppCompatActivity {

    private Button listNameButton;

    public int passedUserID = 0;
    public String passedListName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_list_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0); //gets userid passed from homepage

        EditText listNameBox = findViewById(R.id.ListNameEditText);




        listNameButton = (Button) findViewById(R.id.SubmitListNameButton);
        listNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passedListName = listNameBox.getText().toString();

                openActivityChoosingStorePage();
            }
        });

    }

    public void openActivityChoosingStorePage()
    {
        Intent intent = new Intent(this, ChoosingStoreActivity.class);

        intent.putExtra("passedUserID", passedUserID);
        intent.putExtra("passedListName", passedListName);

        startActivity(intent);
    }


}