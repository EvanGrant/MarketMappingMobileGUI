package com.example.marketmapping;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowStoresOfUserListsPage extends AppCompatActivity implements UserGroceryListForStoreAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private UserGroceryListForStoreAdapter mUserGroceryListForStoreAdapter;
    private ArrayList<UserGroceryListForStoreItem> mUserGroceryListForStoreItemList;
    private RequestQueue mRequestQueue;

    public int passedUserID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stores_of_user_lists_page);

        Intent intent = getIntent();
        passedUserID = intent.getIntExtra("passedUserID", 0);

        mRecyclerView = findViewById(R.id.ShowingStoresofUserRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserGroceryListForStoreItemList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }

    private void parseJSON() {

        String url = "http://10.0.2.2:3000/stores/";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject storeObject = response.getJSONObject(i);

                                String storeName = storeObject.getString("name");
                                String address = storeObject.getString("address");


                                mUserGroceryListForStoreItemList.add(new UserGroceryListForStoreItem(storeName, address));

                            }

                            mUserGroceryListForStoreAdapter = new UserGroceryListForStoreAdapter(ShowStoresOfUserListsPage.this, mUserGroceryListForStoreItemList);
                            mRecyclerView.setAdapter(mUserGroceryListForStoreAdapter);
                            mUserGroceryListForStoreAdapter.setOnItemClickListener(ShowStoresOfUserListsPage.this);


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

    @Override
    public void onItemClick(int position) {
        //Intent intent = new intent(this, (this will be the next page));
        //startActivity(this will be the next page);
    }
}
