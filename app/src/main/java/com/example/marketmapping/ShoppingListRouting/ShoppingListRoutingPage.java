package com.example.marketmapping.ShoppingListRouting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class ShoppingListRoutingPage extends AppCompatActivity {

    TextToSpeech tts;

    public int counter = 0;

    public Button pickedItemButton;
    public EditText editTextItemName;

    public int passedUserID = 0;
    public int passedStoreID = 0;
    public String passedListName = "";
    private RequestQueue mRequestQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_routing_page);


        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedListName = intent.getStringExtra("passedListName");
        ArrayList<String> foodNames = (ArrayList<String>) getIntent().getSerializableExtra("itemsList");



        mRequestQueue = Volley.newRequestQueue(this);

        getItemInfo();

        editTextItemName = (EditText) findViewById(R.id.itemNameEditText);
        //editTextItemName.setText(itemsListString[counter]);

        pickedItemButton = (Button) findViewById(R.id.pickedButton);
        pickedItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void getItemInfo() {

        String url = "http://10.0.2.2:3000/findList/" + passedStoreID + "/" + passedUserID + "/" + passedListName;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject foodObject = response.getJSONObject(i);

                                String foodName = foodObject.getString("name");
                                int itemAisle = foodObject.getInt("aisleId");
                                int itemSection = foodObject.getInt("sectionId");

                                /*
                                if (itemsListString[i] == null) {
                                    itemsListString[i] = "go to aisle " + itemAisle + ", section" + itemSection + "for " + foodName;
                                }
                                else {
                                    itemsListString[i] += " and " + foodName;
                                }

                                */

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(jsonArrayRequest);

    }

}