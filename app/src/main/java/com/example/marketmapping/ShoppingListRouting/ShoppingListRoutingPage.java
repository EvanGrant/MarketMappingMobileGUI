package com.example.marketmapping.ShoppingListRouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketmapping.R;
import com.example.marketmapping.ShoppingShowingListContents.ShoppingShowingListContentsAdapter;
import com.example.marketmapping.ShoppingShowingListContents.ShoppingShowingListContentsItem;
import com.example.marketmapping.ShoppingShowingListContents.ShoppingShowingListContentsPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingListRoutingPage extends AppCompatActivity {

    TextToSpeech tts;

    public int counter = 0;

    public Button pickedItemButton;

    public int passedUserID = 0;
    public int passedStoreID = 0;
    public String passedListName = "";

    public ArrayList<String> foodNames;
    public ArrayList<String> itemsListFullString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_routing_page);


        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedListName = intent.getStringExtra("passedListName");
        foodNames = (ArrayList<String>) getIntent().getSerializableExtra("itemsList");
        itemsListFullString = (ArrayList<String>) getIntent().getSerializableExtra("itemsListFullString");

        ChangeText();

        //TextToSpeechItemDirection();




    }
/*
    private void TextToSpeechItemDirection() {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.US);
                    tts.setSpeechRate(1.0f);
                    tts.speak(itemsListFullString.get(counter), TextToSpeech.QUEUE_ADD, null);
                }
            }
        });
    }

 */

    private void ChangeText() {

        final EditText ChangingText = (EditText) findViewById(R.id.itemNameEditText);
        ChangingText.setText(foodNames.get(counter));
        pickedItemButton = (Button) findViewById(R.id.pickedButton);
        pickedItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangingText.setText(foodNames.get(counter));

                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                    String tempVal = itemsListFullString.get(counter);

                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.US);
                            tts.setSpeechRate(1.0f);
                            tts.speak(tempVal, TextToSpeech.QUEUE_FLUSH, null);
                            Toast.makeText(ShoppingListRoutingPage.this, "It should work", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                counter++;

            }
        });

    }

}