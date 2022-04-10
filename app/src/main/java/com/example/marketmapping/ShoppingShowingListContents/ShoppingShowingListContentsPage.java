package com.example.marketmapping.ShoppingShowingListContents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketmapping.R;
import com.example.marketmapping.ShoppingShowListNames.ShowListNamesAdapter;
import com.example.marketmapping.ShoppingShowListNames.ShowListNamesItem;
import com.example.marketmapping.ShoppingShowListNames.ShowListNamesPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShoppingShowingListContentsPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ShoppingShowingListContentsAdapter mShoppingShowingListContentsAdapter;
    private ArrayList<ShoppingShowingListContentsItem> mShoppingShowingListContentsList;
    private RequestQueue mRequestQueue;

    public String passedListName = "";
    public int passedUserID = 0;
    public int passedStoreID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_showing_list_contents_page);

        Intent intent = getIntent();

        passedListName = intent.getStringExtra("passedListName");
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedStoreID = intent.getIntExtra("passedStoreID", 0);

        mRecyclerView = findViewById(R.id.shopping_showing_list_contents_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShoppingShowingListContentsList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void parseJSON(){
        //Get new query from Noah that sorts the contents of the list, then input here to get it working
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


                                mShoppingShowingListContentsList.add(new ShoppingShowingListContentsItem(foodName, itemAisle, itemSection));
                            }

                            mShoppingShowingListContentsAdapter = new ShoppingShowingListContentsAdapter(ShoppingShowingListContentsPage.this, mShoppingShowingListContentsList);
                            mRecyclerView.setAdapter(mShoppingShowingListContentsAdapter);

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