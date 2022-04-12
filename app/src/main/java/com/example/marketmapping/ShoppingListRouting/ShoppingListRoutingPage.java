package com.example.marketmapping.ShoppingListRouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.HashMap;
import java.util.Locale;

public class ShoppingListRoutingPage extends AppCompatActivity implements TextToSpeech.OnInitListener {

    public TextToSpeech tts;

    public int counter = 0;

    private Context context;

    public Button pickedItemButton;
    public ImageButton redoButton;
    public ImageButton ttsButton;

    public int passedUserID = 0;
    public int passedStoreID = 0;
    public String passedListName = "";

    public ArrayList<String> foodNames;
    public ArrayList<String> itemsListFullString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_routing_page);

        tts = new TextToSpeech(this, this);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedListName = intent.getStringExtra("passedListName");
        foodNames = (ArrayList<String>) getIntent().getSerializableExtra("itemsList");
        itemsListFullString = (ArrayList<String>) getIntent().getSerializableExtra("itemsListFullString");

        ChangeText();

        ttsButton = (ImageButton) findViewById(R.id.ttsButton);

        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextToSpeechItemDirection();
            }
        });

    }

    private void ChangeText() {

        final EditText ChangingText = (EditText) findViewById(R.id.itemNameEditText);
        ChangingText.setText(foodNames.get(counter));
        pickedItemButton = (Button) findViewById(R.id.pickedButton);
        pickedItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangingText.setText(foodNames.get(counter));
                counter++;
            }
        });

        redoButton = (ImageButton) findViewById(R.id.imageButton);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                ChangingText.setText(foodNames.get(counter));
            }
        });

    }

    private void TextToSpeechItemDirection() {

        String tempVal = itemsListFullString.get(counter);
        tts.setLanguage(Locale.US);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(tempVal, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else {
            tts.speak(tempVal, TextToSpeech.QUEUE_FLUSH,null);
        }


    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                ttsButton.setEnabled(true);
                TextToSpeechItemDirection();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
