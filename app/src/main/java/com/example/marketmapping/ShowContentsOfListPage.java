package com.example.marketmapping;
//THIS IS FOR EDIT LIST ROUTE, GETS CREATED WHEN CLICKING ON EDIT LIST BUTTON IN HOME PAGE
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

public class ShowContentsOfListPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ShowContentsOfListAdapter mShowContentsOfListAdapter;
    private ArrayList<ShowContentsOfListItem> mShowContentsOfListList;
    private RequestQueue mRequestQueue;

    public int passedStoreID = 0;
    public int passedUserID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contents_of_list_page);

        Intent intent = getIntent();
        passedStoreID = intent.getIntExtra("passedStoreID", 0);
        passedUserID = intent.getIntExtra("passedUserID",0);

        mRecyclerView = findViewById(R.id.show_contents_of_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShowContentsOfListList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON(){
        String url = "http://10.0.2.2:3000/findlist/" + passedUserID + "/" + passedStoreID;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject foodObject = response.getJSONObject(i);

                                String foodName = foodObject.getString("name");
                                int aisleNumber = foodObject.getInt("aisle_id");
                                int shelfNumber = foodObject.getInt("section_id");

                                mShowContentsOfListList.add(new ShowContentsOfListItem(foodName, aisleNumber, shelfNumber));
                            }

                            mShowContentsOfListAdapter = new ShowContentsOfListAdapter(ShowContentsOfListPage.this, mShowContentsOfListList);
                            mRecyclerView.setAdapter(mShowContentsOfListAdapter);

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