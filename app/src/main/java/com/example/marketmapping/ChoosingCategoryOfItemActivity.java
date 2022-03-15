package com.example.marketmapping;

import static com.example.marketmapping.ChoosingStoreActivity.EXTRA_STORE_NAME;
import static com.example.marketmapping.ChoosingStoreActivity.storeID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ChoosingCategoryOfItemActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {
    public static final String EXTRA_CATEGORY = "categoryName";
    public int passedStoreID = 0;

    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private ArrayList<ExampleCategory> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_category_of_item);

        Intent intent = getIntent();
        String storeName = intent.getStringExtra(EXTRA_STORE_NAME); //I think this value is the store position. Give it a whirl and see if it works.
        passedStoreID = intent.getIntExtra("storeposition", 0);
        passedStoreID += 1; //This is to give it correct position, temporary

        mRecyclerView = findViewById(R.id.category_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

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
        Intent categoryIntent = new Intent(this, AddItemtoAddtoListPage.class);
        ExampleCategory clickedCategory = mExampleList.get(position);

        categoryIntent.putExtra(EXTRA_CATEGORY, clickedCategory.getCategoryName());

        startActivity(categoryIntent);

    }
}