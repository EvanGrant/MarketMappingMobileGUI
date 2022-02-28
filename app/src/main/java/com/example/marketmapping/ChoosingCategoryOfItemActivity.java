package com.example.marketmapping;

import static com.example.marketmapping.ChoosingStoreActivity.EXTRA_STORE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

public class ChoosingCategoryOfItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private ArrayList<ExampleCategory> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_category_of_item); //THIS ISNT WORKING BECAUSE I NEED
                                                                    // ANOTHER LAYOUT DETAIL SIMILAR TO CHOOSING STORE ACTIVITY
        Intent intent = getIntent();
        String storeName = intent.getStringExtra(EXTRA_STORE_NAME);

        //TextView textViewStore = findViewById(R.id.text_view_storename);

        //textViewStore.setText(storeName);

        mRecyclerView = findViewById(R.id.category_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "http://10.0.2.2:3000/categories/1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject storeObject = response.getJSONObject(i);

                                String categoryName = storeObject.getString("category");

                                mExampleList.add(new ExampleCategory(categoryName));
                            }

                            mCategoryAdapter = new CategoryAdapter(ChoosingCategoryOfItemActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mCategoryAdapter);

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