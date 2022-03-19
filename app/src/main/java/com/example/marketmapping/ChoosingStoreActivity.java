package com.example.marketmapping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChoosingStoreActivity extends AppCompatActivity implements Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    public ArrayList<ExampleStoreName> mExampleList;
    private RequestQueue mRequestQueue;
    public ArrayList<JSONObject> mStoreObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_store);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mStoreObjects = new ArrayList<>();

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



                                mExampleList.add(new ExampleStoreName(storeName));
                                mStoreObjects.add(storeObject);
                            }

                            mAdapter = new Adapter(ChoosingStoreActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(ChoosingStoreActivity.this);

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


        TextView textView = view.findViewById(R.id.text_view_storename);

        //Get text from textview, that is the storename
        //using storename, get the position of the storename in mExampleList
        //iterate thru store list until I find storename that equals storename
        //using that position, get item out jsonobjectarray at that position
        // ex. if position 6 is shaws, then position 6 in the jsonobjectarray is the jsonobject for the store

        ExampleStoreName chosenStore = (mExampleList.get(position)); //this gets the value of the chosenstore at position and applies it to the ExampleStoreName datatype
        String storeNameString = chosenStore.getStoreName(); //then chosenStore gets converted to a string that I can use

        String store = "";

        try {
            for (int i = 0; i < mExampleList.size(); i++) {
                if (mExampleList.get(i).getStoreName().equals(storeNameString)) {
                    store = mStoreObjects.get(i).getString("id");
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        int storeID = Integer.parseInt(store);

        Intent CategoryItemIntent = new Intent(this, ChoosingCategoryOfItemActivity.class);
        ExampleStoreName clickedItem = mExampleList.get(position);

        CategoryItemIntent.putExtra("storeid", storeID);

        startActivity(CategoryItemIntent);
    }
}