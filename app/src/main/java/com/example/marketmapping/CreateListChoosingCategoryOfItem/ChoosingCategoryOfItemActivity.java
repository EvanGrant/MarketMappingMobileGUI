package com.example.marketmapping.CreateListChoosingCategoryOfItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.marketmapping.AddItemtoAddtoListPage;
import com.example.marketmapping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChoosingCategoryOfItemActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {
    public int passedStoreID;

    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private ArrayList<ExampleCategory> mExampleList;
    public ArrayList<JSONObject> mStoreObjects;
    private RequestQueue mRequestQueue;

    public int passedUserID;
    public String passedListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_category_of_item);

        Intent intent = getIntent();
        passedStoreID = intent.getIntExtra("storeID", 0);
        passedUserID = intent.getIntExtra("passedUserID", 0);
        passedListName = intent.getStringExtra("passedListName");

        mRecyclerView = findViewById(R.id.category_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mStoreObjects = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "http://10.0.2.2:3000/categories/" + passedStoreID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject storeObject = response.getJSONObject(i);

                                String categoryName = storeObject.getString("category");

                                mExampleList.add(new ExampleCategory(categoryName));
                                mStoreObjects.add(storeObject);
                            }

                            mCategoryAdapter = new CategoryAdapter(ChoosingCategoryOfItemActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mCategoryAdapter);
                            mCategoryAdapter.setOnItemClickListener(ChoosingCategoryOfItemActivity.this);

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
    public void onItemClick(View view, int position) {
        Intent addItemIntent = new Intent(this, AddItemtoAddtoListPage.class);


        ExampleCategory chosenCategory = mExampleList.get(position);
        String categoryNameString = chosenCategory.getCategoryName();

        addItemIntent.putExtra("storeCategory", categoryNameString);
        addItemIntent.putExtra("storeid", passedStoreID);
        addItemIntent.putExtra("passedUserID", passedUserID);
        addItemIntent.putExtra("passedListName" , passedListName); //passes ListName

        startActivity(addItemIntent);

    }
}